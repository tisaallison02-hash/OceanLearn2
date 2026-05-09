package com.allison.oceanwise.Firebase

import com.allison.oceanwise.data.model.MarineAnimal
import com.allison.oceanwise.data.model.QuizQuestion
import com.allison.oceanwise.data.model.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirestoreManager {
    private val db = FirebaseFirestore.getInstance()

    suspend fun saveUser(user: User) {
        db.collection(FirebaseReferences.USERS).document(user.uid).set(user).await()
    }

    suspend fun getUser(uid: String): User? {
        return db.collection(FirebaseReferences.USERS).document(uid).get().await().toObject(User::class.java)
    }

    suspend fun getMarineAnimals(): List<MarineAnimal> {
        return db.collection(FirebaseReferences.MARINE_ANIMALS).get().await().toObjects(MarineAnimal::class.java)
    }

    suspend fun getQuizQuestions(): List<QuizQuestion> {
        return db.collection(FirebaseReferences.QUIZ_QUESTIONS).get().await().toObjects(QuizQuestion::class.java)
    }
}
