package com.example.linguawarrior.ui.screens.Game

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.LinguaWarrior.R
import com.example.linguawarrior.ui.SharedViewModel

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    sharedViewModel: SharedViewModel,
    onExitPauseMenu : () -> Unit
) {
    val sharedUiState by sharedViewModel.uiState.collectAsState()

    var isPause by rememberSaveable {
        mutableStateOf(false)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar(
                questionNumber = sharedUiState.questionNumber,
                time = stringResource(R.string.time_value, sharedUiState.time),
                score = sharedUiState.currentScore,
                onPause = {
                    sharedViewModel.pauseTimer()
                    isPause = !isPause
                }
            )
        }
    ) {
        paddingValues ->

        QuestionView(
            quizViewModel = sharedViewModel,
            modifier = Modifier.padding(paddingValues)
        )

        if (isPause) {
            PauseMenu(
                onDissmissRequest = {
                    sharedViewModel.resumeTimer()
                    isPause = !isPause },
                onResumeQuiz = {
                    sharedViewModel.resumeTimer()
                    isPause = !isPause },
                onExit = {
                    isPause = !isPause
                    onExitPauseMenu()
                }
            )
        }
    }
}
