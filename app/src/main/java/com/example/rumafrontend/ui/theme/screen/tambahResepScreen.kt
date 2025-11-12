package com.example.rumafrontend.ui.theme.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun tambahResepScreen(onNavigationBack : () -> Unit, query: String, onQueryChange : (String) -> Unit ){
    Scaffold (
        containerColor = Color(0xFFF2ECDC),
        topBar = {
            TopAppBar(title = {
                Text(
                    "Resep Makanan ", style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold, fontSize = 25.sp
                    ), color = Color(0xFFF2ECDC)
                )
            },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF7E2625)
                ),
                navigationIcon = {
                    IconButton(onClick = onNavigationBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Kembali",
                            Modifier
                                .size(30.dp)
                                .padding(2.dp),
                            tint = Color(0xFFF2ECDC)
                        )
                    }
                })
        }) { innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ){
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                value = query,
                onValueChange = onQueryChange,
                label = { Text("Search items") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription
                    = "Search Icon")
                }
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text("Tips & Trik", style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp,
            ), color = Color(0xFF868859)
                )


        }

    }
    
}


