package com.example.linguawarrior.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.linguawarrior.ui.screens.answers.RevealAnswersScreen
import com.example.linguawarrior.ui.screens.game.GameScreen
import com.example.linguawarrior.ui.screens.game.GameUiEvent
import com.example.linguawarrior.ui.screens.start.StartScreen
import com.example.linguawarrior.ui.shared.viewmodel.SharedViewModel


enum class LinguaWarriorScreen() {
    Start,
    Game,
    ReviewAnswers
}

@Composable
fun LinguaWarriorApp(
    sharedViewModel: SharedViewModel = viewModel(),
    gameViewModel : GameViewModel = viewModel(),
    navController : NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = LinguaWarriorScreen.Start.name
    ) {
        composable(route = LinguaWarriorScreen.Start.name ) {
            StartScreen(
                onSharedEvent = {event -> sharedViewModel.onEvent(event)},
                onConfirmation = {
                    gameViewModel.initializeGame(questionDataset = sharedViewModel.fetchQuestions())
                    navController.navigate(route = LinguaWarriorScreen.Game.name)
                }
            )
        }

        composable(route =  LinguaWarriorScreen.Game.name) {
            val gameUiState by gameViewModel.uiState.collectAsState()
            GameScreen(
                sharedViewModel = sharedViewModel,
                gameViewModel = gameViewModel,
                gameUiState = gameUiState,
                onEvent = { event -> gameViewModel.onEvent(event) },
                onSharedUiEvent = {event -> sharedViewModel.onEvent(event)},
                onExitPauseMenu = { navController.navigate(route = LinguaWarriorScreen.Start.name) },
                onReviewAnswer = { navController.navigate(route = LinguaWarriorScreen.ReviewAnswers.name) }
            )
        }

        composable(route = LinguaWarriorScreen.ReviewAnswers.name) {
            RevealAnswersScreen(
                sharedViewModel = sharedViewModel,
                navigateUp = { navController.navigateUp() }
            )
        }
    }
}