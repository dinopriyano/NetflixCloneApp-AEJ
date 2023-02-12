package aej.dino.netflixcloneapps.core.data

import aej.dino.netflixcloneapps.core.data.local.LocalDataSource
import aej.dino.netflixcloneapps.core.data.remote.RemoteDataSource
import aej.dino.netflixcloneapps.core.data.remote.Resource
import aej.dino.netflixcloneapps.core.data.remote.request.LoginRequest
import aej.dino.netflixcloneapps.core.data.remote.request.RegisterRequest
import aej.dino.netflixcloneapps.core.data.remote.response.LoginResponse
import aej.dino.netflixcloneapps.core.data.remote.response.RegisterResponse
import aej.dino.netflixcloneapps.core.data.remote.response.WebResponse
import aej.dino.netflixcloneapps.core.domain.repository.IAuthRepository
import kotlinx.coroutines.flow.Flow

class AuthRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : IAuthRepository {
    override suspend fun login(
        loginRequest: LoginRequest
    ): Flow<Resource<WebResponse<LoginResponse>>> {
        return remoteDataSource.login(loginRequest)
    }

    override suspend fun logout() {
        return localDataSource.logout()
    }

    override suspend fun register(registerRequest: RegisterRequest): Flow<Resource<WebResponse<RegisterResponse>>> {
        return remoteDataSource.register(registerRequest)
    }

    override suspend fun getIsLoggedIn(): Flow<Boolean> {
        return localDataSource.isLoggedIn()
    }

    override suspend fun getCurrentUsername(): Flow<String> {
        return localDataSource.getCurrentUsername()
    }

    override suspend fun storeUsername(email: String) {
        localDataSource.storeUsername(email)
    }

    override suspend fun storeToken(token: String) {
        localDataSource.storeToken(token)
    }


}