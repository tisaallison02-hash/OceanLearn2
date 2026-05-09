package com.allison.oceanwise.data.repository

import com.allison.oceanwise.Firebase.FirebaseAuthManager
import com.allison.oceanwise.Firebase.FirestoreManager
import com.allison.oceanwise.data.model.User
import com.google.firebase.auth.FirebaseUser

class AuthRepository(
    private val authManager: FirebaseAuthManager,
    private val firestoreManager: FirestoreManager
) {
    val currentUser: FirebaseUser? get() = authManager.currentUser

    suspend fun login(email: String, pass: String): FirebaseUser? {
        return authManager.signIn(email, pass)
    }

    suspend fun register(name: String, email: String, pass: String): FirebaseUser? {
        val firebaseUser = authManager.signUp(email, pass)
        if (firebaseUser != null) {
            // Create the user profile in Firestore immediately after registration
            val newUser = User(
                uid = firebaseUser.uid,
                name = name,
                email = email,
                totalScore = 0
            )
            firestoreManager.saveUser(newUser)
        }
        return firebaseUser
    }

    fun logout() {
        authManager.signOut()
    }
}
