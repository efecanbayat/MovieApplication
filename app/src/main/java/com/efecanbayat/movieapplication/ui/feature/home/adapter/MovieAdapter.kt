package com.efecanbayat.movieapplication.ui.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.efecanbayat.movieapplication.databinding.ItemMovieBinding
import com.efecanbayat.movieapplication.ui.feature.home.MovieItemViewType
import com.efecanbayat.movieapplication.ui.feature.home.OnHomeItemClickListener

class MovieAdapter(private val onItemClickListener: OnHomeItemClickListener) :
    ListAdapter<MovieItemViewType, MovieAdapter.MovieViewHolder>(MovieDiffUtil()) {

    inner class MovieViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter.MovieViewHolder {
        return MovieViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        val item = getItem(position) as MovieItemViewType.MovieItem
        holder.binding.item = item.movie
        holder.binding.onItemClickListener = onItemClickListener
        holder.binding.executePendingBindings()
    }
}

class MovieDiffUtil : DiffUtil.ItemCallback<MovieItemViewType>() {
    override fun areItemsTheSame(
        oldItem: MovieItemViewType,
        newItem: MovieItemViewType
    ): Boolean {
        if (oldItem is MovieItemViewType.MovieItem && newItem is MovieItemViewType.MovieItem)
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