package com.example.watchliste

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(private val movieList: MutableList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvMovieTitle)
        val tvGenre: TextView = view.findViewById(R.id.tvGenre)
        val tvRating: TextView = view.findViewById(R.id.tvRating)
        val btnDelete: ImageButton = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]

        holder.tvTitle.text = movie.title
        holder.tvGenre.text = movie.genre
        holder.tvRating.text = "⭐ ${movie.rating}"

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, MovieDetailsActivity::class.java).apply {
                putExtra("MOVIE_TITLE", movie.title)
                putExtra("MOVIE_GENRE", movie.genre)
                putExtra("MOVIE_RATING", movie.rating)
                putExtra("MOVIE_DESC", movie.description)
            }
            context.startActivity(intent)
        }

        holder.btnDelete.setOnClickListener {
            val currentPosition = holder.adapterPosition
            if (currentPosition != RecyclerView.NO_POSITION) {
                movieList.removeAt(currentPosition)
                notifyItemRemoved(currentPosition)
                // This keeps the list indices in sync after a deletion
                notifyItemRangeChanged(currentPosition, movieList.size)
            }
        }
    }

    override fun getItemCount() = movieList.size
}