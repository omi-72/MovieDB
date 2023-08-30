package com.example.moviedb.ui.details

import com.example.moviedb.data.movieDetails.MovieDetailsMovieDetails
import com.example.moviedb.remote.MovieInterface
import com.example.moviedb.utils.Constants
import com.example.moviedb.utils.Status

class MovieDetailsRepository(private val movieInterface: MovieInterface) {


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