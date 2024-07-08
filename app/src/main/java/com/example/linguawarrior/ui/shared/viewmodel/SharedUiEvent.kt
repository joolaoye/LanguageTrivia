package com.example.linguawarrior.ui.shared.viewmodel

import com.example.LinguaWarrior.model.Question
import com.example.linguawarrior.ui.screens.game.GameUiEvent

sealed class SharedUiEvent {
    data class UploadQuestionSet(val questionSet: List<Question>) : SharedUiEvent()
    data class UpdateAnswer(
        val option : String,
        val currentQuestion: Question,
        val questionNumber : Int
    ) : SharedUiEvent()
    object FetchQuestions : SharedUiEvent()
    object DiscardAnswers : SharedUiEvent()
}