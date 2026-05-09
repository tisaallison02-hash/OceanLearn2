package com.allison.oceanwise.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.allison.oceanwise.components.OceanCard
import com.allison.oceanwise.data.model.MarineAnimal
import com.allison.oceanwise.viewmodels.OceanViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarineAnimalsScreen(navController: NavController, viewModel: OceanViewModel) {
    val animals by viewModel.animals.collectAsState()
    val loading by viewModel.loading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchMarineAnimals()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Marine Life Explorer") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            if (loading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (animals.isEmpty()) {
                Text(
                    text = "No animals found. Update your Firestore!",
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(animals) { animal ->
                        AnimalItem(animal)
                    }
                }
            }
        }
    }
}

@Composable
fun AnimalItem(animal: MarineAnimal) {
    OceanCard(
        title = animal.name,
        imageUrl = animal.imageUrl.ifEmpty { null }
    ) {
        Column {
            if (animal.scientificName.isNotEmpty()) {
                Text(
                    text = animal.scientificName,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(text = animal.description, style = MaterialTheme.typography.bodyMedium)
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                InfoChip(label = "Habitat", value = animal.habitat)
                if (animal.conservationStatus.isNotEmpty()) {
                    InfoChip(label = "Status", value = animal.conservationStatus)
                }
            }
            
            if (animal.funFact.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer
                    ),
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = "💡 Fun Fact: ${animal.funFact}",
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@Composable
fun InfoChip(label: String, value: String) {
    Column {
        Text(text = label, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
        Text(text = value, style = MaterialTheme.typography.bodySmall)
    }
}
