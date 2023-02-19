package aej.dino.netflixcloneapps.core.data.remote.request

import com.google.gson.annotations.SerializedName

data class UpdateUserRequest(

	@field:SerializedName("email")
	var email: String = "",

	@field:SerializedName("username")
	val username: String = ""
)
