package com.allison.oceanwise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.allison.oceanwise.cloudinary.CloudinaryConfig
import com.allison.oceanwise.navigation.AppNavHost
import com.allison.oceanwise.ui.theme.OceanWiseTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)
        CloudinaryConfig.initialize(this)

        enableEdgeToEdge()

        setContent {
            OceanWiseTheme {

                val navController = rememberNavController()

                AppNavHost(navController = navController)
            }
        }
    }
}
