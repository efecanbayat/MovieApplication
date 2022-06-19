package com.efecanbayat.movieapplication.ui.feature.movieDetail.adapter

import com.efecanbayat.movieapplication.ui.feature.movieDetail.OnCastItemClickListener
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.efecanbayat.movieapplication.data.model.response.movieDetail.Cast
import com.efecanbayat.movieapplication.databinding.ItemCastBinding

class MovieCastAdapter(private val onItemClickListener: OnCastItemClickListener) :
    ListAdapter<Cast, MovieCastAdapter.MovieCastViewHolder>(MovieCastDiffUtil()) {

    inner class MovieCastViewHolder(val binding: ItemCastBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieCastViewHolder {
        return MovieCastViewHolder(ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MovieCastViewHolder, position: Int) {
        val item = getItem(position) as Cast
        holder.binding.item = item
        holder.binding.onItemClickListener = onItemClickListener
        holder.binding.executePendingBindings()
    }
}

class MovieCastDiffUtil : DiffUtil.ItemCallback<Cast>() {
    override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Cast,
        newItem: Cast
    ): Boolean {
        return oldItem == newItem
    }
}