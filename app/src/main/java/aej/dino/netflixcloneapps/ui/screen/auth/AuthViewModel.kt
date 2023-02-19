package aej.dino.netflixcloneapps.ui.screen.auth

import aej.dino.netflixcloneapps.MovieApplication
import aej.dino.netflixcloneapps.core.data.remote.Resource
import aej.dino.netflixcloneapps.core.data.remote.request.LoginRequest
import aej.dino.netflixcloneapps.core.data.remote.request.RegisterRequest
import aej.dino.netflixcloneapps.core.domain.model.User
import aej.dino.netflixcloneapps.core.domain.usecase.AuthUseCase
import aej.dino.netflixcloneapps.core.domain.usecase.UserUseCase
import aej.dino.netflixcloneapps.core.presentation.base.BaseViewModel
import aej.dino.netflixcloneapps.ui.screen.auth.login.LoginScreenState
import aej.dino.netflixcloneapps.ui.screen.auth.register.RegisterScreenState
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authUseCase: AuthUseCase,
    private val userUseCase: UserUseCase
) : BaseViewModel() {

    val registerRequest = MutableStateFlow<RegisterRequest>(RegisterRequest())

    private val _loginScreenState = MutableStateFlow<LoginScreenState>(LoginScreenState.Empty)
    val loginScreenState: StateFlow<LoginScreenState> get() = _loginScreenState

    private val _registerScreenState =
        MutableStateFlow<RegisterScreenState>(RegisterScreenState.Empty)
    val registerScreenState: StateFlow<RegisterScreenState> get() = _registerScreenState
    private val _userStoreResponse = MutableStateFlow<Boolean>(false)
    val userStoreResponse: StateFlow<Boolean> get() = _userStoreResponse

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MovieApplication)
                AuthViewModel(application.appMovieContainer.authUseCase, application.appMovieContainer.userUseCase)
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            authUseCase.login(LoginRequest(password, email)).collect { result ->
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
            authUseCase.register(request).collect { result ->
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

    fun storeUser(user: User) {
        viewModelScope.launch {
            userUseCase.storeUser(user).runFlow(_userStoreResponse)
        }
    }

    fun storeUsername(email: String) {
        viewModelScope.launch {
            authUseCase.storeUsername(email)
        }
    }

    fun storeEmail(email: String) {
        viewModelScope.launch {
            authUseCase.storeEmail(email)
        }
    }

    fun storeId(id: String) {
        viewModelScope.launch {
            authUseCase.storeId(id)
        }
    }

    fun storeToken(token: String) {
        viewModelScope.launch {
            authUseCase.storeToken(token)
        }
    }

}