package com.example.linguawarrior.ui

import com.example.LinguaWarrior.model.Question
import com.example.linguawarrior.model.Answer

data class SharedUiState(
    val questionSet : List<Question> = listOf(),
    val answerSet : List<Answer> = listOf()
)
