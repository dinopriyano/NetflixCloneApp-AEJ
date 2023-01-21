package aej.dino.netflixcloneapps.data.remote.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest(

	@field:SerializedName("password")
	val password: String = "",

	@field:SerializedName("birthdate")
	val birthdate: String = "",

	@field:SerializedName("gender")
	val gender: Int = -1,

	@field:SerializedName("email")
	var email: String = "",

	@field:SerializedName("username")
	val username: String = ""
)
