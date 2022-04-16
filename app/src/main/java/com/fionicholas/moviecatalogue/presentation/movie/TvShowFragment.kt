package com.fionicholas.moviecatalogue.presentation.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fionicholas.moviecatalogue.R
import com.fionicholas.moviecatalogue.core.domain.movie.model.response.TvShow
import com.fionicholas.moviecatalogue.core.presentation.movie.TvShowAdapter
import com.fionicholas.moviecatalogue.core.utils.ext.showDefaultState
import com.fionicholas.moviecatalogue.core.utils.ext.showErrorState
import com.fionicholas.moviecatalogue.core.utils.ext.showLoadingState
import com.fionicholas.moviecatalogue.core.utils.network.Result
import com.fionicholas.moviecatalogue.presentation.detail.TvShowDetailActivity
import com.kennyc.view.MultiStateView
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {

    private val tvShowAdapter: TvShowAdapter by lazy {
        TvShowAdapter(::onTvShowSelected)
    }

    private lateinit var rvTvShow: RecyclerView
    private lateinit var msvTvShow: MultiStateView

    private val movieViewModel: MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvTvShow = view.findViewById(R.id.rvTvShow)
        msvTvShow = view.findViewById(R.id.msvTvShow)

        initObserver()

        rvTvShow.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = tvShowAdapter
        }

        getTvShows()
    }

    private fun getTvShows() {
        movieViewModel.getTvShows()
    }

    private fun initObserver() {
        movieViewModel.tvShows.observe(viewLifecycleOwner, {
            when (it) {
                is Result.Loading -> {
                    msvTvShow.showLoadingState()
                }
                is Result.Failure -> {
                    msvTvShow.showErrorState(errorMessage = it.message.toString())
                }
                is Result.Success -> {
                    msvTvShow.showDefaultState()
                    setTvShowData(it.data)
                }
                else -> {
                }
            }
        })
    }

    private fun onTvShowSelected(tvShow: TvShow) {
        TvShowDetailActivity.start(requireContext(), tvShow)
    }

    private fun setTvShowData(data: List<TvShow>) {
        tvShowAdapter.setData(data)
    }
}