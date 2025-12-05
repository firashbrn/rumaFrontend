package com.example.rumafrontend.data.database.Dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rumafrontend.data.model.ResepEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ResepDao {
    // Mengambil semua resep
    @Query("SELECT * FROM resep_table ORDER BY id DESC")
    fun getAllRecipes(): Flow<List<ResepEntity>>

    // Mengambil detail satu resep
    @Query("SELECT * FROM resep_table WHERE id = :id")
    fun getRecipeById(id: Int): Flow<ResepEntity?>

    // Menyimpan/mengganti daftar resep (caching dari API)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipes: List<ResepEntity>)

    // Update status favorit lokal
    @Query("UPDATE resep_table SET is_favorit = :isFavorite WHERE id = :recipeId")
    suspend fun updateFavoriteStatus(recipeId: Int, isFavorite: Boolean)

    // Membersihkan cache
    @Query("DELETE FROM resep_table")
    suspend fun clearCache()
}