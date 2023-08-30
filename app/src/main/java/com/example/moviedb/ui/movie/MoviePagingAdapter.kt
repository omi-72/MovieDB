package com.example.moviedb.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.BR
import com.example.moviedb.data.MovieResponse
import com.example.moviedb.databinding.ViewHolderMovieBinding

class MoviePagingAdapter : PagingDataAdapter<MovieResponse,MoviePagingAdapter.MyViewHolder>(DIFF_UTIL) {

    var onCLick: ((String) -> Unit)? = null
    companion object{
        val DIFF_UTIL = object : DiffUtil.ItemCallback<MovieResponse>(){
            override fun areItemsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
                return oldItem.results==newItem.results
            }

            override fun areContentsTheSame(
                oldItem: MovieResponse,
                newItem: MovieResponse
            ): Boolean {
                return oldItem==newItem
            }

        }
    }
    fun onMovieClick(listener: (String) -> Unit) {
        onCLick = listener
    }

    inner class MyViewHolder(val viewDataBinding: ViewHolderMovieBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.viewDataBinding.setVariable(BR.movieResponse,data)

        holder.viewDataBinding.root.setOnClickListener {
            onCLick?.let {
                it(data?.imdbID!!)
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ViewHolderMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }
}