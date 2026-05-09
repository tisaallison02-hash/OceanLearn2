package com.allison.oceanwise.cloudinary

import android.content.Context
import com.cloudinary.android.MediaManager

object CloudinaryConfig {
    fun initialize(context: Context) {
        val config = mapOf(
            "cloud_name" to "your_cloud_name",
            "api_key" to "your_api_key",
            "api_secret" to "your_api_secret"
        )
        try {
            MediaManager.init(context, config)
        } catch (e: Exception) {
            // Already initialized or error
        }
    }
}
