package com.fionicholas.moviecatalogue.core.presentation.search

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
import com.fionicholas.moviecatalogue.core.domain.search.model.Search
import com.fionicholas.moviecatalogue.core.presentation.search.SearchAdapter.SearchViewHolder
import com.fionicholas.moviecatalogue.core.utils.AppConstants
import com.fionicholas.moviecatalogue.core.utils.ext.formatRatingBar
import java.util.ArrayList

class SearchAdapter(
    val listener: ((data: Search) -> Unit)?
) : RecyclerView.Adapter<SearchViewHolder>() {

    private var data = ArrayList<Search>()

    fun setData(data: List<Search>?) {
        if (data == null) return
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val movie = data[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = data.size

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var title: TextView = itemView.findViewById(R.id.tvMovieTitle)
        private var rating: TextView = itemView.findViewById(R.id.tvMovieRating)
        private var poster: ImageView = itemView.findViewById(R.id.imgPoster)
        private var movieRating: RatingBar = itemView.findViewById(R.id.movieRating)

        fun bind(data: Search) {
            title.text = if (data.title.isEmpty()) data.name else data.title
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