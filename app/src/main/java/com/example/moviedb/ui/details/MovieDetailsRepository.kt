package com.example.moviedb.ui.details

import com.example.moviedb.data.movieDetails.MovieDetailsMovieDetails

class MovieDetailsRepository(private val movieInterface: MovieInterface) {


    suspend fun getMovieDetails(imdbId: String): Result<MovieDetailsMovieDetails> {


        return try {

            val response = movieInterface.getMovieDetails(imdbId, Constants.API_KEY)
            if (response.isSuccessful) {

                Result(Status.SUCCESS, response.body(), null)

            } else {
                Result(Status.ERROR, null, null)
            }


        } catch (e: Exception) {
            Result(Status.ERROR, null, null)
        }


    }


}