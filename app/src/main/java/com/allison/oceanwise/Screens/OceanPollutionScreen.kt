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
import com.allison.oceanwise.ui.theme.TropicalTurquoise

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OceanPollutionScreen(navController: NavController) {
    Scaffold(
        containerColor = TropicalTurquoise,
        topBar = {
            TopAppBar(
                title = { Text("Ocean Pollution & Solutions") },
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
                    description = "Over 8 million tons of plastic enter our oceans every year. It breaks down into microplastics that enter the food chain, harming everything from plankton to whales.",
                    solution = "Solution: Reduce single-use plastics, support plastic bans, and participate in beach cleanups.",
                    icon = Icons.Default.Warning
                )
            }

            item {
                PollutionItem(
                    title = "Chemical Runoff",
                    description = "Industrial waste and agricultural fertilizers create 'dead zones' where oxygen levels are too low for marine life to survive.",
                    solution = "Solution: Use eco-friendly fertilizers, support sustainable farming, and advocate for stricter industrial regulations.",
                    icon = Icons.Default.Warning
                )
            }

            item {
                PollutionItem(
                    title = "Oil Spills",
                    description = "Oil spills coat the feathers of birds and the fur of mammals, destroying their waterproofing and causing hypothermia or death.",
                    solution = "Solution: Transition to renewable energy and improve safety protocols for offshore drilling and transport.",
                    icon = Icons.Default.Warning
                )
            }

            item {
                PollutionItem(
                    title = "Noise Pollution",
                    description = "Loud noises from ships, sonar, and oil exploration disrupt the communication and navigation of whales and dolphins.",
                    solution = "Solution: Implement quiet ship technologies and restrict sonar use in sensitive marine habitats.",
                    icon = Icons.Default.Warning
                )
            }

            item {
                PollutionItem(
                    title = "Climate Change & Acidification",
                    description = "The ocean absorbs CO2, causing it to become more acidic. This dissolves the shells of shellfish and corals.",
                    solution = "Solution: Reduce your carbon footprint by using public transport, eating less meat, and supporting green energy.",
                    icon = Icons.Default.Warning
                )
            }
            
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "What You Can Do Today ✨",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("1. Use a reusable water bottle and bag.")
                        Text("2. Avoid products with microbeads.")
                        Text("3. Choose sustainable seafood.")
                        Text("4. Share what you learn on OceanLearn!")
                    }
                }
            }
        }
    }
}

@Composable
fun PollutionItem(title: String, description: String, solution: String, icon: ImageVector) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.error)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = title, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onErrorContainer)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = description, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Divider(color = MaterialTheme.colorScheme.error.copy(alpha = 0.2f))
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = solution,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )
        }
    }
}
