package aej.dino.netflixcloneapps.core.domain.usecase

import aej.dino.netflixcloneapps.core.data.UserRepository
import aej.dino.netflixcloneapps.core.data.remote.Resource
import aej.dino.netflixcloneapps.core.data.remote.request.UpdateUserRequest
import aej.dino.netflixcloneapps.core.data.remote.response.UpdateResponse
import aej.dino.netflixcloneapps.core.data.remote.response.WebResponse
import aej.dino.netflixcloneapps.core.domain.model.User
import kotlinx.coroutines.flow.Flow

class UserUseCase constructor(
  private val userRepository: UserRepository
): IUserInteractor {
  override suspend fun getUser(id: String, email: String): Flow<User> =
    userRepository.getUser(email, id)

  override suspend fun storeUser(user: User): Flow<Boolean> =
    userRepository.storeUser(user)

  override suspend fun updateUser(updateUserRequest: UpdateUserRequest, token: String): Flow<Resource<WebResponse<UpdateResponse>>> =
    userRepository.updateUser(updateUserRequest, "Bearer $token")
}