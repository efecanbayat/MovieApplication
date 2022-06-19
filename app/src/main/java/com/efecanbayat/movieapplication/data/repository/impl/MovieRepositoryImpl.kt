package com.efecanbayat.movieapplication.data.repository.impl

import com.efecanbayat.movieapplication.data.model.response.home.MovieResponse
import com.efecanbayat.movieapplication.data.model.response.search.SearchResponse
import com.efecanbayat.movieapplication.data.remote.source.RemoteDataSource
import com.efecanbayat.movieapplication.data.repository.MovieRepository
import com.efecanbayat.movieapplication.utils.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {
    override suspend fun getMovies(page: Int): Flow<DataState<MovieResponse>> {
        return remoteDataSource.getMovies(page)
    }

    override suspend fun searchMovieAndPerson(query: String): Flow<DataState<SearchResponse>> {
        return remoteDataSource.searchMovieAndPerson(query)
    }
}