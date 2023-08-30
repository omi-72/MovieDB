package com.example.moviedb.data

data class MovieResponse(
    val Response: String,
    val Search: List<Serach>,
    val totalResults: String
)