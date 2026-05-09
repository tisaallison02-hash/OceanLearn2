package com.allison.oceanwise.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearnOceansScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Learn About Oceans") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Discover the Wonders of the Deep 🌊",
                style = MaterialTheme.typography.headlineSmall
            )

            OceanFactCard(
                title = "Coral Reefs",
                fact = "Coral reefs are homes for many sea animals. They are often called the 'rainforests of the sea'."
            )

            OceanFactCard(
                title = "Plastic Pollution",
                fact = "Plastic pollution harms ocean life. Millions of tons of plastic enter the ocean every year."
            )

            OceanFactCard(
                title = "The Pacific Ocean",
                fact = "The Pacific Ocean is the largest and deepest ocean on Earth, covering about one-third of the planet's surface."
            )
            
            OceanFactCard(
                title = "Ocean Depth",
                fact = "The average depth of the ocean is about 12,100 feet. The deepest part is the Mariana Trench, which is nearly 7 miles deep!"
            )
        }
    }
}

@Composable
fun OceanFactCard(title: String, fact: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = fact, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
