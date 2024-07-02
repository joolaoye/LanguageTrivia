package com.example.linguawarrior.ui.screens

data class QuizUiState(
    val currentWord : String = "",
    val options : List<String> = listOf(),
    val currentQuestion : String = "",
    val time : String = "",
    val isPaused : Boolean = false,

    val audioToggleChecked : Boolean = true
)
