package com.efecanbayat.movieapplication.ui.feature.home.adapter

import com.efecanbayat.movieapplication.databinding.ItemSearchedPersonBinding
import com.efecanbayat.movieapplication.ui.feature.home.OnHomeItemClickListener
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.efecanbayat.movieapplication.ui.feature.home.MovieItemViewType

class SearchedPersonAdapter(private val onItemClickListener: OnHomeItemClickListener) :
    ListAdapter<MovieItemViewType, SearchedPersonAdapter.SearchedPersonViewHolder>(SearchedPersonDiffUtil()) {

    inner class SearchedPersonViewHolder(val binding: ItemSearchedPersonBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchedPersonAdapter.SearchedPersonViewHolder {
        return SearchedPersonViewHolder(ItemSearchedPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SearchedPersonAdapter.SearchedPersonViewHolder, position: Int) {
        val item = getItem(position) as MovieItemViewType.SearchPersonItem
        holder.binding.item = item.person
        holder.binding.onItemClickListener = onItemClickListener
        holder.binding.executePendingBindings()
    }
}

class SearchedPersonDiffUtil : DiffUtil.ItemCallback<MovieItemViewType>() {
    override fun areItemsTheSame(oldItem: MovieItemViewType, newItem: MovieItemViewType): Boolean {
        if (oldItem is MovieItemViewType.SearchPersonItem && newItem is MovieItemViewType.SearchPersonItem)
            return oldItem.person.id == newItem.person.id
        return false
    }

    override fun areContentsTheSame(
        oldItem: MovieItemViewType,
        newItem: MovieItemViewType
    ): Boolean {
        return oldItem == newItem
    }
}