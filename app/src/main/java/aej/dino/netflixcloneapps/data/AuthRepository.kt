package aej.dino.netflixcloneapps.data

import aej.dino.netflixcloneapps.data.local.LocalDataSource
import aej.dino.netflixcloneapps.domain.model.User
import aej.dino.netflixcloneapps.domain.repository.IAuthRepository
import kotlinx.coroutines.flow.Flow

class AuthRepository(
  private val localDataSource: LocalDataSource
): IAuthRepository {
  override suspend fun login(email: String, password: String): Flow<List<User>> {
    return localDataSource.loginUser(email, password)
  }

  override suspend fun register(user: User): Flow<User> {
    return localDataSource.registerUser(user)
  }

  override suspend fun getIsLoggedIn(): Flow<Boolean> {
    return localDataSource.isLoggedIn()
  }

  override suspend fun storeEmail(email: String) {
    localDataSource.storeEmail(email)
  }
}