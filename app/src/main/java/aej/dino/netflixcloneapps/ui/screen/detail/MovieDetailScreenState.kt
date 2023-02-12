package aej.dino.netflixcloneapps.ui.screen.detail

import aej.dino.netflixcloneapps.core.domain.model.Movie
import aej.dino.netflixcloneapps.core.domain.model.Videos

sealed class MovieDetailScreenState {
  class Success(
    val movieDetail: Movie,
    val videos: Videos
  ) : MovieDetailScreenState()

  class Error(
    val message: String,
    val whatError: MovieDetailScreenRequestEnum
  ) : MovieDetailScreenState()

  object Loading : MovieDetailScreenState()
  object Empty : MovieDetailScreenState()
}
