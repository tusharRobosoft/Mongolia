package com.example.mangolia.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mangolia.`interface`.ListItems


abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: ListItems)
//    abstract fun setListener(listener: DashBoardListener)
}