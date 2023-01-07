package aej.dino.netflixcloneapps.data.local.room.dao

import aej.dino.netflixcloneapps.data.local.room.entity.UserEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

  @Query("SELECT * FROM user WHERE email = :email AND password = :password")
  suspend fun getUserByEmailAndPassword(email: String, password: String): Flow<List<UserEntity>>

  @Insert
  suspend fun storeUser(userEntity: UserEntity)

}