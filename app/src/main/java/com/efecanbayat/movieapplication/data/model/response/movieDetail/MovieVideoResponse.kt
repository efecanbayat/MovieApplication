package com.efecanbayat.movieapplication.data.model.response.movieDetail


import com.google.gson.annotations.SerializedName

data class MovieVideoResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("results")
    val results: List<Result?>?
)