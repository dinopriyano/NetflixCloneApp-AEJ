package aej.dino.netflixcloneapps.core.domain.usecase

import aej.dino.netflixcloneapps.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieInteractor {
    suspend fun getNowPlayingMovie(): Flow<List<Movie>>
    suspend fun getMovieDetail(id: String): Flow<Movie>
}