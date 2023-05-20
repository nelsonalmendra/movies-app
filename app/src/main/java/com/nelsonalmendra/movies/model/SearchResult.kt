package com.nelsonalmendra.movies.model

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("Search")
    val movies: List<Movie>
)
