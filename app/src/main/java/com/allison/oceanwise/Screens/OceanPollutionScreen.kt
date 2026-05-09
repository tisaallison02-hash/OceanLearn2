package com.allison.oceanwise.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OceanPollutionScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ocean Pollution") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "The Crisis in Our Oceans 🌍",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.error
                )
            }

            item {
                PollutionItem(
                    title = "Plastic Waste",
                    description = "Over 8 million tons of plastic enter our oceans every year, killing marine life and entering the food chain.",
                    icon = Icons.Default.Warning
                )
            }

            item {
                PollutionItem(
                    title = "Chemical Runoff",
                    description = "Industrial and agricultural waste creates 'dead zones' where no marine life can survive.",
                    icon = Icons.Default.Warning
                )
            }

            item {
                PollutionItem(
                    title = "Oil Spills",
                    description = "Large-scale oil spills destroy habitats and are extremely difficult to clean up, lasting for decades.",
                    icon = Icons.Default.Warning
                )
            }
        }
    }
}

@Composable
fun PollutionItem(title: String, description: String, icon: ImageVector) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = title, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onErrorContainer)
                Text(text = description, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
