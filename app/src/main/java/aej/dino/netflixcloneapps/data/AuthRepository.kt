package aej.dino.netflixcloneapps.data

import aej.dino.netflixcloneapps.data.local.LocalDataSource
import aej.dino.netflixcloneapps.data.remote.RemoteDataSource
import aej.dino.netflixcloneapps.data.remote.Resource
import aej.dino.netflixcloneapps.data.remote.request.LoginRequest
import aej.dino.netflixcloneapps.data.remote.request.RegisterRequest
import aej.dino.netflixcloneapps.data.remote.response.LoginResponse
import aej.dino.netflixcloneapps.data.remote.response.RegisterResponse
import aej.dino.netflixcloneapps.data.remote.response.WebResponse
import aej.dino.netflixcloneapps.domain.model.User
import aej.dino.netflixcloneapps.domain.repository.IAuthRepository
import kotlinx.coroutines.flow.Flow

class AuthRepository(
  private val localDataSource: LocalDataSource,
  private val remoteDataSource: RemoteDataSource
): IAuthRepository {
  override suspend fun login(
    loginRequest: LoginRequest
  ): Flow<Resource<WebResponse<LoginResponse>>> {
    return remoteDataSource.login(loginRequest)
  }

  override suspend fun register(registerRequest: RegisterRequest): Flow<Resource<WebResponse<RegisterResponse>>> {
    return remoteDataSource.register(registerRequest)
  }

  override suspend fun getIsLoggedIn(): Flow<Boolean> {
    return localDataSource.isLoggedIn()
  }

  override suspend fun storeEmail(email: String) {
    localDataSource.storeEmail(email)
  }
}