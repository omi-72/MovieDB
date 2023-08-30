package com.example.moviedb.remote

import com.example.moviedb.data.MovieResponse
import com.example.moviedb.data.movieDetails.MovieDetailsMovieDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieInterface {

    @GET("/")
    suspend fun getAllMovies(
        @Query("s")s:String,
        @Query("page")page:Int,
        @Query("apiKey")apiKey:String
    ):retrofit2.Response<MovieResponse>

    @GET("/")
    suspend fun getMovieDetails(
        @Query("i") imdbId: String,
        @Query("apiKey") apiKey: String
    ):Response<MovieDetailsMovieDetails>

}
