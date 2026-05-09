package com.allison.oceanwise.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allison.oceanwise.data.model.QuizQuestion
import com.allison.oceanwise.data.repository.QuizRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QuizViewModel(
    private val repository: QuizRepository,
    private val onQuizComplete: (Int) -> Unit = {}
) : ViewModel() {

    private val _questions = MutableStateFlow<List<QuizQuestion>>(emptyList())
    val questions: StateFlow<List<QuizQuestion>> = _questions

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex

    private val _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score

    private val _quizFinished = MutableStateFlow(false)
    val quizFinished: StateFlow<Boolean> = _quizFinished

    init {
        fetchQuestions()
    }

    private fun fetchQuestions() {
        viewModelScope.launch {
            try {
                _questions.value = repository.getQuestions()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun submitAnswer(answerIndex: Int) {
        val currentQuestion = _questions.value.getOrNull(_currentQuestionIndex.value)
        if (currentQuestion != null && answerIndex == currentQuestion.correctAnswerIndex) {
            _score.value += 1
        }

        if (_currentQuestionIndex.value < _questions.value.size - 1) {
            _currentQuestionIndex.value += 1
        } else {
            _quizFinished.value = true
            onQuizComplete(_score.value)
        }
    }

    fun restartQuiz() {
        _currentQuestionIndex.value = 0
        _score.value = 0
        _quizFinished.value = false
        fetchQuestions()
    }
}
