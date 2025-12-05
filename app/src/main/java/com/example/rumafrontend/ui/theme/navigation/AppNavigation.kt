package com.example.rumafrontend.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rumafrontend.ui.theme.screen.login.LoginScreen
import com.example.rumafrontend.ui.theme.screen.profile.EditProfilScreen
import com.example.rumafrontend.ui.theme.screen.profile.ProfileScreen


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
            ProfileScreen{
                navController.navigate("editProfile")
            }
        }

            composable("editProfile"){
                EditProfilScreen()
            }
        }
    }

