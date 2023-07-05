package com.atb.amphibians.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.atb.amphibians.AmphibiansApp
import com.atb.amphibians.data.Amphibian
import com.atb.amphibians.data.AmphibiansRepository
import kotlinx.coroutines.launch
import java.io.IOException

class AmphibiansViewModel(private val amphibiansRepository: AmphibiansRepository): ViewModel() {
    var uiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set

    init {
        getAmphibians()
    }

    private fun getAmphibians() {
        viewModelScope.launch {
            uiState = try {
                AmphibiansUiState.Success(amphibiansRepository.getAmphibians())
            } catch (e: IOException) {
                AmphibiansUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val amphibiansApp = (this[APPLICATION_KEY] as AmphibiansApp)
                val amphibiansRepository = amphibiansApp.container.amphibiansRepository
                AmphibiansViewModel(amphibiansRepository)
            }
        }
    }
}


sealed interface AmphibiansUiState {
    data class Success(val amphibians: List<Amphibian>): AmphibiansUiState
    object Error: AmphibiansUiState
    object Loading: AmphibiansUiState
}