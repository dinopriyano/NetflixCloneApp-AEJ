package aej.dino.netflixcloneapps.data

import aej.dino.netflixcloneapps.domain.model.Movie

object MovieDatasource {
  fun getNowPlayingMovie(): List<Movie> = listOf(
    Movie(
      "1",
      "Black Adam",
      "Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian gods—and imprisoned just as quickly—Black Adam is freed from his earthly tomb, ready to unleash his unique form of justice on the modern world.",
      7.2f,
      "R.drawable.backdrop_1",
      "R.drawable.poster_1"
    )
  )
}
