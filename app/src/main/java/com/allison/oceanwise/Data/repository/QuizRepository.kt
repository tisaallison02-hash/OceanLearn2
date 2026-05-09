package com.allison.oceanwise.data.repository

import com.allison.oceanwise.Firebase.FirestoreManager
import com.allison.oceanwise.data.model.QuizQuestion

class QuizRepository(private val firestoreManager: FirestoreManager) {
    suspend fun getQuestions(): List<QuizQuestion> {
        return firestoreManager.getQuizQuestions()
    }
}
