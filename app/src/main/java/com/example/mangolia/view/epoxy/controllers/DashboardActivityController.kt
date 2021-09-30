package com.example.mangolia.view.epoxy.controllers

import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyController
import com.bumptech.glide.Glide
import com.example.mangolia.R
import com.example.mangolia.databinding.DataClassItemBinding
import com.example.mangolia.databinding.DataClassItemCarousalBinding
import com.example.mangolia.models.Items
import com.example.mangolia.view.epoxy.helper.ViewBindingKotlinModel
import com.example.mangolia.view.epoxy.models.LoadingEpoxyModel

class DashboardActivityController() : EpoxyController() {
    var isLoading: Boolean = false
        set(value) {
            field = value
            if(field)
                requestModelBuild()
        }

    var itemList = ArrayList<Items>()
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

        if(itemList.isEmpty()){
            //TODO show is empty screen
            return
        }

        val items = itemList.map {
            ItemCarousalModel(it).id(it.source?.id)
        }

        CarouselModel_()
            .id("carousal")
            .models(items)
            .addTo(this)


        itemList.forEach {
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

    data class ItemCarousalModel(
        val item: Items
    ):ViewBindingKotlinModel<DataClassItemCarousalBinding>(R.layout.data_class_item_carousal){
        override fun DataClassItemCarousalBinding.bind() {
            title.text = item.source?.title
            tvDesc.text = item.source?.description
            Glide.with(title.context)
                .load(item.source?.image?.url).into(ivImage);
        }

    }
}