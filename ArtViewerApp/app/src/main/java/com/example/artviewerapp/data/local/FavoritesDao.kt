package com.example.artviewerapp.data.local

import androidx.room.*
import com.example.artviewerapp.data.entity.ObjectDetailsEntity

@Dao
interface FavoritesDao {
    @Query("SELECT * FROM favorites")
    suspend fun getAllFavorites(): List<ObjectDetailsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(artwork: ObjectDetailsEntity)

    @Delete
    suspend fun removeFromFavorites(artwork: ObjectDetailsEntity)
}
