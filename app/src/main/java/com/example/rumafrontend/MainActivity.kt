package com.example.rumafrontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.rumafrontend.ui.theme.navigation.AppNavigation
import com.example.rumafrontend.ui.theme.rumaFrontendTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            rumaFrontendTheme {
                AppNavigation()
                    }
            }
        }
    }





