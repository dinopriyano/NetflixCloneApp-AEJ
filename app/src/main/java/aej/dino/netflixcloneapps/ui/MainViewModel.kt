package aej.dino.netflixcloneapps.ui

import aej.dino.netflixcloneapps.MovieApplication
import aej.dino.netflixcloneapps.data.AuthRepository
import aej.dino.netflixcloneapps.ui.screen.detail.MovieDetailViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel constructor(
  private val authRepository: AuthRepository
): ViewModel() {

  private val _isLoggedIn = MutableStateFlow<Boolean?>(null)
  val isLoggedIn: StateFlow<Boolean?> get() = _isLoggedIn



  companion object {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
      initializer {
        val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MovieApplication)
        MainViewModel(application.appMovieContainer.authRepository)
      }
    }
  }

  fun getIsLoggedInUser() {
    viewModelScope.launch {
      authRepository.getIsLoggedIn().collect {
        _isLoggedIn.value = it
      }
    }
  }

}