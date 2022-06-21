package com.efecanbayat.movieapplication.data.repository.impl

import com.efecanbayat.movieapplication.data.model.response.home.MovieResponse
import com.efecanbayat.movieapplication.data.model.response.moviedetail.MovieCreditsResponse
import com.efecanbayat.movieapplication.data.model.response.moviedetail.MovieDetailResponse
import com.efecanbayat.movieapplication.data.model.response.moviedetail.MovieVideoResponse
import com.efecanbayat.movieapplication.data.model.response.persondetail.PersonCreditsResponse
import com.efecanbayat.movieapplication.data.model.response.persondetail.PersonDetailResponse
import com.efecanbayat.movieapplication.data.model.response.search.SearchResponse
import com.efecanbayat.movieapplication.data.remote.source.RemoteDataSource
import com.efecanbayat.movieapplication.data.repository.MovieRepository
import com.efecanbayat.movieapplication.utils.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {
    override suspend fun getMovies(): Flow<DataState<MovieResponse>> {
        return remoteDataSource.getMovies()
    }

    override suspend fun searchMovieAndPerson(query: String): Flow<DataState<SearchResponse>> {
        return remoteDataSource.searchMovieAndPerson(query)
    }

    override suspend fun getMovie(movieId: Int): Flow<DataState<MovieDetailResponse>> {
        return remoteDataSource.getMovie(movieId)
    }

    override suspend fun getMovieCredits(movieId: Int): Flow<DataState<MovieCreditsResponse>> {
        return remoteDataSource.getMovieCredits(movieId)
    }

    override suspend fun getMovieVideos(movieId: Int): Flow<DataState<MovieVideoResponse>> {
        return remoteDataSource.getMovieVideos(movieId)
    }

    override suspend fun getPerson(personId: Int): Flow<DataState<PersonDetailResponse>> {
        return remoteDataSource.getPerson(personId)
    }

    override suspend fun getPersonCredits(personId: Int): Flow<DataState<PersonCreditsResponse>> {
        return remoteDataSource.getPersonCredits(personId)
    }
}