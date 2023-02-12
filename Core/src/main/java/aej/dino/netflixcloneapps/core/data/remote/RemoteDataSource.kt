package aej.dino.netflixcloneapps.core.data.remote

import aej.dino.netflixcloneapps.core.data.remote.network.MovieService
import aej.dino.netflixcloneapps.core.data.remote.request.LoginRequest
import aej.dino.netflixcloneapps.core.data.remote.request.RegisterRequest
import aej.dino.netflixcloneapps.core.data.remote.response.toListMovie
import aej.dino.netflixcloneapps.core.data.remote.response.toMovie
import aej.dino.netflixcloneapps.core.data.remote.response.toVideos
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(
    private val movieService: MovieService,
    private val movieTmdbService: MovieService,
): SafeApiCall {
    suspend fun getNowPlayingMovie() = flow {
        emit( safeApiCall { movieTmdbService.getNowPlayingMovie().toListMovie() } )
    }

    suspend fun getPopularMovie() = flow {
        emit( safeApiCall { movieTmdbService.getPopularMovie().toListMovie() } )
    }

    suspend fun getUpcomingMovie() = flow {
        emit( safeApiCall { movieTmdbService.getUpcomingMovie().toListMovie() } )
    }

    suspend fun getMovieDetail(id: String) = flow {
        movieTmdbService.getMovieDetail(id).toMovie().let { emit(it) }
    }.catch {
        Log.d("MovieRepository", "getMovieDetail: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)

    suspend fun getVideosFromMovie(id: String) = flow {
        emit( safeApiCall { movieTmdbService.getVideosFromMovie(id).toVideos() } )
    }

    suspend fun register(registerRequest: RegisterRequest) = flow {
        emit(safeApiCall { movieService.register(registerRequest) })
    }

    suspend fun login(loginRequest: LoginRequest) = flow {
        emit(safeApiCall { movieService.login(loginRequest) })
    }
}