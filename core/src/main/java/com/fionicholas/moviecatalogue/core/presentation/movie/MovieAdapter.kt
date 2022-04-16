package com.fionicholas.moviecatalogue.core.presentation.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fionicholas.moviecatalogue.core.R
import com.fionicholas.moviecatalogue.core.domain.movie.model.response.Movie
import com.fionicholas.moviecatalogue.core.presentation.movie.MovieAdapter.MovieViewHolder
import com.fionicholas.moviecatalogue.core.utils.AppConstants
import com.fionicholas.moviecatalogue.core.utils.ext.formatRatingBar
import java.util.ArrayList

class MovieAdapter(
    val listener: ((movie: Movie) -> Unit)?
) : RecyclerView.Adapter<MovieViewHolder>() {

    private var movies = ArrayList<Movie>()

    fun setMovies(movies: List<Movie>?) {
        if (movies == null) return
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movies.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var title: TextView = itemView.findViewById(R.id.tvMovieTitle)
        private var rating: TextView = itemView.findViewById(R.id.tvMovieRating)
        private var poster: ImageView = itemView.findViewById(R.id.imgPoster)
        private var movieRating: RatingBar = itemView.findViewById(R.id.movieRating)

        fun bind(data: Movie) {
            title.text = data.title
            rating.text = data.voteAverage.toString()
            Glide.with(itemView.context).load(AppConstants.URL_IMAGE + data.posterPath)
                .placeholder(ContextCompat.getDrawable(itemView.context, R.drawable.bg_grey)).into(poster)

            movieRating.rating = data.voteAverage.formatRatingBar()

            itemView.setOnClickListener {
                listener?.invoke(data)
            }
        }
    }
}