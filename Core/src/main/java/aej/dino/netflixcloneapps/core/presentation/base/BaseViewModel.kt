package aej.dino.netflixcloneapps.core.presentation.base

import aej.dino.netflixcloneapps.core.data.remote.Resource
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel: ViewModel() {

  protected suspend fun <T> Flow<Resource<T>>.runFlow(state: MutableStateFlow<Resource<T>>) {
    state.value = Resource.Loading
    collect {
      state.value = it
    }
  }

}