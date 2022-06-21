package com.efecanbayat.movieapplication.ui.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.efecanbayat.movieapplication.R
import com.efecanbayat.movieapplication.data.model.*
import com.efecanbayat.movieapplication.databinding.ItemMovieListBinding
import com.efecanbayat.movieapplication.databinding.ItemSearchedMovieListBinding
import com.efecanbayat.movieapplication.databinding.ItemSearchedPersonListBinding
import com.efecanbayat.movieapplication.ui.feature.home.OnHomeItemClickListener

class HomeAdapter(private val onItemClickListener: OnHomeItemClickListener) : ListAdapter<HomeData, RecyclerView.ViewHolder>(HomeDataDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_searched_movie_list -> {
                SearchedMovieViewHolder(ItemSearchedMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            R.layout.item_searched_person_list -> {
                SearchedPersonViewHolder(ItemSearchedPersonListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            R.layout.item_movie_list -> {
                PopularMoviesViewHolder(ItemMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_searched_movie_list -> {
                (holder as SearchedMovieViewHolder).bind(getItem(position) as SearchedMovieData, onItemClickListener)
            }
            R.layout.item_searched_person_list -> {
                (holder as SearchedPersonViewHolder).bind(getItem(position) as SearchedPersonData, onItemClickListener)
            }
            R.layout.item_movie_list -> {
                (holder as PopularMoviesViewHolder).bind(getItem(position) as PopularMovieData, onItemClickListener)
            }
        }
    }

    class HomeDataDiffUtil : DiffUtil.ItemCallback<HomeData>() {
        override fun areItemsTheSame(oldItem: HomeData, newItem: HomeData): Boolean {
            return oldItem.type == newItem.type
        }

        override fun areContentsTheSame(oldItem: HomeData, newItem: HomeData): Boolean {
            if (oldItem is SearchedMovieData && newItem is SearchedMovieData) {
                return oldItem.movieItem == newItem.movieItem
            } else if (oldItem is SearchedPersonData && newItem is SearchedPersonData) {
                return oldItem.personItem == newItem.personItem
            } else if (oldItem is PopularMovieData && newItem is PopularMovieData) {
                return oldItem.movieItem == newItem.movieItem
            }
            return false
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).type) {
            HomeDataType.SEARCHED_MOVIE_LIST -> {
                R.layout.item_searched_movie_list
            }
            HomeDataType.SEARCHED_PERSON_LIST -> {
                R.layout.item_searched_person_list
            }
            HomeDataType.POPULAR_MOVIE_LIST -> {
                R.layout.item_movie_list
            }
        }
    }

    inner class SearchedMovieViewHolder(private val binding: ItemSearchedMovieListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(searchedMovieData: SearchedMovieData, onItemClickListener: OnHomeItemClickListener) {
            binding.searchedMovieData = searchedMovieData
            binding.onItemClickListener = onItemClickListener
            binding.executePendingBindings()
        }
    }

    inner class SearchedPersonViewHolder(private val binding: ItemSearchedPersonListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(searchedPersonData: SearchedPersonData, onItemClickListener: OnHomeItemClickListener) {
            binding.searchedPersonData = searchedPersonData
            binding.onItemClickListener = onItemClickListener
            binding.executePendingBindings()
        }
    }

    inner class PopularMoviesViewHolder(private val binding: ItemMovieListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(popularMovieData: PopularMovieData, onItemClickListener: OnHomeItemClickListener) {
            binding.popularMovieData = popularMovieData
            binding.onItemClickListener = onItemClickListener
            binding.executePendingBindings()
        }
    }
}