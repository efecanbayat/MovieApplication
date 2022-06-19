package com.efecanbayat.movieapplication.data.remote.api

import com.efecanbayat.movieapplication.data.model.response.home.MovieResponse
import com.efecanbayat.movieapplication.data.model.response.search.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")
    suspend fun getMovies(
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET("search/multi")
    suspend fun searchMovieAndPerson(
        @Query("query") query: String?
    ): Response<SearchResponse>
}