package aej.dino.netflixcloneapps.domain.repository

import aej.dino.netflixcloneapps.data.remote.Resource
import aej.dino.netflixcloneapps.data.remote.request.LoginRequest
import aej.dino.netflixcloneapps.data.remote.request.RegisterRequest
import aej.dino.netflixcloneapps.data.remote.response.LoginResponse
import aej.dino.netflixcloneapps.data.remote.response.RegisterResponse
import aej.dino.netflixcloneapps.data.remote.response.WebResponse
import aej.dino.netflixcloneapps.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {
  suspend fun login(loginRequest: LoginRequest): Flow<Resource<WebResponse<LoginResponse>>>
  suspend fun register(registerRequest: RegisterRequest): Flow<Resource<WebResponse<RegisterResponse>>>
  suspend fun getIsLoggedIn(): Flow<Boolean>
  suspend fun storeEmail(email: String)
  suspend fun storeToken(token: String)
}