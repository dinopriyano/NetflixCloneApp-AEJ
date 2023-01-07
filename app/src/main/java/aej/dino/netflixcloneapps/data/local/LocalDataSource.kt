package aej.dino.netflixcloneapps.data.local

import aej.dino.netflixcloneapps.data.local.datastore.MovieDataStore
import aej.dino.netflixcloneapps.data.local.room.dao.UserDao
import aej.dino.netflixcloneapps.data.local.room.entity.toUser
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
    Log.d("LocalDataSource", "loginUser: failed =${it.message}")
  }.flowOn(Dispatchers.IO)

}