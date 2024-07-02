package com.example.linguawarrior.ui.screens.Start

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class StartViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(StartUiState())
    val uiState : StateFlow<StartUiState>
        get() = _uiState.asStateFlow()

    fun onPlay() {
        _uiState.update {
            currentState ->
            currentState.copy(displayDialog = true)
        }
    }

    fun dismissStartDialog() {
        _uiState.update {
                currentState ->
            currentState.copy(
                displayDialog = false)
        }
    }
}