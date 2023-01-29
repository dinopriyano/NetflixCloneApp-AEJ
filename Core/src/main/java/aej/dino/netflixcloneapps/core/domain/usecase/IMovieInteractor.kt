package aej.dino.netflixcloneapps.core.domain.usecase

import aej.dino.netflixcloneapps.core.data.remote.Resource
import aej.dino.netflixcloneapps.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieInteractor {
    suspend fun getNowPlayingMovie(): Flow<Resource<List<Movie>>>
    suspend fun getPopularMovie(): Flow<Resource<List<Movie>>>
    suspend fun getUpcomingMovie(): Flow<Resource<List<Movie>>>
    suspend fun getMovieDetail(id: String): Flow<Movie>
}