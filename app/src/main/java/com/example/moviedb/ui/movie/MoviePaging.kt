package com.example.moviedb.ui.movie

import android.graphics.Movie
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviedb.data.MovieResponse
import com.example.moviedb.data.Rating
import com.example.moviedb.remote.MovieInterface
import com.example.moviedb.utils.Constants

class MoviePaging( val s:String, val movieInterface: MovieInterface) : PagingSource<Int,Rating>() {
    override fun getRefreshKey(state: PagingState<Int, Rating>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state?.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1)?:anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Rating> {
        val page = params.key ?: 1

        return try {
            val data = movieInterface.getAllMovies(s, page, Constants.API_KEY)

            LoadResult.Page(
                data = data.body()?.Ratings!!,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (data.body()?.Ratings?.isEmpty()!!) null else page + 1
            )


        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}