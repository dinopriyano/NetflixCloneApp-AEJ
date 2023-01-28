package aej.dino.netflixcloneapps.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class WebResponse<T>(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("data")
	val data: T,
)
