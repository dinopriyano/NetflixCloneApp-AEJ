package aej.dino.netflixcloneapps.domain.repository

import aej.dino.netflixcloneapps.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    suspend fun getNowPlayingMovie(): Flow<List<Movie>>
    suspend fun getMovieDetail(id: String): Flow<Movie>
}