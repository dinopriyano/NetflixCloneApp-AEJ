package aej.dino.netflixcloneapps.core.domain.repository

import aej.dino.netflixcloneapps.core.data.remote.Resource
import aej.dino.netflixcloneapps.core.domain.model.Movie
import aej.dino.netflixcloneapps.core.domain.model.Videos
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    suspend fun getNowPlayingMovie(): Flow<Resource<List<Movie>>>
    suspend fun getPopularMovie(): Flow<Resource<List<Movie>>>
    suspend fun getUpcomingMovie(): Flow<Resource<List<Movie>>>
    suspend fun getMovieDetail(id: String): Flow<Movie>
    suspend fun getVideoFromMovie(id: String): Flow<Resource<Videos>>

    suspend fun getAllFavoriteMovie(): Flow<List<Movie>>
    suspend fun isMovieFavorite(id: String): Flow<Boolean>
    suspend fun addMovieToFavorite(movie: Movie): Flow<Boolean>
    suspend fun removeMoveFromFavorite(movie: Movie): Flow<Boolean>
}