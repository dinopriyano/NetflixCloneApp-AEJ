package aej.dino.netflixcloneapps.core.data.local.room.entity

import aej.dino.netflixcloneapps.core.domain.model.Movie
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movie")
data class FavoriteMovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "rating")
    val rating: Float,
    @ColumnInfo(name = "backdrop")
    val backdropResourceId: String,
    @ColumnInfo(name = "poster")
    val posterResourceId: String
)

fun FavoriteMovieEntity.toMovie() = Movie(
    id, title, description, rating, backdropResourceId, posterResourceId
)

fun Movie.toEntity() = FavoriteMovieEntity(
    id, title, description, rating, backdropResourceId, posterResourceId
)