package com.example.rumafrontend.data.model

data class Langkah(
val id: Int,
val resep_id: Int,
val urutan: Int,
val deskripsi: String,
val foto_langkah: String?,
val video_langkah: String?
)
