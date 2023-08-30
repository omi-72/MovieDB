package com.example.moviedb.ui.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviedb.data.Result
import com.example.moviedb.remote.MovieInterface
import com.example.moviedb.utils.Constants

class MoviePaging( val s:String, val movieInterface: MovieInterface) : PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state?.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1)?:anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val page = params.key ?: 1

        return try {
            val data = movieInterface.getAllMovies(s, page, Constants.API_KEY)

            LoadResult.Page(
                data = data.body()?.results!!
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (data.body()?.results?.isEmpty()!!) null else page + 1
            )


        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}