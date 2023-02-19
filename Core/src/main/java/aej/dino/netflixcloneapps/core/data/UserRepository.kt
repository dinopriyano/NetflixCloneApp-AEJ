package aej.dino.netflixcloneapps.core.data

import aej.dino.netflixcloneapps.core.data.local.LocalDataSource
import aej.dino.netflixcloneapps.core.data.remote.RemoteDataSource
import aej.dino.netflixcloneapps.core.data.remote.Resource
import aej.dino.netflixcloneapps.core.data.remote.request.UpdateUserRequest
import aej.dino.netflixcloneapps.core.data.remote.response.UpdateResponse
import aej.dino.netflixcloneapps.core.data.remote.response.WebResponse
import aej.dino.netflixcloneapps.core.domain.model.User
import aej.dino.netflixcloneapps.core.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow

class UserRepository constructor(
  private val dataSource: LocalDataSource,
  private val remoteDataSource: RemoteDataSource
): IUserRepository {
  override suspend fun getUser(id: String, email: String): Flow<User> =
    dataSource.getUser(email, id)

  override suspend fun storeUser(user: User): Flow<Boolean> =
    dataSource.storeUser(user)

  override suspend fun updateUser(updateUserRequest: UpdateUserRequest, token: String): Flow<Resource<WebResponse<UpdateResponse>>> =
    remoteDataSource.updateUser(updateUserRequest, token)
}