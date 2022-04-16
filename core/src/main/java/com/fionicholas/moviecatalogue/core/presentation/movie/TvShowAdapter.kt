package com.fionicholas.moviecatalogue.core.presentation.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fionicholas.moviecatalogue.core.R
import com.fionicholas.moviecatalogue.core.domain.movie.model.response.TvShow
import com.fionicholas.moviecatalogue.core.presentation.movie.TvShowAdapter.TvShowViewHolder
import com.fionicholas.moviecatalogue.core.utils.AppConstants
import java.util.ArrayList

class TvShowAdapter(
    val listener: ((tvShow: TvShow) -> Unit)?
) : RecyclerView.Adapter<TvShowViewHolder>() {

    private var tvShows = ArrayList<TvShow>()

    fun setData(tvShows: List<TvShow>?) {
        if (tvShows == null) return
        this.tvShows.clear()
        this.tvShows.addAll(tvShows)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return TvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = tvShows[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = tvShows.size

    inner class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var title: TextView = itemView.findViewById(R.id.tvMovieTitle)
        private var rating: TextView = itemView.findViewById(R.id.tvMovieRating)
        private var poster: ImageView = itemView.findViewById(R.id.imgPoster)

        fun bind(data: TvShow) {
            title.text = data.title
            rating.text = data.voteAverage.toString()
            Glide.with(itemView.context).load(AppConstants.URL_IMAGE + data.posterPath)
                .placeholder(ContextCompat.getDrawable(itemView.context, R.drawable.bg_grey)).into(poster)

            itemView.setOnClickListener {
                listener?.invoke(data)
            }
        }
    }
}