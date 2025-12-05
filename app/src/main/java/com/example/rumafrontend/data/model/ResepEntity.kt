package com.example.rumafrontend.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "resep_table")
data class ResepEntity(
    // ID yang harus sama dengan ID resep di database server Anda
    @PrimaryKey
    val id: Int,

    val judul: String,
    val deskripsi: String,
    val foto: String,
    val waktu_masak: String, // Asumsi ini sudah diformat di server/mapper
    val porsi: String,

    // State lokal yang hanya dipertahankan di Room
    val is_favorit: Boolean
)