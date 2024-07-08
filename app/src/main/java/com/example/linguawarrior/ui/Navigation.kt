package com.example.linguawarrior.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.linguawarrior.ui.screens.answers.RevealAnswersScreen
import com.example.linguawarrior.ui.screens.game.GameScreen
import com.example.linguawarrior.ui.screens.start.StartScreen


enum class LinguaWarriorScreen() {
    Start,
    Game,
    ReviewAnswers
}

lateinit var gameViewModel: GameViewModel

@Composable
fun LinguaWarriorApp(
    sharedViewModel: SharedViewModel = viewModel(),
    navController : NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = LinguaWarriorScreen.Start.name
    ) {
        composable(route = LinguaWarriorScreen.Start.name ) {
            StartScreen(
                sharedViewModel = sharedViewModel,
                onConfirmation = {
                    gameViewModel = GameViewModel(questions = sharedViewModel.fetchQuestions())
                    navController.navigate(route = LinguaWarriorScreen.Game.name)
                }
            )
        }

        composable(route =  LinguaWarriorScreen.Game.name) {
            GameScreen(
                sharedViewModel = sharedViewModel,
                gameViewModel = gameViewModel,
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