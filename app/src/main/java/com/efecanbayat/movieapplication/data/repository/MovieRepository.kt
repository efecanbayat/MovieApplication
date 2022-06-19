package com.efecanbayat.movieapplication.data.repository

import com.efecanbayat.movieapplication.data.model.response.home.MovieResponse
import com.efecanbayat.movieapplication.data.model.response.movieDetail.MovieCreditsResponse
import com.efecanbayat.movieapplication.data.model.response.movieDetail.MovieDetailResponse
import com.efecanbayat.movieapplication.data.model.response.movieDetail.MovieVideoResponse
import com.efecanbayat.movieapplication.data.model.response.search.SearchResponse
import com.efecanbayat.movieapplication.utils.DataState
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(page: Int): Flow<DataState<MovieResponse>>
    suspend fun searchMovieAndPerson(query: String): Flow<DataState<SearchResponse>>
    suspend fun getMovie(movieId: Int): Flow<DataState<MovieDetailResponse>>
    suspend fun getMovieCredits(movieId: Int): Flow<DataState<MovieCreditsResponse>>
    suspend fun getMovieVideos(movieId: Int): Flow<DataState<MovieVideoResponse>>
}