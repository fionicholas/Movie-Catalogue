package com.fionicholas.moviecatalogue.favorite.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fionicholas.moviecatalogue.core.domain.movie.model.response.Movie
import com.fionicholas.moviecatalogue.core.presentation.favorite.FavoriteViewModel
import com.fionicholas.moviecatalogue.core.presentation.movie.MovieAdapter
import com.fionicholas.moviecatalogue.core.utils.ext.showDefaultState
import com.fionicholas.moviecatalogue.core.utils.ext.showEmptyState
import com.fionicholas.moviecatalogue.core.utils.ext.showLoadingState
import com.fionicholas.moviecatalogue.favorite.R
import com.fionicholas.moviecatalogue.presentation.detail.MovieDetailActivity
import com.kennyc.view.MultiStateView
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteMoviesFragment : Fragment() {

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter(::onMovieSelected)
    }

    private lateinit var rvFavoriteMovies: RecyclerView
    private lateinit var msvFavoriteMovie: MultiStateView

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvFavoriteMovies = view.findViewById(R.id.rvFavoriteMovies)
        msvFavoriteMovie = view.findViewById(R.id.msvFavoriteMovie)

        initObserver()

        rvFavoriteMovies.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movieAdapter
        }

        getFavoriteMovies()
    }

    override fun onResume() {
        super.onResume()
        getFavoriteMovies()
    }

    private fun getFavoriteMovies() {
        favoriteViewModel.getFavoriteMovies()
    }

    private fun initObserver() {
        favoriteViewModel.favoriteMovies.observe(viewLifecycleOwner, {
            when (it) {
                is com.fionicholas.moviecatalogue.core.utils.network.Result.Loading -> {
                    msvFavoriteMovie.showLoadingState()
                }
                is com.fionicholas.moviecatalogue.core.utils.network.Result.Failure -> {
                    msvFavoriteMovie.showEmptyState(emptyMessage = it.message)
                }
                is com.fionicholas.moviecatalogue.core.utils.network.Result.Empty -> {
                    msvFavoriteMovie.showEmptyState(
                        title = getString(R.string.title_empty),
                        emptyMessage = getString(R.string.message_favorite_movie_not_found)
                    )
                }
                is com.fionicholas.moviecatalogue.core.utils.network.Result.Success -> {
                    msvFavoriteMovie.showDefaultState()
                    setMovieData(it.data)
                }
                else -> {
                }
            }
        })
    }

    private fun onMovieSelected(movie: Movie) {
        MovieDetailActivity.start(requireContext(), movie)
    }

    private fun setMovieData(data: List<Movie>) {
        movieAdapter.setMovies(data)
    }
}