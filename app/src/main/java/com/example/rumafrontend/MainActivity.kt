package com.example.rumafrontend

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.rumafrontend.ui.theme.rumaFrontendTheme
import com.example.rumafrontend.ui.theme.screen.loginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            rumaFrontendTheme {
                loginScreen(
                    onLoginClick = {email, password ->
                         doLogin(
                             email = email,
                             password = password,
                         )
                    }
                )

            }
        }
    }

    fun doLogin(email: String, password: String){

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    rumaFrontendTheme {
        Greeting("Android")
    }
    }
