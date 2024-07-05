package com.example.linguawarrior.ui.screens.game

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.linguawarrior.ui.GameViewModel
import com.example.linguawarrior.ui.SharedViewModel
import com.example.linguawarrior.ui.components.QuestionView

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    sharedViewModel: SharedViewModel,
    gameViewModel: GameViewModel,
    onExitPauseMenu : () -> Unit,
    onReviewAnswer : () -> Unit,
) {
    val gameUiState by gameViewModel.uiState.collectAsState()

    var isPause by rememberSaveable {
        mutableStateOf(false)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar(
                questionNumber = gameUiState.questionNumber,
                time = gameUiState.time,
                score = gameUiState.currentScore,
                onPause = {
                    gameViewModel.pauseTimer()
                    isPause = !isPause
                }
            )
        }
    ) {
        paddingValues ->

        QuestionView(
            word = gameUiState.currentQuestion.word,
            options = gameUiState.currentQuestion.options,
            canClick = gameUiState.canClick,
            onOptionSelected = {
                gameViewModel.checkUserAnswer(
                    option = it
                )
                sharedViewModel.updateAnswer(
                    option = it,
                    currentQuestion = gameUiState.currentQuestion,
                    questionNumber = gameUiState.questionNumber
                )
                               },
            answer = gameUiState.currentQuestion.answer,
            selected = gameUiState.selected,
            modifier = Modifier.padding(paddingValues)
        )

        if (isPause) {
            PauseMenu(
                onResumeQuiz = {
                    gameViewModel.resumeTimer()
                    isPause = !isPause },
                onExit = {
                    isPause = !isPause
                    onExitPauseMenu()
                },
                onNewTrivia = {
                    isPause = !isPause
                    gameViewModel.resetGame()
                }
            )
        }
        
        if (gameUiState.quizEnd) {
            ResultDialog(
                score = gameUiState.currentScore,
                answeredCorrectly = gameUiState.answeredCorrectly,
                replayTrivia = { gameViewModel.resetGame() },
                revealAnswers = onReviewAnswer,
                onExit = { onExitPauseMenu() })
        }
    }
}
