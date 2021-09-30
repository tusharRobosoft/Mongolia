package com.example.mangolia

import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.mangolia.models.Source
import com.example.mangolia.view.epoxy.helper.KotlinModel

data class ItemDataClass(
    val source: Source
) : KotlinModel(R.layout.data_class_item) {

    val image by bind<ImageView>(R.id.iv_image)
    val titleView by bind<TextView>(R.id.title)

    override fun bind() {
        titleView.text = source.title
        Glide.with(titleView.context)
            .load(source.image?.url).into(image);
    }
}