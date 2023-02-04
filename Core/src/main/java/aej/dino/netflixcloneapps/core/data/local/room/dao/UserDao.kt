package aej.dino.netflixcloneapps.core.data.local.room.dao

import aej.dino.netflixcloneapps.core.data.local.room.entity.FavoriteMovieEntity
import aej.dino.netflixcloneapps.core.data.local.room.entity.UserEntity
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

  @Query("SELECT * FROM user WHERE email = :email AND password = :password")
  fun getUserByEmailAndPassword(email: String, password: String): Flow<List<UserEntity>>

  @Insert
  suspend fun storeUser(userEntity: UserEntity)

  @Insert
  suspend fun addFavoriteMovie(movie: FavoriteMovieEntity)

  @Delete
  suspend fun removeMovieFromFavorite(movie: FavoriteMovieEntity)

  @Query("SELECT * FROM favorite_movie")
  fun getAllFavoriteMovie(): Flow<List<FavoriteMovieEntity>>

  @Query("SELECT EXISTS(SELECT * FROM favorite_movie WHERE id == :id)")
  fun getFavoriteMovieById(id: String): Boolean

}