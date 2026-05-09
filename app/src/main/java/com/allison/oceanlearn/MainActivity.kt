package com.allison.oceanlearn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.allison.oceanlearn.navigation.AppNavHost
import com.allison.oceanlearn.ui.theme.OceanLearnTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            OceanLearnTheme {

                val navController = rememberNavController()

                AppNavHost(navController = navController)
            }
        }
    }
}