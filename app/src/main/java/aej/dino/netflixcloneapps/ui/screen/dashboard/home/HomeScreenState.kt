package aej.dino.netflixcloneapps.ui.screen.dashboard.home

import aej.dino.netflixcloneapps.core.domain.model.Movie

sealed class HomeScreenState {
  class Success(
    val nowPlayingMovies: List<Movie>,
    val popularMovies: List<Movie>,
    val upcomingMovies: List<Movie>
  ) : HomeScreenState()

  class Error(
    val message: String,
    val whatError: HomeScreenRequestEnum
  ) : HomeScreenState()

  object Loading : HomeScreenState()
  object Empty : HomeScreenState()
}
