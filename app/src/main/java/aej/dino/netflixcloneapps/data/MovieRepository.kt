package aej.dino.netflixcloneapps.data

import aej.dino.netflixcloneapps.data.remote.RemoteDataSource
import aej.dino.netflixcloneapps.domain.model.Movie
import aej.dino.netflixcloneapps.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepository(
    private val remoteDataSource: RemoteDataSource
) : IMovieRepository {
    override suspend fun getNowPlayingMovie(): Flow<List<Movie>> =
        remoteDataSource.getNowPlayingMovie()

    override suspend fun getMovieDetail(id: String): Flow<Movie> =
        remoteDataSource.getMovieDetail(id)
}