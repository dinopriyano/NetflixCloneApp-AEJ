package aej.dino.netflixcloneapps.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Videos(

  val id: Int,

  val results: List<VideoItem>
): Parcelable

@Parcelize
data class VideoItem(

  val site: String,

  val size: Int,

  val name: String,

  val id: String,

  val type: String,

  val key: String
): Parcelable
