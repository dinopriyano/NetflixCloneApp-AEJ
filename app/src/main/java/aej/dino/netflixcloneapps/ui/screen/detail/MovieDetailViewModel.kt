package aej.dino.netflixcloneapps.ui.screen.detail

import aej.dino.netflixcloneapps.MovieApplication
import aej.dino.netflixcloneapps.data.MovieRepository
import aej.dino.netflixcloneapps.domain.model.Movie
import aej.dino.netflixcloneapps.ui.MovieViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel constructor(
  private val movieRepository: MovieRepository
): ViewModel() {

  private val _movie = MutableStateFlow<Movie?>(null)
  val movie: StateFlow<Movie?> get() = _movie

  companion object {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
      initializer {
        val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MovieApplication)
        MovieDetailViewModel(application.appMovieContainer.movieRepository)
      }
    }
  }

  fun getMovieDetail(id: String) {
    viewModelScope.launch {
      movieRepository.getMovieDetail(id).collect {
        _movie.value = it
      }
    }
  }

}