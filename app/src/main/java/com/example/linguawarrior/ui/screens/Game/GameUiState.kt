package com.example.linguawarrior.ui.screens.Game

import com.example.LinguaWarrior.model.Question

data class GameUiState(
    val dataset : List<Question> = listOf(),
    val currentQuestion : Question = Question(),
    val questionNumber : Int = 1,
    val time : String = "",
    val currentScore : Int = 0
)
