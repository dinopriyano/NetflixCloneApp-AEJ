package aej.dino.netflixcloneapps.ui.screen.auth.login

import aej.dino.netflixcloneapps.core.data.remote.response.LoginResponse

sealed class LoginScreenState {
  class Success(val user: LoginResponse): LoginScreenState()
  class Error(val message: String): LoginScreenState()
  object Empty: LoginScreenState()
  object Loading: LoginScreenState()
}
