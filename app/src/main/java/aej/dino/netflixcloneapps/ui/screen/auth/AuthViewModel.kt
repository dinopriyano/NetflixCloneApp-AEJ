package aej.dino.netflixcloneapps.ui.screen.auth

import aej.dino.netflixcloneapps.MovieApplication
import aej.dino.netflixcloneapps.data.AuthRepository
import aej.dino.netflixcloneapps.data.remote.Resource
import aej.dino.netflixcloneapps.data.remote.request.LoginRequest
import aej.dino.netflixcloneapps.data.remote.request.RegisterRequest
import aej.dino.netflixcloneapps.ui.screen.auth.login.LoginScreenState
import aej.dino.netflixcloneapps.ui.screen.auth.register.RegisterScreenState
import android.util.Log
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
) : ViewModel() {

    val registerRequest = MutableStateFlow<RegisterRequest>(RegisterRequest())

    private val _loginScreenState = MutableStateFlow<LoginScreenState>(LoginScreenState.Empty)
    val loginScreenState: StateFlow<LoginScreenState> get() = _loginScreenState

    private val _registerScreenState =
        MutableStateFlow<RegisterScreenState>(RegisterScreenState.Empty)
    val registerScreenState: StateFlow<RegisterScreenState> get() = _registerScreenState

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MovieApplication)
                AuthViewModel(application.appMovieContainer.authRepository)
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            authRepository.login(LoginRequest(password, email)).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _loginScreenState.value = LoginScreenState.Success(result.data.data)
                    }

                    is Resource.Error -> {
                        _loginScreenState.value = LoginScreenState.Error(result.msg)
                    }

                    else -> Unit
                }
            }
        }
    }

    fun register(request: RegisterRequest) {
        viewModelScope.launch {
            _registerScreenState.value = RegisterScreenState.Loading
            authRepository.register(request).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _registerScreenState.value = RegisterScreenState.Success(result.data.data)
                    }

                    is Resource.Error -> {
                        _registerScreenState.value = RegisterScreenState.Error(result.msg)
                    }

                    else -> Unit
                }
            }
        }
    }

    fun storeEmail(email: String) {
        viewModelScope.launch {
            authRepository.storeEmail(email)
        }
    }

    fun storeToken(token: String) {
        viewModelScope.launch {
            authRepository.storeToken(token)
        }
    }

}