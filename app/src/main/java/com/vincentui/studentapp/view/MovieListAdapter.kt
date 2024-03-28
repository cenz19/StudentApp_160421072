package com.vincentui.studentapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vincentui.studentapp.databinding.MovieListItemBinding
import com.vincentui.studentapp.model.Movie
import com.vincentui.studentapp.model.Student

class MovieListAdapter(val movieList:ArrayList<Movie>):
RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>(){
    class MovieViewHolder(var binding: MovieListItemBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binding.txtTitle.text = movieList[position].title
        val actorsString = movieList[position].actors?.joinToString("\n", "")
        holder.binding.txtActors.text = actorsString

        holder.binding.txtGenre.text = movieList[position].details?.genre
        holder.binding.txtDirector.text = movieList[position].details?.director
        holder.binding.txtYear.text = movieList[position].details?.year.toString()
        holder.binding.txtRating.text = movieList[position].details?.rating.toString()
    }

    fun updateMovieList(newStudentList: ArrayList<Movie>) {
        movieList.clear()
        movieList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}