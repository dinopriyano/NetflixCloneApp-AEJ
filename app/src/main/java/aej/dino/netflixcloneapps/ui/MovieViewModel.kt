package aej.dino.netflixcloneapps.ui

import aej.dino.netflixcloneapps.MovieApplication
import aej.dino.netflixcloneapps.core.data.remote.Resource
import aej.dino.netflixcloneapps.core.data.remote.request.UpdateUserRequest
import aej.dino.netflixcloneapps.core.data.remote.response.UpdateResponse
import aej.dino.netflixcloneapps.core.data.remote.response.WebResponse
import aej.dino.netflixcloneapps.core.domain.model.Movie
import aej.dino.netflixcloneapps.core.domain.usecase.AuthUseCase
import aej.dino.netflixcloneapps.core.domain.usecase.MovieUseCase
import aej.dino.netflixcloneapps.core.domain.usecase.UserUseCase
import aej.dino.netflixcloneapps.core.presentation.base.BaseViewModel
import aej.dino.netflixcloneapps.ui.screen.dashboard.home.HomeScreenRequestEnum
import aej.dino.netflixcloneapps.ui.screen.dashboard.home.HomeScreenState
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MovieViewModel constructor(
    private val movieUseCase: MovieUseCase,
    private val authUseCase: AuthUseCase,
    private val userUseCase: UserUseCase
) : BaseViewModel() {

    private val _homeScreenState = MutableStateFlow<HomeScreenState>(HomeScreenState.Empty)
    val homeScreenState: StateFlow<HomeScreenState> get() = _homeScreenState

    private val _moviesNowPlaying = MutableStateFlow<Resource<List<Movie>>>(Resource.Empty)
    val moviesNowPlaying: StateFlow<Resource<List<Movie>>> get() = _moviesNowPlaying

    private val _moviesPopular = MutableStateFlow<Resource<List<Movie>>>(Resource.Empty)
    val moviesPopular: StateFlow<Resource<List<Movie>>> get() = _moviesPopular

    private val _moviesUpcoming = MutableStateFlow<Resource<List<Movie>>>(Resource.Empty)
    val moviesUpcoming: StateFlow<Resource<List<Movie>>> get() = _moviesUpcoming

    private val _favoriteMovie = MutableStateFlow(emptyList<Movie>())
    val favoriteMovie get() = _favoriteMovie

    private val _currentUsername = MutableStateFlow("")
    val currentUsername get() = _currentUsername
    private val _currentEmail = MutableStateFlow("")
    val currentEmail get() = _currentEmail
    private val _updateUserResponse = MutableStateFlow<Resource<WebResponse<UpdateResponse>>>(Resource.Empty)
    val updateUserResponse : StateFlow<Resource<WebResponse<UpdateResponse>>> get() = _updateUserResponse



    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MovieApplication)
                MovieViewModel(
                    application.appMovieContainer.movieUseCase,
                    application.appMovieContainer.authUseCase,
                    application.appMovieContainer.userUseCase
                )
            }
        }
    }

    fun combineData() {
        combine(moviesNowPlaying, moviesPopular, moviesUpcoming) { nowPlaying, popular, upcoming ->
            if(nowPlaying is Resource.Success && popular is Resource.Success && upcoming is Resource.Success) {
                _homeScreenState.value = HomeScreenState.Success(nowPlaying.data, popular.data, upcoming.data)
            }
            else if(nowPlaying is Resource.Error) {
                _homeScreenState.value = HomeScreenState.Error(nowPlaying.msg, HomeScreenRequestEnum.NOW_PLAYING)
            }
            else if(popular is Resource.Error) {
                _homeScreenState.value = HomeScreenState.Error(popular.msg, HomeScreenRequestEnum.POPULAR)
            }
            else if(upcoming is Resource.Error) {
                _homeScreenState.value = HomeScreenState.Error(upcoming.msg, HomeScreenRequestEnum.UP_COMING)
            }
            else {
                _homeScreenState.value = HomeScreenState.Loading
            }
        }.flowOn(Dispatchers.IO).stateIn(viewModelScope, SharingStarted.Eagerly, null)
    }

    fun getNowPlayingMovies() {
        viewModelScope.launch {
            movieUseCase.getNowPlayingMovie().runFlow(_moviesNowPlaying)
        }
    }

    fun getPopularMovies() {
        viewModelScope.launch {
            movieUseCase.getPopularMovie().runFlow(_moviesPopular)
        }
    }

    fun getUpcomingMovies() {
        viewModelScope.launch {
            movieUseCase.getUpcomingMovie().runFlow(_moviesUpcoming)
        }
    }

    fun getFavoriteMovies(){
        viewModelScope.launch {
            movieUseCase.getAllFavoriteMovie().collect{
                _favoriteMovie.value = it
            }
        }
    }

    fun getCurrentUsername(){
        viewModelScope.launch {
            authUseCase.getCurrentUsername().collect{
                Log.d("TAG", "getCurrentUsername: $it")
                _currentUsername.value = it
            }
        }
    }

    fun getCurrentEmail(){
        viewModelScope.launch {
            authUseCase.getCurrentEmail().collect{
                Log.d("TAG", "getCurrentEmail: $it")
                _currentEmail.value = it
            }
        }
    }

    fun updateUser(updateUserRequest: UpdateUserRequest) {
        viewModelScope.launch {
            userUseCase.updateUser(updateUserRequest, authUseCase.getCurrentToken().first()).runFlow(_updateUserResponse)
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

    fun logout() {
        viewModelScope.launch {
            authUseCase.logout()
        }
    }

}