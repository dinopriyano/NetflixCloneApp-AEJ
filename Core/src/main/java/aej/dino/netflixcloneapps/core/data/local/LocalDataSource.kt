package aej.dino.netflixcloneapps.core.data.local

import aej.dino.netflixcloneapps.core.data.local.datastore.MovieDataStore
import aej.dino.netflixcloneapps.core.data.local.room.dao.UserDao
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

    suspend fun storeEmail(email: String) = movieDataStore.storeData(MovieDataStore.EMAIL, email)
    suspend fun storeToken(token: String) = movieDataStore.storeData(MovieDataStore.TOKEN, token)

}