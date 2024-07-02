package com.example.linguawarrior.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.linguawarrior.ui.screens.Game.GameScreen
import com.example.linguawarrior.ui.screens.Start.StartScreen


enum class LinguaWarriorScreen() {
    Start,
    Game
}
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
                    navController.navigate(route = LinguaWarriorScreen.Game.name)
                    sharedViewModel.resetGame()
                }
            )
        }

        composable(route =  LinguaWarriorScreen.Game.name) {
            GameScreen(
                sharedViewModel = sharedViewModel,
                onExitPauseMenu = { navController.navigate(route = LinguaWarriorScreen.Start.name) }
            )
        }
    }
}