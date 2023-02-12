package aej.dino.netflixcloneapps.core.data.local

import aej.dino.netflixcloneapps.core.data.local.datastore.MovieDataStore
import aej.dino.netflixcloneapps.core.data.local.room.dao.UserDao
import aej.dino.netflixcloneapps.core.data.local.room.entity.toEntity
import aej.dino.netflixcloneapps.core.data.local.room.entity.toMovie
import aej.dino.netflixcloneapps.core.domain.model.Movie
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LocalDataSource constructor(
    private val userDao: UserDao,
    private val movieDataStore: MovieDataStore
) {

    suspend fun isLoggedIn() = flow {
        movieDataStore.token.collect { emit(it.isNotEmpty()) }
    }.catch {
        Log.e("LocalDataSource", "isLoggedIn: failed=${it.message}")
    }.flowOn(Dispatchers.IO)

    suspend fun logout() = movieDataStore.clear()

    suspend fun getCurrentUsername() = flow {
        movieDataStore.username.collect{ emit(it) }
    }.catch {
        Log.e("LocalDataSource", "getCurrentUsername: failed=${it.message}")
    }.flowOn(Dispatchers.IO)

    suspend fun storeUsername(username: String) = movieDataStore.storeData(MovieDataStore.USERNAME, username)
    suspend fun storeToken(token: String) = movieDataStore.storeData(MovieDataStore.TOKEN, token)

    suspend fun getAllFavoriteMovie() = flow {
        userDao.getAllFavoriteMovie().collect{ emit(it.map { entity -> entity.toMovie()}) }
    }.catch {
        Log.e("LocalDataSource", "getAllFavoriteMovie: failed=${it.message}")
    }.flowOn(Dispatchers.IO)

    suspend fun isMovieFavorite(id: String) = flow<Boolean> {
        val isExist = userDao.getFavoriteMovieById(id)
        emit(isExist)
    }.catch {
        Log.e("LocalDataSource", "isMovieFavorite: failed=${it.message}")
    }.flowOn(Dispatchers.IO)

    suspend fun addMovieToFavorite(movie: Movie) = flow {
        userDao.addFavoriteMovie(movie.toEntity())
        emit(true)
    }.catch {
        Log.e("LocalDataSource", "addMovieToFavorite: failed=${it.message}")
    }.flowOn(Dispatchers.IO)

    suspend fun removeMovieFromFavorite(movie: Movie) = flow {
        userDao.removeMovieFromFavorite(movie.toEntity())
        emit(true)
    }.catch {
        Log.e("LocalDataSource", "removeMovieFromFavorite: failed=${it.message}")
    }.flowOn(Dispatchers.IO)

}