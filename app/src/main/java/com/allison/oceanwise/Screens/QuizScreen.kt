package com.allison.oceanwise.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.allison.oceanwise.ViewModels.QuizViewModel
import com.allison.oceanwise.Components.OceanButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(navController: NavController, viewModel: QuizViewModel) {
    val questions by viewModel.questions.collectAsState()
    val currentIndex by viewModel.currentQuestionIndex.collectAsState()
    val score by viewModel.score.collectAsState()
    val finished by viewModel.quizFinished.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ocean Quiz") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp)) {
            if (finished) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Quiz Finished! 🎉", style = MaterialTheme.typography.headlineMedium)
                    Text(text = "Your Score: $score / ${questions.size}", style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(24.dp))
                    OceanButton(text = "Restart Quiz", onClick = { viewModel.restartQuiz() })
                }
            } else if (questions.isNotEmpty()) {
                val currentQuestion = questions[currentIndex]
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    LinearProgressIndicator(
                        progress = { (currentIndex + 1).toFloat() / questions.size },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(text = currentQuestion.question, style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(32.dp))
                    
                    currentQuestion.options.forEachIndexed { index, option ->
                        OceanButton(
                            text = option,
                            onClick = { viewModel.submitAnswer(index) },
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            } else {
                Text(text = "Loading questions...", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}
