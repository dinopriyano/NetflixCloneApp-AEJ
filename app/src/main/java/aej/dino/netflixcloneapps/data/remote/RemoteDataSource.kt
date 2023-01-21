package aej.dino.netflixcloneapps.data.remote

import aej.dino.netflixcloneapps.data.remote.network.MovieService
import aej.dino.netflixcloneapps.data.remote.request.LoginRequest
import aej.dino.netflixcloneapps.data.remote.request.RegisterRequest
import aej.dino.netflixcloneapps.data.remote.response.toListMovie
import aej.dino.netflixcloneapps.data.remote.response.toMovie
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException

class RemoteDataSource(
    private val movieService: MovieService
): SafeApiCall {
    suspend fun getNowPlayingMovie() = flow {
        movieService.getNowPlayingMovie().toListMovie().let { emit(it) }
    }.catch {
        Log.d("MovieRepository", "getNowPlayingMovie: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)

    suspend fun getMovieDetail(id: String) = flow {
        movieService.getMovieDetail(id).toMovie().let { emit(it) }
    }.catch {
        Log.d("MovieRepository", "getMovieDetail: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)

    suspend fun register(registerRequest: RegisterRequest) = flow {
        emit(safeApiCall { movieService.register(registerRequest) })
    }

    suspend fun login(loginRequest: LoginRequest) = flow {
        emit(safeApiCall { movieService.login(loginRequest) })
    }
}