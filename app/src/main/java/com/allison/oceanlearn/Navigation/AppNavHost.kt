package com.allison.oceanlearn.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.allison.oceanlearn.screens.*

@Composable
fun AppNavHost(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Route.HOME
    ) {

        composable(Route.HOME) {
            HomeScreen(navController)
        }

        composable(Route.LEARN_OCEANS) {
            LearnOceansScreen(navController)
        }

        composable(Route.MARINE_ANIMALS) {
            MarineAnimalsScreen(navController)
        }

        composable(Route.POLLUTION) {
            OceanPollutionScreen(navController)
        }

        composable(Route.QUIZ) {
            QuizScreen(navController)
        }
    }
}

