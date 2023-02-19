package aej.dino.netflixcloneapps.core.data.local.room.entity

import aej.dino.netflixcloneapps.core.domain.model.User
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
  tableName = "user",
  primaryKeys = ["id", "email"]
)
data class UserEntity(
  @ColumnInfo(name = "username")
  val fullName: String,

  @ColumnInfo(name = "email")
  val email: String,

  @ColumnInfo(name = "id")
  val id: String,

  @ColumnInfo(name = "gender")
  val gender: Int,

  @ColumnInfo(name = "birth_date")
  val birthDate: String,
)

fun UserEntity.toUser() = User(
  this.fullName,
  this.email,
  this.id,
  this.gender,
  this.birthDate
)
