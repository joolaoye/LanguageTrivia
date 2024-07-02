package com.example.linguawarrior.ui.screens.Game

import com.example.LinguaWarrior.model.Question

data class GameUiState(
    val currentQuestion : Question = Question(),
    val questionNumber : Int = 1,
    val time : String = "",
    val currentScore : Int = 0,
    val isPaused : Boolean = false,
    val audioToggleChecked : Boolean = true
)
