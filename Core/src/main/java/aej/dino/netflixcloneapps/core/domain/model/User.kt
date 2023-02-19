package aej.dino.netflixcloneapps.core.domain.model

import aej.dino.netflixcloneapps.core.data.local.room.entity.UserEntity

data class User(
  val name: String,
  val email: String,
  val id: String,
  val gender: Int,
  val birthDate: String
)

fun User.toUserEntity() = UserEntity(
  this.name,
  this.email,
  this.id,
  this.gender,
  this.birthDate
)
