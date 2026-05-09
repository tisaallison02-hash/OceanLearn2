package com.allison.oceanwise.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class FirebaseAuthManager {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    val currentUser: FirebaseUser?
        get() = auth.currentUser

    suspend fun signInAnonymously(): FirebaseUser? {
        return try {
            val result = auth.signInAnonymously().await()
            result.user
        } catch (e: Exception) {
            null
        }
    }

    suspend fun signUp(email: String, pass: String): FirebaseUser? {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, pass).await()
            result.user
        } catch (e: Exception) {
            null
        }
    }

    suspend fun signIn(email: String, pass: String): FirebaseUser? {
        return try {
            val result = auth.signInWithEmailAndPassword(email, pass).await()
            result.user
        } catch (e: Exception) {
            null
        }
    }

    fun signOut() {
        auth.signOut()
    }
}
