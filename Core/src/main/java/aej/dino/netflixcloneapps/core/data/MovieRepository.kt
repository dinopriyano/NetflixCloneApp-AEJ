package aej.dino.netflixcloneapps.core.data

import aej.dino.netflixcloneapps.core.data.remote.RemoteDataSource
import aej.dino.netflixcloneapps.core.data.remote.Resource
import aej.dino.netflixcloneapps.core.domain.model.Movie
import aej.dino.netflixcloneapps.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepository(
    private val remoteDataSource: RemoteDataSource
) : IMovieRepository {
    override suspend fun getNowPlayingMovie(): Flow<Resource<List<Movie>>> =
        remoteDataSource.getNowPlayingMovie()

    override suspend fun getPopularMovie(): Flow<Resource<List<Movie>>> =
        remoteDataSource.getPopularMovie()

    override suspend fun getUpcomingMovie(): Flow<Resource<List<Movie>>> =
        remoteDataSource.getUpcomingMovie()

    override suspend fun getMovieDetail(id: String): Flow<Movie> =
        remoteDataSource.getMovieDetail(id)
}