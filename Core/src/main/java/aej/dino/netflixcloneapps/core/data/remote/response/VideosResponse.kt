package aej.dino.netflixcloneapps.core.data.remote.response

import aej.dino.netflixcloneapps.core.domain.model.VideoItem
import aej.dino.netflixcloneapps.core.domain.model.Videos
import com.google.gson.annotations.SerializedName

data class VideosResponse(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("results")
	val results: List<ResultsItem>
)

data class ResultsItem(

	@field:SerializedName("site")
	val site: String,

	@field:SerializedName("size")
	val size: Int,

	@field:SerializedName("iso_3166_1")
	val iso31661: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("official")
	val official: Boolean,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("published_at")
	val publishedAt: String,

	@field:SerializedName("iso_639_1")
	val iso6391: String,

	@field:SerializedName("key")
	val key: String
)

fun VideosResponse.toVideos(): Videos {
	return Videos(
		id = this.id,
		results = this.results.map {
			VideoItem(
				it.site,
				it.size,
				it.name,
				it.id,
				it.type,
				it.key
			)
		}
	)
}
