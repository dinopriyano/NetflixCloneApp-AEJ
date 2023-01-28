package aej.dino.netflixcloneapps.core.data.local.room.entity

import aej.dino.netflixcloneapps.core.domain.model.User
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
  tableName = "user"
)
data class UserEntity(
  @ColumnInfo(name = "full_name")
  val fullName: String,

  @ColumnInfo(name = "email")
  @PrimaryKey
  val email: String,

  @ColumnInfo(name = "password")
  val password: String
)

fun UserEntity.toUser() = User(
  this.fullName,
  this.email,
  this.password
)
