package com.efecanbayat.movieapplication.data.repository

import com.efecanbayat.movieapplication.data.model.response.home.MovieResponse
import com.efecanbayat.movieapplication.data.model.response.search.SearchResponse
import com.efecanbayat.movieapplication.utils.DataState
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(page: Int): Flow<DataState<MovieResponse>>
    suspend fun searchMovieAndPerson(query: String): Flow<DataState<SearchResponse>>
}