package com.example.linguawarrior.ui.screens.game

sealed class GameUiEvent {
    data class CheckUserAnswer(val option : String) : GameUiEvent()
    object UpdatQuestionSet : GameUiEvent()
    object ResumeTimer : GameUiEvent()
    object PauseTimer : GameUiEvent()
    object ResetGame : GameUiEvent()
}