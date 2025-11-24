package com.example.rumafrontend.ui.theme.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ShoppingDetailScreen() {
    Scaffold(
        bottomBar = { BottomNavigationBarShoppingDetail() }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5ECDC)) // warna krem lembut
                .padding(padding)
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            // Header (Back dan More)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* TODO: Aksi kembali */ }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }

                IconButton(onClick = { /* TODO: Aksi more */ }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More",
                        tint = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Judul & tanggal
            Text(
                text = "Acara Tahlilan",
                color = Color(0xFF812C2A),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "18/10/2025",
                color = Color(0xFF812C2A),
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            // Tombol Tambah Kategori
            Row(
                modifier = Modifier
                    .padding(bottom = 80.dp)
                    .align(Alignment.Start),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .background(Color(0xFF6B663D), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Tambah",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Tambah Kategori",
                    color = Color(0xFF6B663D),
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBarShoppingDetail() {
    val iconColor = Color(0xFF2D2C1F)

    NavigationBar(
        containerColor = Color(0xFF7A2322)
    ) {
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = {
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = "Favorite",
                    tint = iconColor
                )
            }
        )
        NavigationBarItem(
            selected = false, // tidak perlu selected agar tidak berubah warna
            onClick = { },
            icon = {
                Icon(
                    Icons.Default.Home,
                    contentDescription = "Home",
                    tint = iconColor
                )
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "Profile",
                    tint = iconColor
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingDetailScreenPreview() {
    ShoppingDetailScreen()
}
