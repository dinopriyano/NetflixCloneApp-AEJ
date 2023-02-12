package aej.dino.netflixcloneapps.core.domain.usecase

import aej.dino.netflixcloneapps.core.data.remote.Resource
import aej.dino.netflixcloneapps.core.data.remote.request.LoginRequest
import aej.dino.netflixcloneapps.core.data.remote.request.RegisterRequest
import aej.dino.netflixcloneapps.core.data.remote.response.LoginResponse
import aej.dino.netflixcloneapps.core.data.remote.response.RegisterResponse
import aej.dino.netflixcloneapps.core.data.remote.response.WebResponse
import kotlinx.coroutines.flow.Flow

interface IAuthInteractor {
    suspend fun login(loginRequest: LoginRequest): Flow<Resource<WebResponse<LoginResponse>>>
    suspend fun logout()
    suspend fun register(registerRequest: RegisterRequest): Flow<Resource<WebResponse<RegisterResponse>>>
    suspend fun getIsLoggedIn(): Flow<Boolean>
    suspend fun getCurrentUsername(): Flow<String>
    suspend fun storeUsername(email: String)
    suspend fun storeToken(token: String)
}