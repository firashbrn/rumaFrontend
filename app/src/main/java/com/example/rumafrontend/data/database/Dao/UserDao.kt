package com.example.rumafrontend.data.database.Dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rumafrontend.data.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    // Mengambil data user saat ini secara real-time
    @Query("SELECT * FROM user_table WHERE id = 'session_key'")
    fun getSessionUser(): Flow<UserEntity?>

    // Menyimpan data user (saat Login/Edit Profile)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateUser(user: UserEntity)

    // Membersihkan data user (saat Log Out)
    @Query("DELETE FROM user_table")
    suspend fun clearSession()
}