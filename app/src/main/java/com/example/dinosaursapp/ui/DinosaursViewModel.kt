package com.example.dinosaursapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dinosaursapp.network.DinosaursApi
import com.example.dinosaursapp.network.DinosaursPhoto
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface DinosaursUiState {
    data class Success(val photos: List<DinosaursPhoto>) : DinosaursUiState
    object Error : DinosaursUiState
    object Loading : DinosaursUiState
}

class DinosaursViewModel : ViewModel() {
    var dinosaursUiState: DinosaursUiState by mutableStateOf(DinosaursUiState.Loading)
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getDinosaursPhotos()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates
     * [DinosaursPhoto] [List] [MutableList].
     */
    fun getDinosaursPhotos() {
        viewModelScope.launch {
            try {
                DinosaursUiState.Success(DinosaursApi.retrofitService.getPhotos())
            } catch (e: IOException) {
                DinosaursUiState.Error
            }.also { dinosaursUiState = it }
        }
    }
}