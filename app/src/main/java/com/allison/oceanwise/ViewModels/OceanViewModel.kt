package com.allison.oceanwise.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allison.oceanwise.data.model.MarineAnimal
import com.allison.oceanwise.data.repository.OceanRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OceanViewModel(private val repository: OceanRepository) : ViewModel() {

    private val _animals = MutableStateFlow<List<MarineAnimal>>(emptyList())
    val animals: StateFlow<List<MarineAnimal>> = _animals

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    fun fetchMarineAnimals() {
        viewModelScope.launch {
            _loading.value = true
            try {
                _animals.value = repository.getMarineAnimals()
            } catch (e: Exception) {
                // Handle error
            } finally {
                _loading.value = false
            }
        }
    }
}
