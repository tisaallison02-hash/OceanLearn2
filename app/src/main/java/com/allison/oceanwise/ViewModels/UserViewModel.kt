package com.allison.oceanwise.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import android.content.Context
import android.net.Uri
import com.allison.oceanwise.cloudinary.ImageUploader
import com.allison.oceanwise.data.model.User
import com.allison.oceanwise.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _uploading = MutableStateFlow(false)
    val uploading: StateFlow<Boolean> = _uploading

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message

    fun fetchUser(uid: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                _currentUser.value = repository.getUser(uid)
            } catch (e: Exception) {
                // Handle error
            } finally {
                _loading.value = false
            }
        }
    }

    fun saveUser(user: User) {
        viewModelScope.launch {
            try {
                repository.saveUser(user)
                _currentUser.value = user
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun uploadProfileImage(context: Context, uri: Uri) {
        val user = _currentUser.value ?: return
        _uploading.value = true
        _message.value = null
        ImageUploader.uploadImage(
            context = context,
            imageUri = uri,
            onSuccess = { imageUrl ->
                val updatedUser = user.copy(profileImage = imageUrl)
                saveUser(updatedUser)
                _uploading.value = false
                _message.value = "Profile picture updated successfully! ✨"
            },
            onError = { error ->
                _uploading.value = false
                _message.value = "Upload failed: $error"
            }
        )
    }

    fun clearMessage() {
        _message.value = null
    }

    fun addPoints(points: Int) {
        val user = _currentUser.value ?: return
        val updatedUser = user.copy(totalScore = user.totalScore + points)
        saveUser(updatedUser)
    }
}
