package aej.dino.netflixcloneapps.domain.repository

import aej.dino.netflixcloneapps.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {
  suspend fun login(email: String, password: String): Flow<List<User>>
  suspend fun register(user: User): Flow<User>
  suspend fun getIsLoggedIn(): Flow<Boolean>
  suspend fun storeEmail(email: String)
}