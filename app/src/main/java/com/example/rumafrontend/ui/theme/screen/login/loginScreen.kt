package com.example.rumafrontend.ui.theme.screen.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rumafrontend.data.model.loginRequest
import com.example.rumafrontend.network.ApiClient
import com.example.rumafrontend.ui.theme.rumaFrontendTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun LoginScreen( loginViewModel: LoginViewModel = viewModel(),
    onLoginSuccess: () -> Unit) {

    var email by remember { mutableStateOf(value = "") }
    var password by remember { mutableStateOf(value = "") }
    val loading by loginViewModel.loading.collectAsState()
    val loginStatus by loginViewModel.loginStatus.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(loginStatus) {
        if (loginStatus == "Success") {
            Toast.makeText(context, "Login berhasil!", Toast.LENGTH_SHORT).show()
            onLoginSuccess()
        } else if (loginStatus != null) {
            Toast.makeText(context, loginStatus, Toast.LENGTH_SHORT).show()
        }
    }

    Surface (
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF2ECDC)
    ){
        Column (
            modifier = Modifier.fillMaxSize()
                .padding(
                    horizontal = 16.dp, vertical = 24.dp
                )
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {

            Text("Login Here", style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
            ),
                color = Color(0xFF7E2625))
            Spacer(Modifier.height(10.dp))
            Text("Selamat datang kembali!", style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
            ),
                color = Color(0xFF868859))
            Spacer(Modifier.height(32.dp))
            OutlinedTextField(
                value = email,
                onValueChange = {email = it},
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            )
            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                value = password,
                onValueChange = {password = it},
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            )
            Spacer(Modifier.height(30.dp))
            Button(
                onClick = {loginViewModel.login(email,password)},
                enabled = email.isNotBlank() && password.isNotBlank(),
                modifier = Modifier.fillMaxWidth().height(70.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF7E2625),
                    contentColor = Color(0xFFF2ECDC),
                    disabledContainerColor = Color.LightGray,
                    disabledContentColor = Color.DarkGray
                )
            ){
                Text("Masuk")
            }
            if (loading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = Color(0xFF7E2625)
                    )
                }
            }

        }
    }

}


@Preview(showBackground = true)
@Composable
private fun LoginPreview() {
    rumaFrontendTheme {
        LoginScreen(
            onLoginSuccess = {}
        )
    }
}






