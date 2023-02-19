package aej.dino.netflixcloneapps.core.domain.repository

import aej.dino.netflixcloneapps.core.data.remote.Resource
import aej.dino.netflixcloneapps.core.data.remote.request.UpdateUserRequest
import aej.dino.netflixcloneapps.core.data.remote.response.UpdateResponse
import aej.dino.netflixcloneapps.core.data.remote.response.WebResponse
import aej.dino.netflixcloneapps.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IUserRepository {

  suspend fun getUser(id: String, email: String): Flow<User>

  suspend fun storeUser(user: User): Flow<Boolean>

  suspend fun updateUser(updateUserRequest: UpdateUserRequest, token: String): Flow<Resource<WebResponse<UpdateResponse>>>

}