package aej.dino.netflixcloneapps.core.data.remote.response

import aej.dino.netflixcloneapps.core.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class ListMovieResponse(
    @field:SerializedName("results")
    val results: List<MovieResponse>? = null,
)

data class MovieResponse(

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("original_language")
    val originalLanguage: String? = null,

    @field:SerializedName("original_title")
    val originalTitle: String? = null,

    @field:SerializedName("video")
    val video: Boolean? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("genre_ids")
    val genreIds: List<Int?>? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @field:SerializedName("release_date")
    val releaseDate: String? = null,

    @field:SerializedName("popularity")
    val popularity: Double? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("adult")
    val adult: Boolean? = null,

    @field:SerializedName("vote_count")
    val voteCount: Int? = null
)

fun MovieResponse.toMovie() = Movie(
    (this.id ?: -1).toString(),
    this.originalTitle ?: "",
    this.overview ?: "",
    this.voteAverage?.toFloat() ?: 0f,
    this.backdropPath.let {
        if (it == null || it.isBlank()) "" else "https://image.tmdb.org/t/p/original/$it"
    },
    this.posterPath.let {
        if (it == null || it.isBlank()) "" else "https://image.tmdb.org/t/p/w500/$it"
    },
)

fun ListMovieResponse.toListMovie() = this.results?.map { it.toMovie() } ?: listOf()