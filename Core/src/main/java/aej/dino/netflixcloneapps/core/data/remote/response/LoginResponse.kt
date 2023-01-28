package aej.dino.netflixcloneapps.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("token_type")
	val tokenType: String,

	@field:SerializedName("user")
	val user: User,

	@field:SerializedName("token")
	val token: String
)

data class User(

	@field:SerializedName("birthdate")
	val birthdate: String,

	@field:SerializedName("gender")
	val gender: Int,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("two_factor_recovery_codes")
	val twoFactorRecoveryCodes: Any,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("email_verified_at")
	val emailVerifiedAt: Any,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("two_factor_secret")
	val twoFactorSecret: Any,

	@field:SerializedName("two_factor_confirmed_at")
	val twoFactorConfirmedAt: Any,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String
)
