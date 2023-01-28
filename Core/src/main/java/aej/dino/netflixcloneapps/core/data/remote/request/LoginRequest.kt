package aej.dino.netflixcloneapps.core.data.remote.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("email")
	val email: String
)
