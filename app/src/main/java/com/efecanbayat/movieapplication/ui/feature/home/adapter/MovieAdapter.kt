package com.efecanbayat.movieapplication.ui.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.efecanbayat.movieapplication.R
import com.efecanbayat.movieapplication.databinding.ItemMovieBinding
import com.efecanbayat.movieapplication.databinding.ItemMovieLoadingBinding
import com.efecanbayat.movieapplication.ui.feature.home.MovieItemViewType
import com.efecanbayat.movieapplication.ui.feature.home.OnHomeItemClickListener

class MovieAdapter(private val onItemClickListener: OnHomeItemClickListener) :
    ListAdapter<MovieItemViewType, RecyclerView.ViewHolder>(MovieDiffUtil()) {

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieItemViewType.MovieItem) {
            binding.item = item.movie
            binding.onItemClickListener = onItemClickListener
            binding.executePendingBindings()
        }
    }

    inner class LoadingViewHolder(val binding: ItemMovieLoadingBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_movie -> MovieViewHolder(
                ItemMovieBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
            R.layout.item_movie_loading -> LoadingViewHolder(
                ItemMovieLoadingBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_movie -> (holder as MovieViewHolder).bind(getItem(position) as MovieItemViewType.MovieItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is MovieItemViewType.MovieItem -> R.layout.item_movie
            is MovieItemViewType.Loading -> R.layout.item_movie_loading
            else -> {
                throw IllegalArgumentException("Unknown view type")
            }
        }
    }
}

class MovieDiffUtil : DiffUtil.ItemCallback<MovieItemViewType>() {
    override fun areItemsTheSame(
        oldItem: MovieItemViewType,
        newItem: MovieItemViewType
    ): Boolean {
        if (oldItem is MovieItemViewType.MovieItem && newItem is MovieItemViewType.MovieItem)
            return oldItem.movie.id == newItem.movie.id
        else if (oldItem is MovieItemViewType.Loading && newItem is MovieItemViewType.Loading)
            return true
        return false
    }

    override fun areContentsTheSame(
        oldItem: MovieItemViewType,
        newItem: MovieItemViewType
    ): Boolean {
        return oldItem == newItem
    }
}