package com.fionicholas.moviecatalogue.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.fionicholas.moviecatalogue.R
import com.fionicholas.moviecatalogue.core.base.BaseActivity
import com.fionicholas.moviecatalogue.core.domain.movie.model.response.TvShow
import com.fionicholas.moviecatalogue.core.presentation.favorite.FavoriteViewModel
import com.fionicholas.moviecatalogue.core.utils.AppConstants
import com.fionicholas.moviecatalogue.core.utils.BundleKeys
import com.fionicholas.moviecatalogue.core.utils.ext.formatRatingBar
import com.fionicholas.moviecatalogue.core.utils.ext.toDisplayFormat
import com.fionicholas.moviecatalogue.core.utils.network.Result
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowDetailActivity : BaseActivity() {

    private val tvShow: TvShow? by lazy { intent.getParcelableExtra(BundleKeys.TV_SHOW_DETAIL) as TvShow? }

    private lateinit var tvTitle: TextView
    private lateinit var tvRating: TextView
    private lateinit var tvOverview: TextView
    private lateinit var tvReleaseDate: TextView
    private lateinit var imgBackdropImage: ImageView
    private lateinit var imgPoster: ImageView
    private lateinit var movieRating: RatingBar
    private lateinit var favoriteButton: FloatingActionButton

    private var isFavorite: Boolean = false

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_show_detail)

        tvTitle = findViewById(R.id.tvMovieTitle)
        tvRating = findViewById(R.id.tvMovieRating)
        tvOverview = findViewById(R.id.tvOverview)
        imgBackdropImage = findViewById(R.id.imgBackdropImage)
        imgPoster = findViewById(R.id.imgPoster)
        tvReleaseDate = findViewById(R.id.tvReleaseDate)
        movieRating = findViewById(R.id.movieRating)
        favoriteButton = findViewById(R.id.favoriteButton)

        setupToolbar(title = getString(R.string.label_tv_show_detail), isChild = true)

        setTvShowDetail()

        initObserver()

        tvShow?.id?.let { getFavoriteTvShowById(it) }

        favoriteButton.setOnClickListener {
            if (isFavorite) {
                tvShow?.id?.let { id -> deleteFavoriteTvShow(id) }
            } else {
                tvShow?.let { insertFavoriteMovie(it) }
            }
        }
    }

    private fun setTvShowDetail() {
        tvTitle.text = tvShow?.title
        tvRating.text = tvShow?.voteAverage.toString()
        tvOverview.text = tvShow?.overview
        tvReleaseDate.text = tvShow?.releaseDate?.toDisplayFormat()
        movieRating.rating = tvShow?.voteAverage.formatRatingBar()
        Glide.with(this).load(AppConstants.URL_IMAGE + tvShow?.posterPath)
            .placeholder(ContextCompat.getDrawable(this, R.drawable.bg_grey)).into(imgPoster)
        Glide.with(this).load(AppConstants.URL_IMAGE + tvShow?.backdropPath)
            .placeholder(ContextCompat.getDrawable(this, R.drawable.bg_grey)).into(imgBackdropImage)
    }

    private fun getFavoriteTvShowById(id: Int) {
        favoriteViewModel.getFavoriteTvShowById(id)
    }

    private fun insertFavoriteMovie(tvShow: TvShow) {
        favoriteViewModel.insertFavoriteTvShow(tvShow)
    }

    private fun deleteFavoriteTvShow(id: Int) {
        favoriteViewModel.deleteFavoriteTvShow(id)
    }

    private fun initObserver() {
        favoriteViewModel.insertFavoriteTvShow.observe(this, {
            when (it) {
                is Result.Loading -> {
                }
                is Result.Failure -> {
                    Log.d(AppConstants.MOVIE_TAG, it.message.toString())
                    isFavorite = false
                }
                is Result.Success -> {
                    Toast.makeText(this, getString(R.string.message_favorite_success), Toast.LENGTH_SHORT).show()
                    favoriteButton.setImageResource(R.drawable.ic_favorite_filled)
                    isFavorite = true
                }
                else -> {
                }
            }
        })

        favoriteViewModel.deleteFavoriteTvShow.observe(this, {
            when (it) {
                is Result.Loading -> {
                }
                is Result.Failure -> {
                    Log.d(AppConstants.MOVIE_TAG, it.message.toString())
                }
                is Result.Success -> {
                    Toast.makeText(this, getString(R.string.message_success_remove_favorite), Toast.LENGTH_SHORT)
                        .show()
                    favoriteButton.setImageResource(R.drawable.ic_favorite_border)
                    isFavorite = false
                }
                else -> {
                }
            }
        })

        favoriteViewModel.favoriteTvShowById.observe(this, {
            when (it) {
                is Result.Loading -> {
                }
                is Result.Failure -> {
                    isFavorite = false
                    Log.d(AppConstants.MOVIE_TAG, it.message.toString())
                }
                is Result.Success -> {
                    isFavorite = true
                    favoriteButton.setImageResource(R.drawable.ic_favorite_filled)
                }
                else -> {
                }
            }
        })
    }

    companion object {

        fun start(context: Context, tvShow: TvShow) {
            val intent = Intent(context, TvShowDetailActivity::class.java)
                .putExtra(BundleKeys.TV_SHOW_DETAIL, tvShow)
            context.startActivity(intent)
        }
    }
}