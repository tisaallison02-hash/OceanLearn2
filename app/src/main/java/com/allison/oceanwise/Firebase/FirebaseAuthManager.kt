package com.allison.oceanwise.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class FirebaseAuthManager {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    val currentUser: FirebaseUser?
        get() = auth.currentUser

    suspend fun signUp(email: String, pass: String): FirebaseUser? {
        // We let the exception propagate so we can catch the real error message in the ViewModel
        val result = auth.createUserWithEmailAndPassword(email, pass).await()
        return result.user
    }

    suspend fun signIn(email: String, pass: String): FirebaseUser? {
        val result = auth.signInWithEmailAndPassword(email, pass).await()
        return result.user
    }

    fun signOut() {
        auth.signOut()
    }
}
