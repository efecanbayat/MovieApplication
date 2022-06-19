package com.efecanbayat.movieapplication.data.remote.source.impl

import com.efecanbayat.movieapplication.data.base.BaseRemoteDataSource
import com.efecanbayat.movieapplication.data.model.response.home.MovieResponse
import com.efecanbayat.movieapplication.data.model.response.search.SearchResponse
import com.efecanbayat.movieapplication.data.remote.api.MovieService
import com.efecanbayat.movieapplication.data.remote.source.RemoteDataSource
import com.efecanbayat.movieapplication.utils.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val movieService: MovieService
) : RemoteDataSource, BaseRemoteDataSource() {
    override suspend fun getMovies(page: Int): Flow<DataState<MovieResponse>> {
        return getResult {
            movieService.getMovies(page)
        }
    }

    override suspend fun searchMovieAndPerson(query: String): Flow<DataState<SearchResponse>> {
        return getResult {
            movieService.searchMovieAndPerson(query)
        }
    }
}