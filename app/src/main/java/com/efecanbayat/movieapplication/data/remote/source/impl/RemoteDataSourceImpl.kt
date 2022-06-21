package com.efecanbayat.movieapplication.data.remote.source.impl

import com.efecanbayat.movieapplication.data.base.BaseRemoteDataSource
import com.efecanbayat.movieapplication.data.model.response.home.MovieResponse
import com.efecanbayat.movieapplication.data.model.response.moviedetail.MovieCreditsResponse
import com.efecanbayat.movieapplication.data.model.response.moviedetail.MovieDetailResponse
import com.efecanbayat.movieapplication.data.model.response.moviedetail.MovieVideoResponse
import com.efecanbayat.movieapplication.data.model.response.persondetail.PersonCreditsResponse
import com.efecanbayat.movieapplication.data.model.response.persondetail.PersonDetailResponse
import com.efecanbayat.movieapplication.data.model.response.search.SearchResponse
import com.efecanbayat.movieapplication.data.remote.api.MovieService
import com.efecanbayat.movieapplication.data.remote.source.RemoteDataSource
import com.efecanbayat.movieapplication.utils.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val movieService: MovieService
) : RemoteDataSource, BaseRemoteDataSource() {
    override suspend fun getMovies(): Flow<DataState<MovieResponse>> {
        return getResult {
            movieService.getMovies()
        }
    }

    override suspend fun searchMovieAndPerson(query: String): Flow<DataState<SearchResponse>> {
        return getResult {
            movieService.searchMovieAndPerson(query)
        }
    }

    override suspend fun getMovie(movieId: Int): Flow<DataState<MovieDetailResponse>> {
        return getResult {
            movieService.getMovie(movieId)
        }
    }

    override suspend fun getMovieCredits(movieId: Int): Flow<DataState<MovieCreditsResponse>> {
        return getResult {
            movieService.getMovieCredits(movieId)
        }
    }

    override suspend fun getMovieVideos(movieId: Int): Flow<DataState<MovieVideoResponse>> {
        return getResult {
            movieService.getMovieVideos(movieId)
        }
    }

    override suspend fun getPerson(personId: Int): Flow<DataState<PersonDetailResponse>> {
        return getResult {
            movieService.getPerson(personId)
        }
    }

    override suspend fun getPersonCredits(personId: Int): Flow<DataState<PersonCreditsResponse>> {
        return getResult {
            movieService.getPersonCredits(personId)
        }
    }
}