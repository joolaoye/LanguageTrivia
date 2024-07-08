package com.example.linguawarrior.ui

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.LinguaWarrior.model.Question
import com.example.linguawarrior.data.MAX_NO_OF_WORDS
import com.example.linguawarrior.data.SCORE_INCREASE
import com.example.linguawarrior.ui.screens.game.GameUiEvent
import com.example.linguawarrior.ui.screens.game.GameUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameViewModel(
    private var questions: List<Question>
) : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState : StateFlow<GameUiState>
        get() = _uiState.asStateFlow()

    var questionNumber = 0
        private set

    lateinit var currentQuestion : Question
        private set
    var remainingTime : Long = 10000
        private set

    var timer : CountDownTimer? = null

    fun onEvent(event : GameUiEvent) {
        when(event) {
            is GameUiEvent.CheckUserAnswer -> checkUserAnswer(event.option)
            is GameUiEvent.ResumeTimer -> resumeTimer()
            is GameUiEvent.ResetGame -> resetGame()
            is GameUiEvent.UpdateDataset -> updateDataset(event.questions)
            is GameUiEvent.PauseTimer -> pauseTimer()
        }
    }


    fun startTimer(timeInMs : Long) {
        timer?.cancel()

        timer = object : CountDownTimer(timeInMs, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                remainingTime = millisUntilFinished

                _uiState.update {
                        currentState ->

                    currentState.copy(time = (millisUntilFinished / 1000))
                }
            }

            override fun onFinish() {
                checkUserAnswer()
            }
        }.start()
    }


    fun checkUserAnswer(option: String = "") {
        viewModelScope.launch {

            _uiState.update {
                currentState ->

                currentState.copy(selected = option)
            }

            if (option == currentQuestion?.answer) {
                _uiState.update {
                        currentState ->

                    currentState.copy(
                        currentScore = _uiState.value.currentScore.plus(SCORE_INCREASE),
                        answeredCorrectly = currentState.answeredCorrectly + 1,
                        canClick = false
                    )
                }
            } else {
                _uiState.update {
                        currentState ->

                    currentState.copy(
                        answeredWrong = true,
                        canClick = false
                    )
                }
            }

            pauseTimer()

            delay(2000)

            resetTimer()
            questionNumber += 1
            updateQuestion()
        }
    }

    fun updateQuestion() {
        if (questionNumber == MAX_NO_OF_WORDS) {
            _uiState.update {
                    currentState ->

                currentState.copy(
                    quizEnd = true
                )
            }
        } else {
            currentQuestion = questions[questionNumber]

            _uiState.update {
                    currentState ->

                currentState.copy(
                    currentQuestion = currentQuestion,
                    questionNumber = questionNumber + 1,
                    selected = "",
                    answeredWrong = false,
                    canClick = true
                )
            }

            startTimer(remainingTime)
        }
    }

    fun pauseTimer() {
        timer?.cancel()
    }

    fun resumeTimer() {
        startTimer(remainingTime)
    }

    private fun resetTimer() {
        pauseTimer()
        remainingTime = 10000
    }

    fun resetGame() {
        resetTimer()
        questionNumber = 0
        _uiState.value = GameUiState()
        updateQuestion()
    }

    // FIXME: Redundant code
    fun updateDataset(questions: List<Question>) {
        this.questions = questions
        resetGame()
    }

    init {
        resetGame()
    }
}