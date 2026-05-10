package com.allison.oceanwise.viewmodels

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
                val firestoreQuestions = repository.getQuestions()
                if (firestoreQuestions.isNotEmpty()) {
                    _questions.value = firestoreQuestions
                } else {
                    _questions.value = getDefaultQuestions()
                }
            } catch (e: Exception) {
                _questions.value = getDefaultQuestions()
            }
        }
    }

    private fun getDefaultQuestions(): List<QuizQuestion> {
        return listOf(
            QuizQuestion(
                id = "1",
                question = "What percentage of the Earth's surface is covered by oceans?",
                options = listOf("50%", "71%", "85%", "60%"),
                correctAnswerIndex = 1
            ),
            QuizQuestion(
                id = "2",
                question = "Which is the largest ocean on Earth?",
                options = listOf("Atlantic", "Indian", "Arctic", "Pacific"),
                correctAnswerIndex = 3
            ),
            QuizQuestion(
                id = "3",
                question = "What is the largest animal in the ocean?",
                options = listOf("Great White Shark", "Giant Squid", "Blue Whale", "Orca"),
                correctAnswerIndex = 2
            ),
            QuizQuestion(
                id = "4",
                question = "What are coral reefs often called?",
                options = listOf("Jungles of the Sea", "Rainforests of the Sea", "Ocean Gardens", "Blue Deserts"),
                correctAnswerIndex = 1
            ),
            QuizQuestion(
                id = "5",
                question = "Which ocean is the coldest?",
                options = listOf("Pacific", "Atlantic", "Southern", "Arctic"),
                correctAnswerIndex = 3
            ),
            QuizQuestion(
                id = "6",
                question = "How much of the ocean has been explored by humans?",
                options = listOf("Less than 5%", "About 25%", "Exactly 50%", "Over 80%"),
                correctAnswerIndex = 0
            ),
            QuizQuestion(
                id = "7",
                question = "Which sea creature has three hearts?",
                options = listOf("Dolphin", "Octopus", "Shark", "Sea Turtle"),
                correctAnswerIndex = 1
            ),
            QuizQuestion(
                id = "8",
                question = "What is the deepest point in the ocean?",
                options = listOf("Tonga Trench", "Java Trench", "Mariana Trench", "Puerto Rico Trench"),
                correctAnswerIndex = 2
            ),
            QuizQuestion(
                id = "9",
                question = "Which of these is NOT a real type of shark?",
                options = listOf("Hammerhead", "Tiger", "Leopard", "Lion"),
                correctAnswerIndex = 3
            ),
            QuizQuestion(
                id = "10",
                question = "What provides most of the Earth's oxygen?",
                options = listOf("Rainforests", "Ocean Phytoplankton", "Grasslands", "Desert Plants"),
                correctAnswerIndex = 1
            ),
            QuizQuestion(
                id = "11",
                question = "Which ocean animal is often called the 'Sea Cow'?",
                options = listOf("Walrus", "Manatee", "Dugong", "Sea Lion"),
                correctAnswerIndex = 1
            ),
            QuizQuestion(
                id = "12",
                question = "How many arms does a common starfish (sea star) have?",
                options = listOf("5", "8", "10", "12"),
                correctAnswerIndex = 0
            ),
            QuizQuestion(
                id = "13",
                question = "What is the process of ocean water becoming more acidic due to CO2?",
                options = listOf("Salination", "Oxidation", "Ocean Acidification", "Thermal Expansion"),
                correctAnswerIndex = 2
            ),
            QuizQuestion(
                id = "14",
                question = "Which creature is known as the smartest invertebrate?",
                options = listOf("Dolphin", "Octopus", "Giant Squid", "Jellyfish"),
                correctAnswerIndex = 1
            ),
            QuizQuestion(
                id = "15",
                question = "What is the largest species of shark alive today?",
                options = listOf("Great White Shark", "Hammerhead Shark", "Whale Shark", "Bull Shark"),
                correctAnswerIndex = 2
            ),
            QuizQuestion(
                id = "16",
                question = "Which of these is a marine mammal?",
                options = listOf("Shark", "Tuna", "Blue Whale", "Sea Bass"),
                correctAnswerIndex = 2
            ),
            QuizQuestion(
                id = "17",
                question = "What is the main cause of coral bleaching?",
                options = listOf("Overfishing", "Rising water temperatures", "Plastic pollution", "Tides"),
                correctAnswerIndex = 1
            ),
            QuizQuestion(
                id = "18",
                question = "How do most fish breathe?",
                options = listOf("Lungs", "Skin", "Gills", "Blowholes"),
                correctAnswerIndex = 2
            ),
            QuizQuestion(
                id = "19",
                question = "Which ocean is home to the Great Barrier Reef?",
                options = listOf("Atlantic", "Indian", "Pacific", "Arctic"),
                correctAnswerIndex = 2
            ),
            QuizQuestion(
                id = "20",
                question = "What is a group of jellyfish called?",
                options = listOf("A school", "A pod", "A smack", "A pride"),
                correctAnswerIndex = 2
            ),
            QuizQuestion(
                id = "21",
                question = "Which ocean is the smallest?",
                options = listOf("Indian", "Arctic", "Atlantic", "Southern"),
                correctAnswerIndex = 1
            ),
            QuizQuestion(
                id = "22",
                question = "How many hearts does an octopus have?",
                options = listOf("1", "2", "3", "4"),
                correctAnswerIndex = 2
            ),
            QuizQuestion(
                id = "23",
                question = "What is the fastest fish in the ocean?",
                options = listOf("Sailfish", "Bluefin Tuna", "Great White Shark", "Swordfish"),
                correctAnswerIndex = 0
            ),
            QuizQuestion(
                id = "24",
                question = "Which sea creature can regrow its arms?",
                options = listOf("Crab", "Starfish", "Dolphin", "Seahorse"),
                correctAnswerIndex = 1
            ),
            QuizQuestion(
                id = "25",
                question = "What is the scientific name for the 'twilight zone' of the ocean?",
                options = listOf("Epipelagic", "Mesopelagic", "Bathypelagic", "Abyssopelagic"),
                correctAnswerIndex = 1
            ),
            QuizQuestion(
                id = "26",
                question = "Which of these is the largest turtle in the world?",
                options = listOf("Green Sea Turtle", "Loggerhead", "Leatherback", "Hawksbill"),
                correctAnswerIndex = 2
            ),
            QuizQuestion(
                id = "27",
                question = "What do you call a scientist who studies the ocean?",
                options = listOf("Geologist", "Meteorologist", "Oceanographer", "Biologist"),
                correctAnswerIndex = 2
            ),
            QuizQuestion(
                id = "28",
                question = "How many teeth can a shark lose in its lifetime?",
                options = listOf("Up to 100", "Up to 1,000", "Up to 30,000", "None"),
                correctAnswerIndex = 2
            ),
            QuizQuestion(
                id = "29",
                question = "What is the primary food source for Blue Whales?",
                options = listOf("Small Fish", "Plankton", "Krill", "Squid"),
                correctAnswerIndex = 2
            ),
            QuizQuestion(
                id = "30",
                question = "Which country has the longest coastline in the world?",
                options = listOf("Australia", "Canada", "USA", "Norway"),
                correctAnswerIndex = 1
            ),
            QuizQuestion(
                id = "31",
                question = "What is the largest coral reef system in the world?",
                options = listOf("Belize Barrier Reef", "Great Barrier Reef", "New Caledonia Barrier Reef", "Red Sea Coral Reef"),
                correctAnswerIndex = 1
            ),
            QuizQuestion(
                id = "32",
                question = "Which sea creature is known as the 'Unicorn of the Sea'?",
                options = listOf("Dolphin", "Narwhal", "Swordfish", "Beluga Whale"),
                correctAnswerIndex = 1
            ),
            QuizQuestion(
                id = "33",
                question = "What is the name of the phenomenon where the ocean glows at night?",
                options = listOf("Aurora Borealis", "Bioluminescence", "Phosphorescence", "Chemiluminescence"),
                correctAnswerIndex = 1
            ),
            QuizQuestion(
                id = "34",
                question = "Which ocean is the Bermuda Triangle located in?",
                options = listOf("Pacific", "Indian", "Arctic", "Atlantic"),
                correctAnswerIndex = 3
            ),
            QuizQuestion(
                id = "35",
                question = "What do whales use to communicate over long distances?",
                options = listOf("Echoes", "Songs", "Bubbles", "Tail Slaps"),
                correctAnswerIndex = 1
            ),
            QuizQuestion(
                id = "36",
                question = "Which of these is a flightless bird that lives in the ocean environment?",
                options = listOf("Seagull", "Albatross", "Penguin", "Pelican"),
                correctAnswerIndex = 2
            ),
            QuizQuestion(
                id = "37",
                question = "What is the most common salt found in ocean water?",
                options = listOf("Potassium Chloride", "Sodium Chloride", "Magnesium Sulfate", "Calcium Carbonate"),
                correctAnswerIndex = 1
            ),
            QuizQuestion(
                id = "38",
                question = "Which animal has the largest eyes in the world?",
                options = listOf("Blue Whale", "Giant Squid", "Great White Shark", "Elephant"),
                correctAnswerIndex = 1
            ),
            QuizQuestion(
                id = "39",
                question = "What is the name for the daily rise and fall of the ocean's surface?",
                options = listOf("Currents", "Waves", "Tides", "Swells"),
                correctAnswerIndex = 2
            ),
            QuizQuestion(
                id = "40",
                question = "Which ocean is completely surrounded by land on all sides (almost)?",
                options = listOf("Arctic", "Indian", "Atlantic", "Pacific"),
                correctAnswerIndex = 0
            )
        )
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
