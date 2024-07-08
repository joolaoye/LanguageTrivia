package com.example.linguawarrior.ui.screens.game

import com.example.LinguaWarrior.model.Question

sealed class GameUiEvent {
    data class CheckUserAnswer(val option : String) : GameUiEvent()
    data class UpdateDataset(val questions : List<Question>) : GameUiEvent()
    object ResumeTimer : GameUiEvent()
    object PauseTimer : GameUiEvent()
    object ResetGame : GameUiEvent()
}