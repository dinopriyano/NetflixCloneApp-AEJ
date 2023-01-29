package aej.dino.netflixcloneapps.core.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ErrorResponse(
	@field:SerializedName("status_message")
	val statusMessage: String,

	@field:SerializedName("status_code")
	val statusCode: Int,

	@field:SerializedName("success")
	val success: Boolean
) : Parcelable
