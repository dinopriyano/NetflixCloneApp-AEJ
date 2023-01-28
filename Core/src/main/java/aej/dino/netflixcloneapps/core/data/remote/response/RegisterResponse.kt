package aej.dino.netflixcloneapps.core.data.remote.response

import aej.dino.netflixcloneapps.core.data.remote.response.User
import com.google.gson.annotations.SerializedName

data class RegisterResponse(

    @field:SerializedName("token_type")
	val tokenType: String,

    @field:SerializedName("user")
	val user: User,

    @field:SerializedName("token")
	val token: String
)
