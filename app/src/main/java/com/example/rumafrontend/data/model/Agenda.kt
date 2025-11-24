package com.example.rumafrontend.data.model

data class Agenda(
    val id: Int,
    val judul: String,
    val kategori: String,
    val waktu: String,
    val deskripsi: String,
    val tanggal: String,
    val pengingat: String,
    val lokasi: String
)