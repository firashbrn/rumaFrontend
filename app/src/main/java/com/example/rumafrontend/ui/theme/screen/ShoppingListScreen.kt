package com.example.rumafrontend.ui.theme.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListScreen(
    onBackClick: () -> Unit = {},
    onMapClick: () -> Unit = {}
) {
    val backgroundColor = Color(0xFFF4ECDD)
    val accentColor = Color(0xFF792323)
    val oliveColor = Color(0xFFA7A37E)
    var showAddSheet by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = { BottomNavigationBar() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddSheet = true },
                containerColor = Color(0xFF4C5325),
                contentColor = Color.White,
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Tambah List")
            }
        },
        containerColor = backgroundColor
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(backgroundColor)
                .padding(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Kembali",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(28.dp)
                            .clickable { onBackClick() }
                    )
                    Text(
                        text = "DAFTAR BELANJA",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = accentColor,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Lokasi",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { onMapClick() }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Search bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .shadow(6.dp, RoundedCornerShape(25.dp))
                    .background(oliveColor, RoundedCornerShape(25.dp))
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Cari",
                    tint = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Statistik Card
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatCard(icon = Icons.Default.List, title = "List", count = 0)
                StatCard(icon = Icons.Default.CheckCircle, title = "Selesai", count = 0)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Label Section
            Text(
                text = "List Belanja Saya",
                color = Color(0xFF792323),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(Color(0xFFB17D72), RoundedCornerShape(20.dp))
                    .padding(horizontal = 125.dp, vertical = 4.dp)
            )
        }

        if (showAddSheet) {
            AddListBottomSheet(
                onDismiss = { showAddSheet = false }
            )
        }
    }
}

@Composable
fun StatCard(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, count: Int) {
    Box(
        modifier = Modifier
            .size(width = 150.dp, height = 120.dp)
            .background(Color(0xFFD9D9D9), RoundedCornerShape(28.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, contentDescription = title, tint = Color.Black, modifier = Modifier.size(28.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = count.toString(), fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun BottomNavigationBar() {
    val accentColor = Color(0xFF792323)
    val iconColor = Color(0xFF2D3319)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(accentColor, RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
            .padding(horizontal = 40.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Favorite, contentDescription = "Favorite", tint = iconColor)
            Icon(Icons.Default.Home, contentDescription = "Home", tint = iconColor)
            Icon(Icons.Default.Person, contentDescription = "Profile", tint = iconColor)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddListBottomSheet(onDismiss: () -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    var title by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color(0xFF2D3319),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Kembali", color = Color.White, fontWeight = FontWeight.Bold)
                Text("New List", color = Color(0xFFF4F1D5), fontWeight = FontWeight.Bold)
                Text("Selesai", color = Color.White, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                placeholder = { Text("Judul", color = Color(0xFFF4F1D5)) },
                leadingIcon = { Icon(Icons.Default.List, contentDescription = null, tint = Color(0xFFF4F1D5)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
                    .background(Color(0xFF4C5325), RoundedCornerShape(16.dp)),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFF4F1D5),
                    unfocusedBorderColor = Color(0xFFF4F1D5),
                    cursorColor = Color(0xFFF4F1D5),
                    focusedTextColor = Color(0xFFF4F1D5),
                    unfocusedTextColor = Color(0xFFF4F1D5)
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = date,
                onValueChange = { date = it },
                placeholder = { Text("Tanggal Belanja", color = Color(0xFFF4F1D5)) },
                leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = null, tint = Color(0xFFF4F1D5)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF4C5325), RoundedCornerShape(16.dp)),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFF4F1D5),
                    unfocusedBorderColor = Color(0xFFF4F1D5),
                    cursorColor = Color(0xFFF4F1D5),
                    focusedTextColor = Color(0xFFF4F1D5),
                    unfocusedTextColor = Color(0xFFF4F1D5)
                )
            )
        }
    }

    // Fokus otomatis dan tampilkan keyboard
    LaunchedEffect(Unit) {
        delay(300)
        focusRequester.requestFocus()
        keyboardController?.show()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewShoppingListScreen() {
    ShoppingListScreen()
}
