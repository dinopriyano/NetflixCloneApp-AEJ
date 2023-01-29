package aej.dino.netflixcloneapps.core.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

interface SafeApiCall {
  suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
  ): Resource<T> {
    return withContext(Dispatchers.IO) {
      try{
        Resource.Success(apiCall.invoke())
      }
      catch (e: Throwable) {
        when(e) {
          is HttpException -> {
            Resource.Error(e.code(), "Ups, ada masalah pada internet kamu")
          }
          is UnknownHostException -> {
            Resource.Error(503, "Ups, ada masalah pada internet kamu")
          }
          is SocketTimeoutException -> {
            Resource.Error(408, "Ups, terjadi timeout")
          }
          else -> {
            Resource.Error(-1, "Ups, ada yang tidak beres")
          }
        }
      }
    }
  }
}