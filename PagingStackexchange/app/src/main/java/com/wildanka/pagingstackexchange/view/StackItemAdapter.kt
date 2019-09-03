package com.wildanka.pagingstackexchange.view

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wildanka.pagingstackexchange.model.Items

class StackItemAdapter(mContext : Context): PagedListAdapter<Items, StackItemAdapter.ItemViewHolder>(ItemsDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        val ItemsDiffCallback = object : DiffUtil.ItemCallback<Items>() {
            override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean {
                return oldItem.answerId == newItem.answerId
            }

            override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

}