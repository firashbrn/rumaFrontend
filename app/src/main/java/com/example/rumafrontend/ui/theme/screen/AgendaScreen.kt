package com.example.rumafrontend.ui.theme.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.rumafrontend.ViewModel.AgendaViewModel.AgendaViewModel
import com.example.rumafrontend.data.model.Agenda

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendaScreen(
    navController: NavController,
    viewModel: AgendaViewModel
) {
    val agendaList = viewModel.agendas
    val categories = listOf("Momen Spesial", "Liburan & Rekreasi", "Kumpul Keluarga", "Kegiatan Rumah")
    var selectedCategory by remember { mutableStateOf("") }
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add_agenda") },
                containerColor = Color(0xFF712626)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Tambah Agenda", tint = Color.White)
            }
        },
        bottomBar = {
            BottomAppBar(containerColor = Color(0xFF712626)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Favorite, contentDescription = "Favorit", tint = Color.White)
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Home, contentDescription = "Beranda", tint = Color.White)
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Person, contentDescription = "Profil", tint = Color.White)
                    }
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF8F2E8))
        ) {
            // 🔹 Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF712626))
                    .padding(16.dp)
            ) {
                Text(
                    text = "Agenda Keluarga",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 🔹 Search bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Cari agenda...") },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = { /* filter kalender nanti */ }) {
                    Icon(Icons.Default.DateRange, contentDescription = "Kalender", tint = Color(0xFF712626))
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 🔹 Kategori filter
            Text(
                "Kategori",
                modifier = Modifier.padding(start = 16.dp),
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2E2E1F)
            )

            LazyRow(
                contentPadding = PaddingValues(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { category ->
                    Button(
                        onClick = {
                            selectedCategory = if (selectedCategory == category) "" else category
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedCategory == category)
                                Color(0xFF712626)
                            else Color(0xFFE0BEBE)
                        ),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text(category, color = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 🔹 Filter data berdasarkan kategori dan pencarian
            val filteredAgenda = agendaList.filter {
                (selectedCategory.isEmpty() || it.kategori == selectedCategory) &&
                        (searchQuery.isEmpty() || it.judul.contains(searchQuery, ignoreCase = true))
            }

            // 🔹 Daftar agenda
            if (filteredAgenda.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Agenda kosong, ayo tambahkan agenda!",
                        color = Color(0xFF2E2E1F)
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp)
                ) {
                    items(filteredAgenda) { agenda ->
                        AgendaCard(
                            agenda = agenda,
                            onClick = {
                                // nanti bisa navigasi ke detail
                                // navController.navigate("agenda_detail/${agenda.id}")
                            }
                        )
                    }
                }
            }
        }
    }
}

// 🔹 Komponen kartu agenda
@Composable
fun AgendaCard(agenda: Agenda, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF8E1))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = agenda.judul,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF712626)
            )
            Text(
                text = agenda.kategori,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF4B4B3D)
            )
            Text(
                text = "📅 ${agenda.waktu}",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF2E2E1F)
            )
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun AgendaScreenPreview() {
    val navController = rememberNavController()
    val fakeViewModel = AgendaViewModel().apply {
        addAgenda("Liburan ke Bali", "Liburan & Rekreasi", "12 Desember 2025")
        addAgenda("Ulang Tahun Mama", "Momen Spesial", "5 Januari 2026")
    }
    AgendaScreen(navController = navController, viewModel = fakeViewModel)
}
