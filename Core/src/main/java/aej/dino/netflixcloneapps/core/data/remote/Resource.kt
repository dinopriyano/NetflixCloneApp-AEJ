package aej.dino.netflixcloneapps.core.data.remote

sealed class Resource<out T> {
  class Success<T>(val data: T): Resource<T>()
  class Error(val code: Int, val msg: String = ""): Resource<Nothing>()
  object Empty: Resource<Nothing>()
  object Loading: Resource<Nothing>()
}
