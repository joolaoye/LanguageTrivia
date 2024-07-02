package com.example.linguawarrior.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.LinguaWarrior.model.Question
import com.example.linguawarrior.ui.screens.Game.GameUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SharedViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState : StateFlow<GameUiState>
        get() = _uiState.asStateFlow()

    private lateinit var questionSet : List<Question>

    var questionNumber = 0
        private set

    var currentScore = 0
        private set

    fun checkUserAnswer(option: String) {
        if (option == questionSet[questionNumber].answer) {
            _uiState.update {
                    currentState ->

                currentState.copy(
                    currentScore = _uiState.value.currentScore.plus(20)
                )
            }
        }

        questionNumber += 1
        updateQuestion()
    }

    fun updateQuestion() {
        _uiState.update {
                currentState ->

            currentState.copy(
                currentQuestion = questionSet[questionNumber],
                questionNumber = questionNumber.inc()
            )
        }
    }

    fun uploadDataset(dataset: List<Question>) {
        _uiState.value = GameUiState(dataset = dataset)
    }

    fun resetGame() {
        questionSet =  _uiState.value.dataset.shuffled().take(10)
        questionNumber = 0
        updateQuestion()
    }
}