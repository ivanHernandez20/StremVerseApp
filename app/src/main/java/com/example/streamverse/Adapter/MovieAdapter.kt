package com.example.streamverse.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.streamverse.DetailActivity
import com.example.streamverse.Model.Movie
import com.example.streamverse.R
import com.example.streamverse.databinding.ItemMovieBinding


class MovieAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){
    class MovieViewHolder(val binding: ItemMovieBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.binding.mTitle.text = movie.title
        holder.binding.mDescripcion.text = movie.shortDescription

        Glide.with(holder.itemView.context).load(movie.image).into(holder.binding.imgMovie)
        holder.binding.btnVerMas.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("movie", movie)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = movies.size
}