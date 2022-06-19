package com.efecanbayat.movieapplication.ui.feature.home.adapter

import com.efecanbayat.movieapplication.ui.feature.home.OnHomeItemClickListener
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.efecanbayat.movieapplication.databinding.ItemSearchedMovieBinding
import com.efecanbayat.movieapplication.ui.feature.home.MovieItemViewType

class SearchedMovieAdapter(private val onItemClickListener: OnHomeItemClickListener) :
    ListAdapter<MovieItemViewType, SearchedMovieAdapter.SearchedMovieViewHolder>(SearchedMovieDiffUtil()) {

    inner class SearchedMovieViewHolder(val binding: ItemSearchedMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchedMovieAdapter.SearchedMovieViewHolder {
        return SearchedMovieViewHolder(ItemSearchedMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SearchedMovieAdapter.SearchedMovieViewHolder, position: Int) {
        val item = getItem(position) as MovieItemViewType.SearchMovieItem
        holder.binding.item = item.movie
        holder.binding.onItemClickListener = onItemClickListener
        holder.binding.executePendingBindings()
    }
}

class SearchedMovieDiffUtil : DiffUtil.ItemCallback<MovieItemViewType>() {
    override fun areItemsTheSame(oldItem: MovieItemViewType, newItem: MovieItemViewType): Boolean {
        if (oldItem is MovieItemViewType.SearchMovieItem && newItem is MovieItemViewType.SearchMovieItem)
            return oldItem.movie.id == newItem.movie.id
        return false
    }

    override fun areContentsTheSame(
        oldItem: MovieItemViewType,
        newItem: MovieItemViewType
    ): Boolean {
        return oldItem == newItem
    }
}