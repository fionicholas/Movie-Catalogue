package com.fionicholas.moviecatalogue.favorite.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fionicholas.moviecatalogue.core.domain.movie.model.response.TvShow
import com.fionicholas.moviecatalogue.core.presentation.favorite.FavoriteViewModel
import com.fionicholas.moviecatalogue.core.presentation.movie.TvShowAdapter
import com.fionicholas.moviecatalogue.core.utils.ext.showDefaultState
import com.fionicholas.moviecatalogue.core.utils.ext.showEmptyState
import com.fionicholas.moviecatalogue.core.utils.ext.showLoadingState
import com.fionicholas.moviecatalogue.favorite.R
import com.fionicholas.moviecatalogue.presentation.detail.TvShowDetailActivity
import com.kennyc.view.MultiStateView
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteTvShowsFragment : Fragment() {

    private val tvShowAdapter: TvShowAdapter by lazy {
        TvShowAdapter(::onMovieSelected)
    }

    private lateinit var rvFavoriteTvShows: RecyclerView
    private lateinit var msvFavoriteTvShow: MultiStateView

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv_shows, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvFavoriteTvShows = view.findViewById(R.id.rvFavoriteTvShows)
        msvFavoriteTvShow = view.findViewById(R.id.msvFavoriteTvShow)

        initObserver()

        rvFavoriteTvShows.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = tvShowAdapter
        }

        getFavoriteTvShows()
    }

    private fun getFavoriteTvShows() {
        favoriteViewModel.getFavoriteTvShows()
    }

    override fun onResume() {
        super.onResume()
        getFavoriteTvShows()
    }

    private fun initObserver() {
        favoriteViewModel.favoriteTvShows.observe(viewLifecycleOwner, {
            when (it) {
                is com.fionicholas.moviecatalogue.core.utils.network.Result.Loading -> {
                    msvFavoriteTvShow.showLoadingState()
                }
                is com.fionicholas.moviecatalogue.core.utils.network.Result.Failure -> {
                    msvFavoriteTvShow.showEmptyState(emptyMessage = it.message)
                }
                is com.fionicholas.moviecatalogue.core.utils.network.Result.Empty -> {
                    msvFavoriteTvShow.showEmptyState(
                        title = getString(R.string.title_empty),
                        emptyMessage = getString(R.string.message_favorite_tv_show_not_found)
                    )
                }
                is com.fionicholas.moviecatalogue.core.utils.network.Result.Success -> {
                    msvFavoriteTvShow.showDefaultState()
                    setTvShowData(it.data)
                }
                else -> {
                }
            }
        })
    }

    private fun onMovieSelected(tvShow: TvShow) {
        TvShowDetailActivity.start(requireContext(), tvShow)
    }

    private fun setTvShowData(data: List<TvShow>) {
        tvShowAdapter.setData(data)
    }
}