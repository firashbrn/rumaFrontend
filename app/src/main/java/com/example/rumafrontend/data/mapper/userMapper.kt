package com.example.rumafrontend.data.mapper

import com.example.rumafrontend.data.model.loginRespons
import com.example.rumafrontend.data.model.pengguna
import com.example.rumafrontend.data.model.UserEntity

// --- 1. Dari API Response ke Room Entity (Digunakan saat Login Sukses) ---
// Room membutuhkan UserEntity untuk disimpan
fun loginRespons.toUserEntity(): UserEntity {
    return UserEntity(
        // ID statis wajib untuk Room
        id = "session_key",

        // Data yang tersedia dari respons API login
        status = this.status,
        email = this.email,
        token = this.token,
        message = this.message,

        // Data yang akan didapatkan NANTI (disetel ke default/null)
        username = "", // String kosong sebagai default jika field String tidak nullable
        password = null,
        imageUrl = null
    )
}

// --- 2. Dari Room Entity ke Domain Model (Digunakan di AuthRepository) ---
// ViewModel dan UI hanya menggunakan User Domain Model
fun UserEntity.toDomain(): pengguna {
    return pengguna(
        username = this.username,
        email = this.email,
        password = this.password,
        imageUrl = this.imageUrl,
        token = this.token
    )
}

// --- Tambahan: Dari Domain Model ke Room Entity (Digunakan saat Edit Profile) ---
fun pengguna.toEntity(): UserEntity {
    return UserEntity(

        id = "session_key",

        // 2. Data dari Domain Model (pengguna)
        username = this.username,
        email = this.email,
        token = this.token,
        imageUrl = this.imageUrl,
        password = this.password,

        // 3. Data yang HILANG di Domain Model (diisi dengan placeholder)
        status = "",   // Diisi string kosong
        message = ""   // Diisi string kosong
    )
}