package com.allison.oceanwise.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.allison.oceanwise.Firebase.FirebaseAuthManager
import com.allison.oceanwise.Firebase.FirestoreManager
import com.allison.oceanwise.Screens.*
import com.allison.oceanwise.ViewModels.AuthViewModel
import com.allison.oceanwise.ViewModels.OceanViewModel
import com.allison.oceanwise.ViewModels.QuizViewModel
import com.allison.oceanwise.ViewModels.UserViewModel
import com.allison.oceanwise.data.repository.AuthRepository
import com.allison.oceanwise.data.repository.OceanRepository
import com.allison.oceanwise.data.repository.QuizRepository
import com.allison.oceanwise.data.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    val firestoreManager = remember { FirestoreManager() }
    val authManager = remember { FirebaseAuthManager() }
    val oceanRepository = remember { OceanRepository(firestoreManager) }
    val quizRepository = remember { QuizRepository(firestoreManager) }
    val userRepository = remember { UserRepository(firestoreManager) }
    val authRepository = remember { AuthRepository(authManager, firestoreManager) }
    
    val userViewModel: UserViewModel = viewModel(
        factory = GenericViewModelFactory { UserViewModel(userRepository) }
    )
    val oceanViewModel: OceanViewModel = viewModel(
        factory = GenericViewModelFactory { OceanViewModel(oceanRepository) }
    )
    val quizViewModel: QuizViewModel = viewModel(
        factory = GenericViewModelFactory { 
            QuizViewModel(quizRepository) { score ->
                userViewModel.addPoints(score)
            }
        }
    )
    val authViewModel: AuthViewModel = viewModel(
        factory = GenericViewModelFactory { AuthViewModel(authRepository) }
    )

    // Fetch user data if already logged in
    val firebaseUser = FirebaseAuth.getInstance().currentUser
    LaunchedEffect(firebaseUser) {
        firebaseUser?.let {
            userViewModel.fetchUser(it.uid)
        }
    }

    val startDestination = if (FirebaseAuth.getInstance().currentUser != null) {
        Route.HOME
    } else {
        Route.LOGIN
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Route.LOGIN) {
            LoginScreen(navController, authViewModel)
        }

        composable(Route.REGISTER) {
            RegisterScreen(navController, authViewModel)
        }

        composable(Route.HOME) {
            HomeScreen(navController)
        }

        composable(Route.LEARN_OCEANS) {
            LearnOceansScreen(navController)
        }

        composable(Route.MARINE_ANIMALS) {
            MarineAnimalsScreen(navController, oceanViewModel)
        }

        composable(Route.POLLUTION) {
            OceanPollutionScreen(navController)
        }

        composable(Route.QUIZ) {
            QuizScreen(navController, quizViewModel)
        }

        composable(Route.PROFILE) {
            ProfileScreen(navController, userViewModel)
        }
    }
}

class GenericViewModelFactory<T : ViewModel>(private val creator: () -> T) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return creator() as T
    }
}
