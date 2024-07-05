package com.example.linguawarrior.ui

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.LinguaWarrior.model.Question
import com.example.linguawarrior.data.MAX_NO_OF_WORDS
import com.example.linguawarrior.data.SCORE_INCREASE
import com.example.linguawarrior.model.Answer
import com.example.linguawarrior.ui.screens.game.GameUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState : StateFlow<GameUiState>
        get() = _uiState.asStateFlow()

    private lateinit var questionSet : List<Question>

    var questionNumber = 0
        private set

    var remainingTime : Long = 10000
        private set

    var timer : CountDownTimer? = null

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

               val updatedAnswers = currentState.answers.toMutableList()

               // Add the new answer
               updatedAnswers.add(
                   Answer(
                       question = currentState.currentQuestion,
                       choice = option
                   )
               )

               currentState.copy(
                   selected = option,
                   answers = updatedAnswers
               )
           }
            if (option == questionSet[questionNumber].answer) {
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
            _uiState.update {
                    currentState ->

                currentState.copy(
                    currentQuestion = questionSet[questionNumber],
                    questionNumber = questionNumber + 1,
                    selected = "",
                    answeredWrong = false,
                    canClick = true
                )
            }

            startTimer(remainingTime)
        }
    }

    fun uploadDataset(dataset: List<Question>) {
        _uiState.value = GameUiState(dataset = dataset)
    }

    fun pauseTimer() {
        timer?.cancel()
    }

    fun resumeTimer() {
        startTimer(remainingTime)
    }

    fun resetTimer() {
        pauseTimer()
        remainingTime = 10000
    }

    fun resetGame() {
        questionSet =  _uiState.value.dataset.shuffled().take(MAX_NO_OF_WORDS)
        resetTimer()
        questionNumber = 0
        _uiState.update {
            currentState ->

            currentState.copy(
                currentScore = 0,
                answeredCorrectly = 0,
                answeredWrong = false,
                quizEnd = false
            )
        }
        updateQuestion()
    }
}