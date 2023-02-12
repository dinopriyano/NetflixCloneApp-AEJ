package aej.dino.netflixcloneapps.ui.screen.detail

import aej.dino.netflixcloneapps.MovieApplication
import aej.dino.netflixcloneapps.core.data.remote.Resource
import aej.dino.netflixcloneapps.core.domain.model.Movie
import aej.dino.netflixcloneapps.core.domain.model.Videos
import aej.dino.netflixcloneapps.core.domain.usecase.MovieUseCase
import aej.dino.netflixcloneapps.core.presentation.base.BaseViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MovieDetailViewModel constructor(
    private val movieUseCase: MovieUseCase
) : BaseViewModel() {

    private val _movie = MutableStateFlow<Movie?>(null)
    val movie: StateFlow<Movie?> get() = _movie
    private val _videos = MutableStateFlow<Resource<Videos>>(Resource.Empty)
    val videos: StateFlow<Resource<Videos>> get() = _videos

    private val _movieDetailScreenState = MutableStateFlow<MovieDetailScreenState>(MovieDetailScreenState.Empty)
    val movieDetailScreenState: StateFlow<MovieDetailScreenState> get() = _movieDetailScreenState

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite get() = _isFavorite

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MovieApplication)
                MovieDetailViewModel(application.appMovieContainer.movieUseCase)
            }
        }
    }

    fun combineData() {
        combine(movie, videos) { movie, video ->
            if(movie != null && video is Resource.Success) {
                _movieDetailScreenState.value = MovieDetailScreenState.Success(movie, (videos.value as Resource.Success).data)
            }
            else if(video is Resource.Error) {
                _movieDetailScreenState.value = MovieDetailScreenState.Error((videos.value as Resource.Error).msg, MovieDetailScreenRequestEnum.VIDEOS)
            }
            else {
                _movieDetailScreenState.value = MovieDetailScreenState.Loading
            }
        }.stateIn(viewModelScope, SharingStarted.Eagerly, null)
    }

    fun getMovieDetail(id: String) {
        viewModelScope.launch {
            movieUseCase.getMovieDetail(id).collect {
                _movie.value = it
            }
        }
    }

    fun getVideosFromMovie(id: String) {
        viewModelScope.launch {
            movieUseCase.getVideoFromMovie(id).runFlow(_videos)
        }
    }

    fun isMovieFavorite(id: String) {
        viewModelScope.launch {
            movieUseCase.isMovieFavorite(id).collect {
                _isFavorite.value = it
            }
        }
    }

    fun addToFavorite(movie: Movie) {
        viewModelScope.launch {
            movieUseCase.addMovieToFavorite(movie).collect {
                //isMovieFavorite(movie.id)
            }
        }
    }

    fun removeFromFavorite(movie: Movie) {
        viewModelScope.launch {
            movieUseCase.removeMoveFromFavorite(movie).collect {
                //isMovieFavorite(movie.id)
            }
        }
    }

}