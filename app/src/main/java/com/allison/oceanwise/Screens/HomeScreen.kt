package com.allison.oceanwise.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.allison.oceanwise.components.OceanButton
import com.allison.oceanwise.navigation.Route

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "OceanLearn 🌊",
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Explore. Learn. Protect.",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        
        OceanButton(
            text = "Learn About Oceans",
            onClick = { navController.navigate(Route.LEARN_OCEANS) }
        )
        Spacer(modifier = Modifier.height(12.dp))
        
        OceanButton(
            text = "Marine Animals",
            onClick = { navController.navigate(Route.MARINE_ANIMALS) }
        )
        Spacer(modifier = Modifier.height(12.dp))
        
        OceanButton(
            text = "Ocean Pollution",
            onClick = { navController.navigate(Route.POLLUTION) }
        )
        Spacer(modifier = Modifier.height(12.dp))
        
        OceanButton(
            text = "Take a Quiz",
            onClick = { navController.navigate(Route.QUIZ) }
        )
        Spacer(modifier = Modifier.height(32.dp))

        TextButton(onClick = { navController.navigate(Route.PROFILE) }) {
            Text("View My Profile")
        }
    }
}
