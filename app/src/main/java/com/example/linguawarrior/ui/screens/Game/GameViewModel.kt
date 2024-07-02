package com.example.linguawarrior.ui.screens.Game

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.LinguaWarrior.model.Question
import com.example.linguawarrior.data.frenchQuestions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState : StateFlow<GameUiState>
        get() = _uiState.asStateFlow()

    private lateinit var questionSet : List<Question>

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

    fun checkUserAnswer(option: String) {
        Log.d("test", option)
    }

    fun takeSubsetFromQuestionDataset() {
       questionSet =  frenchQuestions.shuffled().take(10)
    }

    fun resetGame() {
        takeSubsetFromQuestionDataset()
        _uiState.update {
            currentState ->

            currentState.copy(
                currentQuestion = questionSet[0]
            )
        }
    }

    init {
        resetGame()
    }
}