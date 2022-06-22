package com.efecanbayat.movieapplication.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.efecanbayat.movieapplication.R
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
import com.efecanbayat.movieapplication.ui.feature.home.decoration.PopularMovieItemDecoration
import com.efecanbayat.movieapplication.ui.feature.home.decoration.SearchedItemDecoration
import com.efecanbayat.movieapplication.ui.feature.moviedetail.CastData
import com.efecanbayat.movieapplication.ui.feature.moviedetail.OnCastItemClickListener
import com.efecanbayat.movieapplication.ui.feature.moviedetail.adapter.MovieCastAdapter
import com.efecanbayat.movieapplication.ui.feature.moviedetail.decoration.CastItemDecoration
import com.efecanbayat.movieapplication.ui.feature.persondetail.CreditsData
import com.efecanbayat.movieapplication.ui.feature.persondetail.adapter.PersonCreditsAdapter
import com.efecanbayat.movieapplication.ui.feature.persondetail.decoration.CreditsItemDecoration

@BindingAdapter("imageUrl")
fun ImageView.loadUrlImage(url: String?) {
    Glide.with(this)
        .load(if (url?.isNotEmpty() == true) "${Constants.BASE_IMAGE_URL}$url" else null)
        .into(this)
}

@BindingAdapter(value = ["app:loadHomeData", "app:onItemClickListener"])
fun RecyclerView.loadHomeData(homeDataHolder: HomeDataHolder?, onItemClickListener: OnHomeItemClickListener) {
    homeDataHolder?.let {
        val homeAdapter = if (adapter != null) {
            adapter as HomeAdapter
        } else {
            adapter = HomeAdapter(onItemClickListener)
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

    if (itemDecorationCount == 0) {
        addItemDecoration(SearchedItemDecoration(context.resources.getDimension(R.dimen.dimen_medium).toInt()))
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

    if (itemDecorationCount == 0) {
        addItemDecoration(SearchedItemDecoration(context.resources.getDimension(R.dimen.dimen_medium).toInt()))
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

    if (itemDecorationCount == 0) {
        addItemDecoration(PopularMovieItemDecoration(context.resources.getDimension(R.dimen.dimen_medium).toInt()))
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

    if (itemDecorationCount == 0) {
        addItemDecoration(CastItemDecoration(context.resources.getDimension(R.dimen.dimen_small).toInt()))
    }
}

@BindingAdapter(value = ["app:loadCreditList"])
fun RecyclerView.loadCreditList(creditsData: CreditsData?) {
    creditsData?.creditsItem?.let {
        val personCreditsAdapter = if (adapter != null) {
            adapter as PersonCreditsAdapter
        } else {
            adapter = PersonCreditsAdapter()
            adapter as PersonCreditsAdapter
        }
        personCreditsAdapter.submitList(it)
    }

    if (itemDecorationCount == 0) {
        addItemDecoration(CreditsItemDecoration(context.resources.getDimension(R.dimen.dimen_medium).toInt()))
    }
}