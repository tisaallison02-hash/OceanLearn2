package com.allison.oceanwise.data.repository

import com.allison.oceanwise.Firebase.FirestoreManager
import com.allison.oceanwise.data.model.User

class UserRepository(private val firestoreManager: FirestoreManager) {
    suspend fun saveUser(user: User) {
        firestoreManager.saveUser(user)
    }

    suspend fun getUser(uid: String): User? {
        return firestoreManager.getUser(uid)
    }
}
