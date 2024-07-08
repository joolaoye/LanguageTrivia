package com.example.linguawarrior.ui.shared.viewmodel

import androidx.lifecycle.ViewModel
import com.example.LinguaWarrior.model.Question
import com.example.linguawarrior.data.MAX_NO_OF_WORDS
import com.example.linguawarrior.model.Answer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SharedViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SharedUiState())
    val uiState : StateFlow<SharedUiState>
        get() = _uiState.asStateFlow()

    fun onEvent(event : SharedUiEvent) {
        when(event) {
            is SharedUiEvent.UploadQuestionSet -> uploadQuestionSet(event.questionSet)
            is SharedUiEvent.UpdateAnswer -> updateAnswer(
                option = event.option,
                currentQuestion = event.currentQuestion,
                questionNumber = event.questionNumber
            )
            is SharedUiEvent.FetchQuestions -> fetchQuestions()
        }
    }

    fun uploadQuestionSet(questionSet : List<Question>) {
        _uiState.value = SharedUiState(questionSet = questionSet)
    }

    fun fetchQuestions() : List<Question> {
        return _uiState.value.questionSet
    }

    fun updateAnswer(option: String = "", currentQuestion: Question, questionNumber : Int) {
        _uiState.update { currentState ->

            val updatedAnswers = currentState.answerSet.toMutableList()

            updatedAnswers.add(
                Answer(
                    question = currentQuestion,
                    choice = option,
                    questionNumber = questionNumber
                )
            )

            currentState.copy(
                answerSet = updatedAnswers
            )
        }
    }
}