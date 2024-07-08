package com.example.linguawarrior.ui.shared.viewmodel

import com.example.LinguaWarrior.model.Question

sealed class SharedUiEvent {
    data class UploadQuestionSet(val questionSet: List<Question>) : SharedUiEvent()
    data class UpdateAnswer(
        val option : String,
        val currentQuestion: Question,
        val questionNumber : Int
    ) : SharedUiEvent()
    object FetchQuestions : SharedUiEvent()
}