package com.allison.oceanwise.cloudinary

import android.content.Context
import android.net.Uri
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback

object ImageUploader {

    fun uploadImage(
        context: Context,
        imageUri: Uri,
        onSuccess: (imageUrl: String) -> Unit,
        onError: (message: String) -> Unit
    ) {
        MediaManager.get().upload(imageUri)
            .unsigned("oceanview")
            .option("cloud_name", "dhqlofkbb")
            .callback(object : UploadCallback {
                override fun onStart(requestId: String) {
                    // Upload started
                }

                override fun onProgress(requestId: String, bytes: Long, totalBytes: Long) {
                    // Progress
                }

                override fun onSuccess(requestId: String, resultData: Map<*, *>) {
                    val imageUrl = resultData["secure_url"] as? String ?: ""
                    onSuccess(imageUrl)
                }

                override fun onError(requestId: String, error: ErrorInfo) {
                    onError(error.description)
                }

                override fun onReschedule(requestId: String, error: ErrorInfo) {
                    // Rescheduled
                }
            }).dispatch()
    }
}
