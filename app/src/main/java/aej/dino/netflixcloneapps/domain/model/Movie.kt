package aej.dino.netflixcloneapps.domain.model

data class Movie(
  val title: String,
  val description: String,
  val rating: Float,
  val backdropResourceId: Int,
  val posterResourceId: Int
)
