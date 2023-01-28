package aej.dino.netflixcloneapps.core.data.local.room.dao

import aej.dino.netflixcloneapps.core.data.local.room.entity.UserEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

  @Query("SELECT * FROM user WHERE email = :email AND password = :password")
  fun getUserByEmailAndPassword(email: String, password: String): Flow<List<UserEntity>>

  @Insert
  suspend fun storeUser(userEntity: UserEntity)

}