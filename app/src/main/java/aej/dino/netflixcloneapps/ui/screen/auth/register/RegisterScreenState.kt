package aej.dino.netflixcloneapps.ui.screen.auth.register

import aej.dino.netflixcloneapps.core.data.remote.response.RegisterResponse

sealed class RegisterScreenState {
  class Success(val user: RegisterResponse): RegisterScreenState()
  class Error(val message: String): RegisterScreenState()
  object Empty: RegisterScreenState()
  object Loading: RegisterScreenState()
}
