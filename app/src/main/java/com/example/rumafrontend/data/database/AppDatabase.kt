package com.example.rumafrontend.data.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rumafrontend.data.database.Dao.ResepDao
import com.example.rumafrontend.data.database.Dao.UserDao
import com.example.rumafrontend.data.model.ResepEntity
import com.example.rumafrontend.data.model.UserEntity

@Database(
    entities = [UserEntity::class, ResepEntity::class], // Daftar semua Entity
    version = 1, // Versi harus dinaikkan jika ada perubahan skema
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun resepDao(): ResepDao

    // NOTE: Dalam proyek nyata, inisialisasi ini dilakukan dengan Hilt/DI.
    // Ini hanya contoh Singleton manual.
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "ruma_app_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}