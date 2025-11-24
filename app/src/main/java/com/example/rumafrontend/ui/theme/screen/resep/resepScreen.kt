package com.example.rumafrontend.ui.theme.screen.resep

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rumafrontend.data.model.Resep
import com.example.rumafrontend.data.model.TipsTrik
import com.example.rumafrontend.ui.theme.rumaFrontendTheme
import coil.compose.AsyncImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeResepScreen(onNavigationBack : () -> Unit) {
    var searchQuery by remember { mutableStateOf("") }

    val tipsList = listOf(
        TipsTrik("5 Tips Memasak Bagi Pemula", "https://images.unsplash.com/photo-1617196037303-5e67f3f1d621"),
        TipsTrik(
            "Cara Masak yang Efisien",
            "https://mamasuka.com/storage/recipes/20231005045547.jpeg"
        )
    )

    // 1. Perbaikan inisialisasi resepList
    val resepList = remember {
        mutableStateListOf(
            Resep(
                "Rendang Daging Sapi Ala Ibu",
                "Daging Sapi, Santan, Serai, Daun Kunyit, Cabe Merah, dll.",
                "https://images.unsplash.com/photo-1617196037303-5e67f3f1d621",
                "1 Jam",
                "5 Porsi",
                true
            ),
            Resep(
                "Cumi Saos Asam Manis",
                "Cumi Segar, Kecap Manis, Saos Tomat, Bawang Merah, dll.",
                "https://images.unsplash.com/photo-1604909052868-9b6b54f9077c",
                "45 Menit",
                "2 Porsi",
                false
            ),
            Resep(
                "Telur Balado",
                "Telur Ayam, Cabe Merah, Bawang Merah, Garam.",
                "https://images.unsplash.com/photo-1589302168068-964664d93dc0",
                "30 Menit",
                "3 Porsi",
                true
            )
        )
    } // Penutupan remember dan mutableStateListOf

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
                }
            )
        },
        // 2. Tambahkan bottomBar yang hilang
        bottomBar = { BottomNavigationBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding)
                .background(Color(0xFFF5EFD6))
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            // 3. Perbaikan pemanggilan SearchBar
            SearchBar(searchQuery) { query ->
                searchQuery = query
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Tips & Trik", fontWeight = FontWeight.Bold, color = Color(0xFF756D3D), fontSize = 18.sp)

            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(tipsList) { tips ->
                    TipsCard(tips)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Divider(color = Color.LightGray, thickness = 1.dp)

            // 4. Perbaikan penamaan header untuk daftar resep
            Text("Resep Pilihan", fontWeight = FontWeight.Bold, color = Color(0xFF756D3D), fontSize = 18.sp, modifier = Modifier.padding(vertical = 8.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(resepList) { resep ->
                    // 5. Perbaikan pemanggilan ResepCard
                    ResepCard(resep = resep, onFavoriteClick = {
                        // 6. Gunakan find dan update jika perlu, tapi ini sudah update langsung
                        resep.is_favorit = !resep.is_favorit
                    })
                }
            }
        }
    }
}

@Composable
fun SearchBar(search: String, onSearchChange: (String) -> Unit) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        // 7. Hapus padding horizontal di sini karena sudah ada di Column parent
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ){
        Icon(Icons.Default.Search,
            contentDescription = "Search",
            tint = Color.Gray)
        Spacer(modifier = Modifier.width(12.dp))
        BasicTextField(
            value = search,
            onValueChange = onSearchChange, // 8. Gunakan onSearchChange
            textStyle = TextStyle(color = Color.Black, fontSize = 14.sp ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            decorationBox = {innerTextField ->
                if (search.isEmpty()) Text("Search...", color = Color.Gray, fontSize = 14.sp)
                innerTextField()
            }
        )
    }
}

@Composable
fun TipsCard(tips : TipsTrik) {
    // val context = LocalContext.current // Tidak digunakan, boleh dihapus

    Column (
        modifier = Modifier
            .width(160.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = Color(0xFFF2ECDC))
            .clickable {
                // Handle click action
            }
    ){
        AsyncImage (
            model = tips.imgUrl,
            contentDescription = tips.title,
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Text(
            text = tips.title,
            modifier = Modifier.padding(8.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}


@Composable
fun ResepCard(resep: Resep, onFavoriteClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(8.dp)
    ) {
        AsyncImage(
            model = resep.foto, // 10. Ganti resep.imageUrl menjadi resep.imgUrl (sesuai TipsTrik)
            contentDescription = resep.judul, // 11. Ganti resep.title menjadi resep.judul
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(resep.judul, fontWeight = FontWeight.Bold) // 12. Ganti resep.title menjadi resep.judul
            Text(resep.deskripsi, fontSize = 12.sp, color = Color.Gray) // 13. Ganti resep.description menjadi resep.deskripsi
        }

        IconButton(onClick = onFavoriteClick) {
            Icon(
                imageVector = if (resep.is_favorit) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder, // 14. Ganti Icons.Default.FavoriteBorder menjadi Icons.Outlined.FavoriteBorder (perlu import)
                contentDescription = "Favorite",
                tint = if (resep.is_favorit) Color(0xFFB80000) else Color.Gray
            )
        }
    }
}

@Composable
fun BottomNavigationBar() {
    NavigationBar(containerColor = Color(0xFF7A1E1E)) {
        // 15. Perbaikan konstruksi NavigationBarItem
        NavigationBarItem(
            selected = true,
            onClick = { /* Handle navigation */ },
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite",
                    tint = Color.White
                )
            },
            label = { Text("") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /* Handle navigation */ },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = Color.White
                )
            },
            label = { Text("") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /* Handle navigation */ },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile",
                    tint = Color.White
                )
            },
            label = { Text("") }
        )
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewHomeResepScreen() {

    rumaFrontendTheme {

        HomeResepScreen{}
    }
}