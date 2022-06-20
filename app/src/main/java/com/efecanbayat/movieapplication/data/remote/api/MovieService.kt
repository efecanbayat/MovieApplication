package com.efecanbayat.movieapplication.data.remote.api

import com.efecanbayat.movieapplication.data.model.response.home.MovieResponse
import com.efecanbayat.movieapplication.data.model.response.movieDetail.MovieCreditsResponse
import com.efecanbayat.movieapplication.data.model.response.movieDetail.MovieDetailResponse
import com.efecanbayat.movieapplication.data.model.response.movieDetail.MovieVideoResponse
import com.efecanbayat.movieapplication.data.model.response.personDetail.PersonCreditsResponse
import com.efecanbayat.movieapplication.data.model.response.personDetail.PersonDetailResponse
import com.efecanbayat.movieapplication.data.model.response.search.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") movie_id: Int
    ): Response<MovieDetailResponse>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movie_id: Int
    ): Response<MovieCreditsResponse>

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movie_id: Int
    ): Response<MovieVideoResponse>

    @GET("person/{person_id}")
    suspend fun getPerson(
        @Path("person_id") person_id: Int
    ): Response<PersonDetailResponse>

    @GET("person/{person_id}/combined_credits")
    suspend fun getPersonCredits(
        @Path("person_id") person_id: Int
    ): Response<PersonCreditsResponse>
}