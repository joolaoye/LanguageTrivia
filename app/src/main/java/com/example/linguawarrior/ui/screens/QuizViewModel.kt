package com.example.linguawarrior.ui.screens

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class QuizViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(QuizUiState())
    val uiState : StateFlow<QuizUiState>
        get() = _uiState.asStateFlow()

    fun pauseGame() {
        _uiState.update {
            currentState ->
            currentState.copy(
                isPaused = true)
        }
    }

    fun dismissPauseDialog() {
        _uiState.update {
                currentState ->
            currentState.copy(
                isPaused = false)
        }
    }

    fun toggleAudio() {
        _uiState.update {
            currentState ->

            currentState.copy(audioToggleChecked = !uiState.value.audioToggleChecked)
        }
    }
}