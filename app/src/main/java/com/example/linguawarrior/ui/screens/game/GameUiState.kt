package com.example.linguawarrior.ui.screens.game

import com.example.LinguaWarrior.model.Question

data class GameUiState(
    val dataset : List<Question> = listOf(),
    val currentQuestion : Question = Question(),
    val questionNumber : Int = 0,
    val time : Long = 0,
    val currentScore : Int = 0,
    val answeredCorrectly: Int = 0,
    val answeredWrong : Boolean = false,
    val canClick : Boolean = false,
    val quizEnd : Boolean = false,
)
