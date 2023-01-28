package aej.dino.netflixcloneapps.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
  val id: String,
  val title: String,
  val description: String,
  val rating: Float,
  val backdropResourceId: String,
  val posterResourceId: String
) : Parcelable
