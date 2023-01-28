package aej.dino.netflixcloneapps.ui

import aej.dino.netflixcloneapps.MovieApplication
import aej.dino.netflixcloneapps.core.domain.model.Movie
import aej.dino.netflixcloneapps.core.domain.usecase.MovieUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    private val _movies = MutableStateFlow(emptyList<Movie>())
    val movies: StateFlow<List<Movie>> get() = _movies
    private val currentMovie = mutableListOf<Movie>()

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MovieApplication)
                MovieViewModel(application.appMovieContainer.movieUseCase)
            }
        }
    }

    fun getMovies() {
        viewModelScope.launch {
            movieUseCase.getNowPlayingMovie().collect {
                currentMovie.clear()
                currentMovie.addAll(it)
                _movies.value = currentMovie
            }
        }
    }

    fun searchMovie(keyword: String) {
        _movies.value = currentMovie.filter { movie ->
            movie.title.contains(keyword, true) || movie.description.contains(keyword, true)
        }
    }

}