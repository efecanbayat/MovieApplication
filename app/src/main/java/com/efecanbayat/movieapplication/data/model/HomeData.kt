package com.efecanbayat.movieapplication.data.model

import com.efecanbayat.movieapplication.ui.feature.home.MovieItemViewType

abstract class HomeData(val type: HomeDataType)

data class HomeDataHolder(
    var searchedMovieData: SearchedMovieData? = null,
    var searchedPersonData: SearchedPersonData? = null,
    var popularMovieData: PopularMovieData? = null,

    ) {
    fun prepareData(): ArrayList<HomeData> {
        return ArrayList<HomeData>().apply {
            searchedMovieData?.let { add(it) }
            searchedPersonData?.let { add(it) }
            popularMovieData?.let { add(it) }
        }
    }
}

data class SearchedMovieData(val movieItem: ArrayList<MovieItemViewType>?) : HomeData(HomeDataType.SEARCHED_MOVIE_LIST)

data class SearchedPersonData(val personItem: ArrayList<MovieItemViewType>?) : HomeData(HomeDataType.SEARCHED_PERSON_LIST)

data class PopularMovieData(val movieItem: ArrayList<MovieItemViewType>?) : HomeData(HomeDataType.POPULAR_MOVIE_LIST)

enum class HomeDataType {
    SEARCHED_MOVIE_LIST,
    SEARCHED_PERSON_LIST,
    POPULAR_MOVIE_LIST
}