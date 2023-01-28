package aej.dino.netflixcloneapps.ui

import aej.dino.netflixcloneapps.MovieApplication
import aej.dino.netflixcloneapps.core.domain.usecase.AuthUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _isLoggedIn = MutableStateFlow<Boolean?>(null)
    val isLoggedIn: StateFlow<Boolean?> get() = _isLoggedIn

    private val _isSplash = MutableStateFlow(false)
    val isSplash get() = _isSplash


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MovieApplication)
                MainViewModel(application.appMovieContainer.authUseCase)
            }
        }
    }

    init {
        setIsPlash(true)
    }

    private fun setIsPlash(boolean: Boolean) {
        viewModelScope.launch {
            delay(2000L)
            _isSplash.value = boolean
        }
    }

    fun getIsLoggedInUser() {
        viewModelScope.launch {
            authUseCase.getIsLoggedIn().collect {
                _isLoggedIn.value = it
            }
        }
    }

}