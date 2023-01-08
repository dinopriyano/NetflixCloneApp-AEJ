package aej.dino.netflixcloneapps.data.local

import aej.dino.netflixcloneapps.data.local.datastore.MovieDataStore
import aej.dino.netflixcloneapps.data.local.room.dao.UserDao
import aej.dino.netflixcloneapps.data.local.room.entity.toUser
import aej.dino.netflixcloneapps.domain.model.User
import aej.dino.netflixcloneapps.domain.model.toUserEntity
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class LocalDataSource constructor(
  private val userDao: UserDao,
  private val movieDataStore: MovieDataStore
) {

  suspend fun loginUser(email: String, password: String) = flow {
    emitAll(
      userDao.getUserByEmailAndPassword(email, password).map {
        it.map {
          it.toUser()
        }
      }
    )
  }.catch {
    Log.e("LocalDataSource", "loginUser: failed =${it.message}")
  }.flowOn(Dispatchers.IO)

  suspend fun registerUser(user: User) = flow {
    userDao.storeUser(user.toUserEntity())
    emit(user)
  }.catch {
    Log.e("LocalDataSource", "registerUser: failed=${it.message}")
  }.flowOn(Dispatchers.IO)

  suspend fun isLoggedIn() = flow {
    movieDataStore.email.collect {
      emit(it.isNotEmpty())
    }
  }.catch {
    Log.e("LocalDataSource", "isLoggedIn: failed=${it.message}")
  }.flowOn(Dispatchers.IO)

  suspend fun storeEmail(email: String) = movieDataStore.storeData(MovieDataStore.EMAIL, email)

}