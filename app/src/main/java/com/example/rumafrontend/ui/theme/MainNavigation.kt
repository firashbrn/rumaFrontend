package com.example.rumafrontend.ui.theme


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rumafrontend.ui.theme.screen.ShoppingListScreen
import com.example.rumafrontend.ui.theme.screen.loginScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            loginScreen { email, password ->
                // Navigasi ke ShoppingListScreen setelah login
                navController.navigate("shoppingList") {
                    popUpTo("login") { inclusive = true }
                }
            }
        }

        composable("shoppingList") {
            ShoppingListScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
