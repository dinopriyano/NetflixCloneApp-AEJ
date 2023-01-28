package aej.dino.netflixcloneapps.core.domain.repository

import aej.dino.netflixcloneapps.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    suspend fun getNowPlayingMovie(): Flow<List<Movie>>
    suspend fun getMovieDetail(id: String): Flow<Movie>
}