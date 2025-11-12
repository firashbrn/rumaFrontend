package com.example.rumafrontend.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rumafrontend.ViewModel.AgendaViewModel.AgendaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAgendaScreen(navController: NavController, viewModel: AgendaViewModel) {
    var kategori by remember { mutableStateOf("") }
    var judul by remember { mutableStateOf("") }
    var deskripsi by remember { mutableStateOf("") }
    var tanggal by remember { mutableStateOf("") }
    var waktu by remember { mutableStateOf("") }
    var lokasi by remember { mutableStateOf("") }
    var pengingat by remember { mutableStateOf("") }

    val kategoriList = listOf("Momen Spesial", "Liburan & Rekreasi", "Kumpul Keluarga", "Kegiatan Rumah")
    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Tambah Agenda",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Kembali", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF712626)
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFA2A175))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 🔹 Dropdown Kategori
            Box {
                TextButton(onClick = { expanded = true }) {
                    Text("Kategori: ${if (kategori.isEmpty()) "Pilih Kategori" else kategori}", color = Color.White)
                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, tint = Color.White)
                }
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    kategoriList.forEach {
                        DropdownMenuItem(
                            text = { Text(it) },
                            onClick = {
                                kategori = it
                                expanded = false
                            }
                        )
                    }
                }
            }

            // 🔹 Input Fields
            TextField(value = judul, onValueChange = { judul = it }, label = { Text("Judul") }, modifier = Modifier.fillMaxWidth())
            TextField(value = deskripsi, onValueChange = { deskripsi = it }, label = { Text("Deskripsi") }, modifier = Modifier.fillMaxWidth())
            TextField(value = tanggal, onValueChange = { tanggal = it }, label = { Text("Tanggal") }, modifier = Modifier.fillMaxWidth())
            TextField(value = waktu, onValueChange = { waktu = it }, label = { Text("Waktu") }, modifier = Modifier.fillMaxWidth())
            TextField(value = lokasi, onValueChange = { lokasi = it }, label = { Text("Lokasi") }, modifier = Modifier.fillMaxWidth())
            TextField(value = pengingat, onValueChange = { pengingat = it }, label = { Text("Pengingat") }, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(16.dp))

            // 🔹 Tombol Batal & Simpan (versi benar)
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            ) {
                // Tombol Batal
                Button(
                    onClick = { navController.popBackStack() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E2E1F)),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Batal", color = Color.White)
                }

                // Tombol Simpan
                Button(
                    onClick = {
                        if (judul.isNotEmpty() && kategori.isNotEmpty() && waktu.isNotEmpty()) {
                            viewModel.addAgenda(judul, kategori, waktu)
                            navController.popBackStack()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF712626)),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Simpan", color = Color.White)
                }
            }
        }
    }
}

// 🔸 Styling TextField Colors (kalau mau dipakai)
@Composable
fun inputColors() = TextFieldDefaults.colors(
    focusedContainerColor = Color.White,
    unfocusedContainerColor = Color.White,
    focusedLabelColor = Color(0xFF712626),
    unfocusedLabelColor = Color(0xFF712626),
    focusedIndicatorColor = Color(0xFF712626),
    unfocusedIndicatorColor = Color(0xFF712626),
    cursorColor = Color(0xFF712626)
)
