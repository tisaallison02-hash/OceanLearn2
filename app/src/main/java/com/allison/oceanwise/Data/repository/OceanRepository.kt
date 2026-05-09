package com.allison.oceanwise.data.repository

import com.allison.oceanwise.Firebase.FirestoreManager
import com.allison.oceanwise.data.model.MarineAnimal

class OceanRepository(private val firestoreManager: FirestoreManager) {
    suspend fun getMarineAnimals(): List<MarineAnimal> {
        return firestoreManager.getMarineAnimals()
    }
}
