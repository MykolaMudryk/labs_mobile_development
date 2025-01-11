package com.example.artviewerapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.artviewerapp.data.entity.ObjectDetailsEntity

@Database(entities = [ObjectDetailsEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "favorites_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
