package aej.dino.netflixcloneapps.ui.screen.auth

import aej.dino.netflixcloneapps.MovieApplication
import aej.dino.netflixcloneapps.data.AuthRepository
import aej.dino.netflixcloneapps.domain.model.User
import aej.dino.netflixcloneapps.ui.MainViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
  private val authRepository: AuthRepository
): ViewModel() {

  private val _userLogin = MutableStateFlow<User?>(null)
  val userLoin: StateFlow<User?> get() = _userLogin

  private val _userRegister = MutableStateFlow<User?>(null)
  val userRegister: StateFlow<User?> get() = _userRegister

  companion object {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
      initializer {
        val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MovieApplication)
        AuthViewModel(application.appMovieContainer.authRepository)
      }
    }
  }

  fun login(email: String, password: String) {
    viewModelScope.launch {
      authRepository.login(email, password).collect { users ->
        if(users.isNotEmpty()) {
          _userLogin.value = users[0]
        }
      }
    }
  }

  fun register(email: String, password: String) {
    viewModelScope.launch {
      authRepository.register(User(name = "", email = email, password = password)).collect { user ->
        _userRegister.value = user
      }
    }
  }

  fun storeEmail(email: String) {
    viewModelScope.launch {
      authRepository.storeEmail(email)
    }
  }

}