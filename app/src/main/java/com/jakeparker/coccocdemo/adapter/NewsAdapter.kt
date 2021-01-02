package com.jakeparker.coccocdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jakeparker.coccocdemo.databinding.ItemNewsBinding
import com.jakeparker.coccocdemo.model.Item

/**
 * @author Long Tran
 * @since 02/01/2021
 */
class NewsAdapter(private val callback: Callback) :
    ListAdapter<Item, NewsAdapter.NewsViewHolder>(NewsDiffCallback()) {

    interface Callback {
        fun onSelect(item: Item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            callback
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindDataToView(item, position)
    }

    class NewsViewHolder(
        private val binding: ItemNewsBinding,
        private val callback: Callback
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindDataToView(data: Item, dataPosition: Int) {
            binding.apply {
                item = data
                onClickListener = View.OnClickListener {
                    callback.onSelect(data)
                }
                executePendingBindings()
            }
        }
    }
}

class NewsDiffCallback : DiffUtil.ItemCallback<Item>() {

    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return (oldItem.title == newItem.title &&
                oldItem.link == oldItem.link)
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}