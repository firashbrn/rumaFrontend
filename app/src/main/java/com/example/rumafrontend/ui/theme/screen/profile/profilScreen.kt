package com.example.rumafrontend.ui.theme.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rumafrontend.R
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController


// --- Komponen Kustom ---
@Composable
fun ProfileInputField(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = label, style = MaterialTheme.typography.labelMedium)
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = value,
            onValueChange = { /* Tidak bisa diubah */ },
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFF2ECDC),
                focusedBorderColor = Color(0xFF3c1b0f)
            )
        )
    }
}

// --- Screen Utama ---

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel (),
    navigateToEdit: () -> Unit,
    onLogoutSuccess: () -> Unit,
    navController: NavController
) {
    // Collect State dari ViewModel
    val state by viewModel.uiState.collectAsState()
    val user = state.userModel


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        containerColor = Color(0xFFFBF5EB) // Warna latar belakang seperti desain
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 32.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Bagian Foto Profil
            ProfilePicture(user?.imageUrl)

            Spacer(modifier = Modifier.height(16.dp))

            // Tombol Edit
            Button(
                onClick = navigateToEdit,
                modifier = Modifier.width(100.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7E2625))
            ) {
                Text("Edit")
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Info Username & Email
            ProfileInputField(label = "Username", value = user?.username ?: "")
            Spacer(modifier = Modifier.height(16.dp))
            ProfileInputField(label = "Email", value = user?.email ?: "")

            Spacer(modifier = Modifier.height(32.dp))

            // Dark Mode Switch
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Dark Mode", fontWeight = FontWeight.Medium)
                Switch(
                    checked = state.isDarkMode,
                    onCheckedChange = { viewModel.onDarkModeToggle(it) },
                    colors = SwitchDefaults.colors(checkedTrackColor = Color(0xFF625b71))
                )
            }

            Spacer(modifier = Modifier.weight(1f)) // Dorong ke bawah

            if (user?.token == null) {
                // Panggil callback ke NavHost untuk menavigasi ke Login
                LaunchedEffect(key1 = Unit) {
                    onLogoutSuccess()
                }
            }
            // Tombol Log Out
            Button(
                onClick = { viewModel.onLogoutClicked() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7E2625))
            ) {
                Text("Log Out", style = MaterialTheme.typography.titleMedium, color = Color.White)
            }
        }
    }

    // Dialog Log Out
    if (state.showLogoutDialog) {
        LogoutConfirmationDialog(viewModel = viewModel)
    }
}

@Composable
fun ProfilePicture(imageUrl: String?) {
    // Gunakan Coil/Glide untuk memuat gambar dari URL (imageUrl)
    // Untuk contoh ini, kita gunakan placeholder lokal.
    Box(
        modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
            .background(Color.LightGray)
    ) {
        Image(
            painter = painterResource(id = R.drawable.fotoprofile), // Ganti dengan resource placeholder Anda
            contentDescription = "Foto Profil",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun LogoutConfirmationDialog(viewModel: ProfileViewModel) {
    AlertDialog(
        onDismissRequest = { viewModel.dismissLogoutDialog() },
        containerColor = Color(0xFFFBF5EB),
        title = {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Yakin Ingin Log Out??",
                    fontWeight = FontWeight.Bold,
                )
            }
        },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { viewModel.confirmLogout() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7E2625))
                ) {
                    Text("Yakin")
                }
                Button(
                    onClick = { viewModel.dismissLogoutDialog() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7E2625))
                ) {
                    Text("Ga jadi")
                }
            }
        },
    )
}