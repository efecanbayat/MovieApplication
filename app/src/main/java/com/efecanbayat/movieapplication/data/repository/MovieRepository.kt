package com.efecanbayat.movieapplication.data.repository

import com.efecanbayat.movieapplication.data.model.response.home.MovieResponse
import com.efecanbayat.movieapplication.data.model.response.moviedetail.MovieCreditsResponse
import com.efecanbayat.movieapplication.data.model.response.moviedetail.MovieDetailResponse
import com.efecanbayat.movieapplication.data.model.response.moviedetail.MovieVideoResponse
import com.efecanbayat.movieapplication.data.model.response.persondetail.PersonCreditsResponse
import com.efecanbayat.movieapplication.data.model.response.persondetail.PersonDetailResponse
import com.efecanbayat.movieapplication.data.model.response.search.SearchResponse
import com.efecanbayat.movieapplication.utils.DataState
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(): Flow<DataState<MovieResponse>>
    suspend fun searchMovieAndPerson(query: String): Flow<DataState<SearchResponse>>
    suspend fun getMovie(movieId: Int): Flow<DataState<MovieDetailResponse>>
    suspend fun getMovieCredits(movieId: Int): Flow<DataState<MovieCreditsResponse>>
    suspend fun getMovieVideos(movieId: Int): Flow<DataState<MovieVideoResponse>>
    suspend fun getPerson(personId: Int): Flow<DataState<PersonDetailResponse>>
    suspend fun getPersonCredits(personId: Int): Flow<DataState<PersonCreditsResponse>>
}