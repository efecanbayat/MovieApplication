package com.efecanbayat.movieapplication.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.efecanbayat.movieapplication.data.model.HomeDataHolder
import com.efecanbayat.movieapplication.data.model.PopularMovieData
import com.efecanbayat.movieapplication.data.model.SearchedMovieData
import com.efecanbayat.movieapplication.data.model.SearchedPersonData
import com.efecanbayat.movieapplication.ui.feature.home.HomeViewModel
import com.efecanbayat.movieapplication.ui.feature.home.OnHomeItemClickListener
import com.efecanbayat.movieapplication.ui.feature.home.adapter.HomeAdapter
import com.efecanbayat.movieapplication.ui.feature.home.adapter.MovieAdapter
import com.efecanbayat.movieapplication.ui.feature.home.adapter.SearchedMovieAdapter
import com.efecanbayat.movieapplication.ui.feature.home.adapter.SearchedPersonAdapter
import com.efecanbayat.movieapplication.ui.feature.movieDetail.CastData
import com.efecanbayat.movieapplication.ui.feature.movieDetail.OnCastItemClickListener
import com.efecanbayat.movieapplication.ui.feature.movieDetail.adapter.MovieCastAdapter

@BindingAdapter("imageUrl")
fun ImageView.loadUrlImage(url: String?) {
    Glide.with(this)
        .load(if (url?.isNotEmpty() == true) "${Constants.BASE_IMAGE_URL}$url" else null)
        .into(this)
}

@BindingAdapter(value = ["app:loadHomeData", "app:onItemClickListener", "app:viewModel"])
fun RecyclerView.loadHomeData(homeDataHolder: HomeDataHolder?, onItemClickListener: OnHomeItemClickListener, viewModel: HomeViewModel) {
    homeDataHolder?.let {
        val homeAdapter = if (adapter != null) {
            adapter as HomeAdapter
        } else {
            adapter = HomeAdapter(onItemClickListener, viewModel)
            adapter as HomeAdapter
        }
        homeAdapter.submitList(it.prepareData())
    }
}

@BindingAdapter(value = ["app:loadSearchedMovieList", "app:onItemClickListener"])
fun RecyclerView.loadSearchedMovieList(searchedMovieData: SearchedMovieData?, onItemClickListener: OnHomeItemClickListener) {
    searchedMovieData?.movieItem?.let {
        val searchedMovieAdapter = if (adapter != null) {
            adapter as SearchedMovieAdapter
        } else {
            adapter = SearchedMovieAdapter(onItemClickListener)
            adapter as SearchedMovieAdapter
        }
        searchedMovieAdapter.submitList(it)
    }
}

@BindingAdapter(value = ["app:loadSearchedPersonList", "app:onItemClickListener"])
fun RecyclerView.loadSearchedPersonList(searchedPersonData: SearchedPersonData?, onItemClickListener: OnHomeItemClickListener) {
    searchedPersonData?.personItem?.let {
        val searchedPersonAdapter = if (adapter != null) {
            adapter as SearchedPersonAdapter
        } else {
            adapter = SearchedPersonAdapter(onItemClickListener)
            adapter as SearchedPersonAdapter
        }
        searchedPersonAdapter.submitList(it)
    }
}

@BindingAdapter(value = ["app:loadPopularMovieList", "app:onItemClickListener"])
fun RecyclerView.loadPopularMovieList(popularMovieData: PopularMovieData?, onItemClickListener: OnHomeItemClickListener) {
    popularMovieData?.movieItem?.let {
        val movieAdapter = if (adapter != null) {
            adapter as MovieAdapter
        } else {
            adapter = MovieAdapter(onItemClickListener)
            adapter as MovieAdapter
        }
        movieAdapter.submitList(it)
    }
}

@BindingAdapter(value = ["app:loadCastList", "app:onItemClickListener"])
fun RecyclerView.loadCastList(castData: CastData?, onItemClickListener: OnCastItemClickListener) {
    castData?.castItem?.let {
        val movieCastAdapter = if (adapter != null) {
            adapter as MovieCastAdapter
        } else {
            adapter = MovieCastAdapter(onItemClickListener)
            adapter as MovieCastAdapter
        }
        movieCastAdapter.submitList(it)
    }
}