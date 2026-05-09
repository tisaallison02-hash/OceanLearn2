package com.allison.oceanlearn.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.allison.oceanlearn.navigation.Route

@Composable
fun HomeScreen(navController: NavHostController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("🌊 OceanLearn Home")

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { navController.navigate(Route.LEARN_OCEANS) }) {
            Text("Learn Oceans")
        }

        Button(onClick = { navController.navigate(Route.MARINE_ANIMALS) }) {
            Text("Marine Animals")
        }

        Button(onClick = { navController.navigate(Route.POLLUTION) }) {
            Text("Ocean Pollution")
        }

        Button(onClick = { navController.navigate(Route.QUIZ) }) {
            Text("Quiz")
        }
    }
}
