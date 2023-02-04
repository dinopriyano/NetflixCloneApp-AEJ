package aej.dino.netflixcloneapps.core.domain.usecase

import aej.dino.netflixcloneapps.core.data.AuthRepository
import aej.dino.netflixcloneapps.core.data.remote.request.LoginRequest
import aej.dino.netflixcloneapps.core.data.remote.request.RegisterRequest
import kotlinx.coroutines.flow.Flow

class AuthUseCase(
    private val authRepository: AuthRepository
): IAuthInteractor {
    override suspend fun login(loginRequest: LoginRequest) = authRepository.login(loginRequest)

    override suspend fun register(registerRequest: RegisterRequest) = authRepository.register(registerRequest)

    override suspend fun getIsLoggedIn() = authRepository.getIsLoggedIn()
    override suspend fun getCurrentUsername() = authRepository.getCurrentUsername()

    override suspend fun storeUsername(email: String) = authRepository.storeUsername(email)

    override suspend fun storeToken(token: String) = authRepository.storeToken(token)

}