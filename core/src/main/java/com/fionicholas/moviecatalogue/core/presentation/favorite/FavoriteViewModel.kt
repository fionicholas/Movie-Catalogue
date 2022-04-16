package com.fionicholas.moviecatalogue.core.presentation.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fionicholas.moviecatalogue.core.domain.favorite.FavoriteUseCase
import com.fionicholas.moviecatalogue.core.domain.movie.model.response.Movie
import com.fionicholas.moviecatalogue.core.domain.movie.model.response.TvShow
import com.fionicholas.moviecatalogue.core.utils.network.Result
import com.fionicholas.moviecatalogue.core.utils.network.genericErrorHandler
import com.fionicholas.moviecatalogue.core.utils.rx.addTo
import com.fionicholas.moviecatalogue.core.utils.rx.completableScheduler
import com.fionicholas.moviecatalogue.core.utils.rx.singleScheduler
import io.reactivex.disposables.CompositeDisposable

class FavoriteViewModel(private val useCase: FavoriteUseCase, private val disposable: CompositeDisposable) :
    ViewModel() {

    val favoriteMovies: LiveData<Result<List<Movie>>> by lazy { _favoriteMovies }
    val favoriteTvShows: LiveData<Result<List<TvShow>>> by lazy { _favoriteTvShows }
    val favoriteMovieById: LiveData<Result<Movie>> by lazy { _favoriteMovieById }
    val favoriteTvShowById: LiveData<Result<TvShow>> by lazy { _favoriteTvShowById }
    val insertFavoriteMovie: LiveData<Result<Unit>> by lazy { _insertFavoriteMovie }
    val insertFavoriteTvShow: LiveData<Result<Unit>> by lazy { _insertFavoriteTvShow }
    val deleteFavoriteMovie: LiveData<Result<Unit>> by lazy { _deleteFavoriteMovie }
    val deleteFavoriteTvShow: LiveData<Result<Unit>> by lazy { _deleteFavoriteTvShow }

    private val _favoriteMovies = MutableLiveData<Result<List<Movie>>>()
    private val _favoriteTvShows = MutableLiveData<Result<List<TvShow>>>()
    private val _favoriteMovieById = MutableLiveData<Result<Movie>>()
    private val _favoriteTvShowById = MutableLiveData<Result<TvShow>>()
    private val _insertFavoriteMovie = MutableLiveData<Result<Unit>>()
    private val _insertFavoriteTvShow = MutableLiveData<Result<Unit>>()
    private val _deleteFavoriteMovie = MutableLiveData<Result<Unit>>()
    private val _deleteFavoriteTvShow = MutableLiveData<Result<Unit>>()

    init {
        _favoriteMovies.value = Result.default()
        _favoriteTvShows.value = Result.default()
        _favoriteMovieById.value = Result.default()
        _favoriteTvShowById.value = Result.default()
        _insertFavoriteMovie.value = Result.default()
        _insertFavoriteTvShow.value = Result.default()
        _deleteFavoriteMovie.value = Result.default()
        _deleteFavoriteTvShow.value = Result.default()
    }

    fun getFavoriteMovies() {
        _favoriteMovies.value = Result.loading()

        useCase.getFavoriteMovies()
            .compose(singleScheduler())
            .subscribe({
                if (it.isEmpty()) {
                    _favoriteMovies.value = Result.empty()
                } else {
                    _favoriteMovies.value = Result.success(it)
                }
            }, { genericErrorHandler(it, _favoriteMovies) })
            .addTo(disposable)
    }

    fun getFavoriteTvShowById(id: Int) {
        _favoriteTvShowById.value = Result.loading()

        useCase.getFavoriteTvShowById(id)
            .compose(singleScheduler())
            .subscribe({
                _favoriteTvShowById.value = Result.success(it)
            }, { genericErrorHandler(it, _favoriteTvShowById) })
            .addTo(disposable)
    }

    fun getFavoriteMovieById(id: Int) {
        _favoriteMovieById.value = Result.loading()

        useCase.getFavoriteMovieById(id)
            .compose(singleScheduler())
            .subscribe({
                _favoriteMovieById.value = Result.success(it)
            }, { genericErrorHandler(it, _favoriteMovieById) })
            .addTo(disposable)
    }

    fun getFavoriteTvShows() {
        _favoriteTvShows.value = Result.loading()

        useCase.getFavoriteTvShows()
            .compose(singleScheduler())
            .subscribe({
                if (it.isEmpty()) {
                    _favoriteTvShows.value = Result.empty()
                } else {
                    _favoriteTvShows.value = Result.success(it)
                }
            }, { genericErrorHandler(it, _favoriteTvShows) })
            .addTo(disposable)
    }

    fun insertFavoriteMovie(movie: Movie) {
        _insertFavoriteMovie.value = Result.loading()

        useCase.insertFavoriteMovie(movie)
            .compose(completableScheduler<Unit>())
            .subscribe({
                _insertFavoriteMovie.value = Result.success(Unit)

            }, { genericErrorHandler(it, _insertFavoriteMovie) })
            .addTo(disposable)
    }

    fun insertFavoriteTvShow(tvShow: TvShow) {
        _insertFavoriteTvShow.value = Result.loading()

        useCase.insertFavoriteTvShow(tvShow)
            .compose(completableScheduler<Unit>())
            .subscribe({
                _insertFavoriteTvShow.value = Result.success(Unit)

            }, { genericErrorHandler(it, _insertFavoriteTvShow) })
            .addTo(disposable)
    }

    fun deleteFavoriteMovie(id: Int) {
        _deleteFavoriteMovie.value = Result.loading()

        useCase.deleteFavoriteMovie(id)
            .compose(completableScheduler<Unit>())
            .subscribe({
                _deleteFavoriteMovie.value = Result.success(Unit)

            }, { genericErrorHandler(it, _deleteFavoriteMovie) })
            .addTo(disposable)
    }

    fun deleteFavoriteTvShow(id: Int) {
        _deleteFavoriteTvShow.value = Result.loading()

        useCase.deleteFavoriteTvShow(id)
            .compose(completableScheduler<Unit>())
            .subscribe({
                _deleteFavoriteTvShow.value = Result.success(Unit)

            }, { genericErrorHandler(it, _deleteFavoriteTvShow) })
            .addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }
}