package com.example.rumafrontend.data.repository


import com.example.rumafrontend.data.database.Dao.UserDao
import com.example.rumafrontend.data.mapper.toDomain
import com.example.rumafrontend.data.mapper.toUserEntity // Asumsi ini ada
import com.example.rumafrontend.data.model.loginRequest
import com.example.rumafrontend.data.model.pengguna
import com.example.rumafrontend.network.ApiService
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class AuthRepository @Inject constructor(private val apiService: ApiService, private val userDao: UserDao) {

    // --- Profile/Sesi ---
    fun getUserProfile(): Flow<pengguna> {
        // Ambil dari Room dan konversi ke Domain Model
        return userDao.getSessionUser().map { it?.toDomain() ?: pengguna(
            username = "",
            email = "",
            token = "",
            password = null,
            imageUrl = null
        )}
    }

    // --- Login ---
    suspend fun login(request: loginRequest): String { // Mengembalikan String (Success atau pesan error)
        return try {
            val response = apiService.login(request)

            if (response.isSuccessful && response.body() != null) {
                val body = response.body()!!

                // Cek status dari body respons
                if (body.status == "Success") {
                    val userEntity = body.toUserEntity()
                    userDao.insertOrUpdateUser(userEntity) // Simpan sesi
                    "Success" // Mengembalikan tanda sukses
                } else {
                    body.message // Mengembalikan pesan error dari server
                }
            } else {
                "Login Gagal: Code ${response.code()}" // Mengembalikan error HTTP
            }
        } catch (e: Exception) {
            // Tangkap exception jaringan, timeout, atau parsing error
            "Error: ${e.message ?: "Kesalahan Jaringan!"}"
        }
    }

    // --- Logout ---
    suspend fun logout() {
        userDao.clearSession()
        // Panggil apiService.logout() jika ada
    }

    // --- Edit Profile ---
    suspend fun updateProfile(newUsername: String, newEmail: String, newPassword: String?) {
        // 1. Ambil token dari sesi Room untuk API call
        val currentToken = userDao.getSessionUser().first()?.token ?: throw Exception("Token tidak ada")

        // 2. Buat API request untuk update (asumsi ada)
        // val response = apiService.updateProfile(currentToken, updateRequest)

        // 3. Jika API sukses, update Room (sumber kebenaran lokal)
        val updatedEntity = userDao.getSessionUser().first()!!.copy(
            username = newUsername,
            email = newEmail
        )
        userDao.insertOrUpdateUser(updatedEntity)
    }
}