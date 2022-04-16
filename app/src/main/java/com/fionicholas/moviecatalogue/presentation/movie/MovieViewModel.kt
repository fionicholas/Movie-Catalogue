package com.fionicholas.moviecatalogue.presentation.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fionicholas.moviecatalogue.core.domain.movie.MovieUseCase
import com.fionicholas.moviecatalogue.core.domain.movie.model.response.Movie
import com.fionicholas.moviecatalogue.core.domain.movie.model.response.TvShow
import com.fionicholas.moviecatalogue.core.utils.AppConstants
import com.fionicholas.moviecatalogue.core.utils.network.Result
import com.fionicholas.moviecatalogue.core.utils.network.genericErrorHandler
import com.fionicholas.moviecatalogue.core.utils.rx.addTo
import com.fionicholas.moviecatalogue.core.utils.rx.singleScheduler
import io.reactivex.disposables.CompositeDisposable

class MovieViewModel(private val useCase: MovieUseCase, private val disposable: CompositeDisposable) :
    ViewModel() {

    val movies: LiveData<Result<List<Movie>>> by lazy { _movies }
    val tvShows: LiveData<Result<List<TvShow>>> by lazy { _tvShows }

    private val _movies = MutableLiveData<Result<List<Movie>>>()
    private val _tvShows = MutableLiveData<Result<List<TvShow>>>()

    init {
        _movies.value = Result.default()
        _tvShows.value = Result.default()
    }

    fun getMovies() {
        _movies.value = Result.loading()

        useCase.getMovies(apiKey = AppConstants.API_KEY, language = AppConstants.LANG)
            .compose(singleScheduler())
            .subscribe({
                if (it.isEmpty()) {
                    _movies.value = Result.empty()
                } else {
                    _movies.value = Result.success(it)
                }
            }, { genericErrorHandler(it, _movies) })
            .addTo(disposable)
    }

    fun getTvShows() {
        _tvShows.value = Result.loading()

        useCase.getTvShows(apiKey = AppConstants.API_KEY, language = AppConstants.LANG)
            .compose(singleScheduler())
            .subscribe({
                if (it.isEmpty()) {
                    _tvShows.value = Result.empty()
                } else {
                    _tvShows.value = Result.success(it)
                }
            }, { genericErrorHandler(it, _tvShows) })
            .addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }
}