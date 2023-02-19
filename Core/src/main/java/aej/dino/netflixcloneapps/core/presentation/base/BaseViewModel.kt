package aej.dino.netflixcloneapps.core.presentation.base

import aej.dino.netflixcloneapps.core.data.remote.Resource
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel: ViewModel() {

  protected suspend inline fun <reified T> Flow<T>.runFlow(state: MutableStateFlow<T>) {
    val clazz = T::class.java
    if(clazz.isAssignableFrom(Resource::class.java)) {
      state.value = Resource.Loading as T
    }
    collect {
      state.value = it
    }
  }

}