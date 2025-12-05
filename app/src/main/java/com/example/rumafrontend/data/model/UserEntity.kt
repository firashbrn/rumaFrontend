package com.example.rumafrontend.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    // Kunci primer tunggal untuk sesi. Karena hanya satu user yang login,
    // kita gunakan nilai statis (tidak autoGenerate).
    @PrimaryKey
    val id: String = "session_key",

    // Data yang diterima dari API setelah login
    val username: String,
    val email: String,
    val password: String?, // Tidak semua pengguna membutuhkan password, jadi nullable
    val token: String,
    val status: String,
    val message: String,
    val imageUrl: String? // URL Foto Profil (sesuai desain Anda)
)