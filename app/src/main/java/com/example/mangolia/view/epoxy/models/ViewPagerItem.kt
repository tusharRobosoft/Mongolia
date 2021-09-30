package com.example.mangolia.view.epoxy.models

import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.example.mangolia.R

@EpoxyModelClass(layout = R.layout.data_class_item_carousal)
abstract class ViewPagerItem : EpoxyModelWithHolder<ViewPagerItem.Holder>() {

    @EpoxyAttribute
    lateinit var title: String
    @EpoxyAttribute
    lateinit var description: String
    @EpoxyAttribute
    lateinit var url: String

    override fun bind(holder: Holder) {
        super.bind(holder)
        holder.textTitle.text = title
        holder.textDesc.text = description
        Glide.with(holder.textDesc.context)
            .load(url).into(holder.imageView);
    }

    class Holder : BaseEpoxyHolder() {
        val textTitle: TextView by bind(R.id.title)
        val textDesc: TextView by bind(R.id.tv_desc)
        val imageView: ImageView by bind(R.id.iv_image)
    }
}
