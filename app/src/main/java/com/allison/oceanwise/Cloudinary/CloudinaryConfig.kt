package com.allison.oceanwise.cloudinary

import android.content.Context
import com.cloudinary.android.MediaManager

object CloudinaryConfig {
    fun initialize(context: Context) {
        val config = mapOf(
            "cloud_name" to "dhqlofkbb",
            "secure" to true
        )
        try {
            MediaManager.init(context, config)
        } catch (e: Exception) {
            // Already initialized. If you changed the cloud name, 
            // you must 'Force Stop' the app for changes to take effect.
        }
    }
}
