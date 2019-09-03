package com.wildanka.pagingstackexchange.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wildanka.pagingstackexchange.R
import com.wildanka.pagingstackexchange.model.entity.Items

class StackItemAdapter(private val mContext : Context): PagedListAdapter<Items, StackItemAdapter.ItemViewHolder>(ItemsDiffCallback) { // super(ItemsDiffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_stack_answer, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item : Items? = getItem(position)

        holder.bind(item)

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
        private val ivIcon = itemView.findViewById<ImageView>(R.id.iv_icon);
        private val tvUsername = itemView.findViewById<TextView>(R.id.tv_username)

        fun bind(item: Items?){
            if (item != null){
                Glide.with(mContext)
                    .load(item.owner?.profileImage)
                    .into(ivIcon)
            }

            tvUsername.text = item?.owner?.displayName

            Log.e("StackItemAdapt", item?.owner?.profileImage+" | "+item?.owner?.displayName)
        }
    }

}