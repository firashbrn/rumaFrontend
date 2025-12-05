package com.example.rumafrontend.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rumafrontend.ui.theme.screen.login.LoginScreen
import com.example.rumafrontend.ui.theme.screen.profile.ProfileScreen
import com.example.rumafrontend.ui.theme.screen.profile.ProfileViewModel


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ){
        composable("login") {
            LoginScreen (
                onLoginSuccess = {
                    navController.navigate("profile"){
                        popUpTo("login") {inclusive = true}
                    }
                }
            )
        }

        composable ("profile"){
            // 2. ViewModel dibuat/ditemukan oleh framework Compose
            val profileViewModel: ProfileViewModel = viewModel()

            ProfileScreen(
                viewModel = profileViewModel, // Teruskan ViewModel
                navigateToEdit = {
                    navController.navigate("editProfile")
                }, onLogoutSuccess = {
                    navController.navigate("login"){
                        popUpTo("profile") { inclusive = true } // Clear Profile stack
                    }
                },
                navController = navController
            )
        }
        }
}



@Composable
fun AgendaScreen(content: @Composable () -> Unit) {
    TODO("Not yet implemented")
}

