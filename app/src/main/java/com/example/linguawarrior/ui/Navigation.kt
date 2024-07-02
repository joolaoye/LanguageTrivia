package com.example.linguawarrior.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.linguawarrior.ui.screens.QuestionScreen
import com.example.linguawarrior.ui.screens.Start.StartScreen


enum class LinguaWarriorScreen() {
    Start,
    QuizScreen
}
@Composable
fun LinguaWarriorApp(
    navController : NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = LinguaWarriorScreen.Start.name
    ) {
        composable(route = LinguaWarriorScreen.Start.name ) {
            StartScreen(
                onConfirmation = { navController.navigate(route = LinguaWarriorScreen.QuizScreen.name)}
            )
        }

        composable(route =  LinguaWarriorScreen.QuizScreen.name) {
            QuestionScreen(
                onExitPauseMenu = { navController.navigate(route = LinguaWarriorScreen.Start.name) }
            )
        }
    }
}