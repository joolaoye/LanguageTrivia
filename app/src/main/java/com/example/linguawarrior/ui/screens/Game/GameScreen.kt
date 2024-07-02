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
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun QuestionScreen(
    modifier: Modifier = Modifier,
    quizViewModel: GameViewModel = viewModel(),
    onExitPauseMenu : () -> Unit
) {
    val quizUiState by quizViewModel.uiState.collectAsState()

    var isPause by rememberSaveable {
        mutableStateOf(false)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar(
                questionNumber = quizUiState.questionNumber,
                time = ":05",
                score = quizUiState.currentScore,
                onPause = { isPause = !isPause }
            )
        }
    ) {
        paddingValues ->

        QuestionView(
            quizViewModel = quizViewModel,
            modifier = Modifier.padding(paddingValues)
        )

        if (isPause) {
            PauseMenu(
                onDissmissRequest = { isPause = !isPause },
                onResumeQuiz = { isPause = !isPause },
                onExit = {
                    isPause = !isPause
                    onExitPauseMenu()
                }
            )
        }
    }
}
