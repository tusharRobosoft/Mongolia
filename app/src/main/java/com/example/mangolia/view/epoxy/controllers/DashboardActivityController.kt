package com.example.mangolia.view.epoxy.controllers

import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.VisibilityState
import com.airbnb.epoxy.carousel
import com.bumptech.glide.Glide
import com.example.mangolia.R
import com.example.mangolia.databinding.DataClassItemBinding
import com.example.mangolia.databinding.DataClassItemCarousalBinding
import com.example.mangolia.models.Items
import com.example.mangolia.models.Packages
import com.example.mangolia.view.epoxy.helper.ViewBindingKotlinModel
import com.example.mangolia.view.epoxy.models.LoadingEpoxyModel
import com.example.mangolia.view.epoxy.models.TabLayoutModel_
import com.example.mangolia.view.epoxy.models.ViewPagerItem_

class DashboardActivityController() : EpoxyController() {
    var isLoading: Boolean = false
        set(value) {
            field = value
            if(field)
                requestModelBuild()
        }

    var packageList = ArrayList<Packages>()
        set(value) {
            field = value
            isLoading = false
            requestModelBuild()
        }



    override fun buildModels() {
        if (isLoading){
            LoadingEpoxyModel().id("Loading State").addTo(this)
            return
        }

        if(packageList.isEmpty()){
            //TODO show is empty screen
            return
        }

        val temp = packageList


        carousel {
            id("This is a ViewPager.")
            models(temp[0].items?.mapIndexed{ index, item ->
                ViewPagerItem_()
                    .id(item.source?.id)
                    .title(item.source?.title)
                    .description(item.source?.description)
                    .url(item.source?.image?.url)
                    .onVisibilityStateChanged { _, _, visibilityState ->
                        if (visibilityState == VisibilityState.FOCUSED_VISIBLE) {
                            temp[0].visibleItemIndex = index
                            this@DashboardActivityController.requestModelBuild()
                        }
                    }

            }!!)

        }
        TabLayoutModel_()
            .id("This is the ViewPager's TabLayout.")
            .count(temp[0].items!!.size)
            .checked(temp[0].visibleItemIndex)
            .addTo(this)


        packageList[1].items?.forEach {
            ItemViewEpoxyModel(it)
                .id(it.source?.id)
                .addTo(this)
        }
    }

    data class ItemViewEpoxyModel(
        val item: Items
    ):ViewBindingKotlinModel<DataClassItemBinding>(R.layout.data_class_item) {
        override fun DataClassItemBinding.bind() {
            title.text = item.source?.title
            Glide.with(title.context)
                .load(item.source?.image?.url).into(ivImage);
        }
    }
}