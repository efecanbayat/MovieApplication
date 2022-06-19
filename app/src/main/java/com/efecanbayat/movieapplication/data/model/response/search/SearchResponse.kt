package com.efecanbayat.movieapplication.data.model.response.search

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val searchResults: List<SearchResult?>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)