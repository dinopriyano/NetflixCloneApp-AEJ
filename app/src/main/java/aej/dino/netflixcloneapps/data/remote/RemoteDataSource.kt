package aej.dino.netflixcloneapps.data.remote

import aej.dino.netflixcloneapps.data.remote.network.MovieService
import aej.dino.netflixcloneapps.data.remote.response.toListMovie
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(
    private val movieService: MovieService
) {
    suspend fun getNowPlayingMovie() = flow {
        movieService.getNowPlayingMovie().toListMovie().let { emit(it) }
    }.catch {
        Log.d("MovieRepository", "getNowPlayingMovie: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)
}