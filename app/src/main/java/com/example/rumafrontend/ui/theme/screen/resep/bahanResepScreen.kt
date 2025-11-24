package com.example.rumafrontend.ui.theme.screen.resep

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun bahanResepScreen(onNavigationBack: () -> Unit) {
    var bahanList by remember { mutableStateOf(listOf<String>()) }
    var bahan by remember { mutableStateOf("") }
    Scaffold(
        containerColor = Color(0xFFF2ECDC),
        topBar = {
            TopAppBar(title = {
                Text(
                    "Daftar Bahan", style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold, fontSize = 25.sp
                    ), color = Color(0xFF868859)
                )
            },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF2ECDC)
                ),
                navigationIcon = {
                    IconButton(onClick = onNavigationBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Kembali",
                            Modifier
                                .size(30.dp)
                                .padding(2.dp),
                            tint = Color(0xFF868859)
                        )
                    }
                })
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(36.dp))

            Text(
                "Tambahkan Bahan - Bahan yang Diperlukan!!",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                ), color = Color(0xFF3c1b0f)
            )

            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = bahan,
                    onValueChange = { bahan = it },
                    label = { Text("Tambahkan Bahan") },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        if (bahan.isNotBlank()) {
                            bahanList = bahanList + bahan
                            bahan = ""
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7E2625))
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Add", color = Color.White)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                if (bahanList.isEmpty()) {
                    Text("Belum ada bahan ditambahkan", color = Color(0xFF292f17))
                } else {
                    bahanList.forEach { item ->
                        Text(text = "• $item", color = Color(0xFF292f17))
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewBahanResepScreen() {
    bahanResepScreen {  }
}
