package com.example.rumafrontend.data.model

data class Resep(

    val judul: String,
    val deskripsi: String,
    val foto: String,
    val waktu_masak: String,
    val porsi: String,
    var is_favorit: Boolean,

    )