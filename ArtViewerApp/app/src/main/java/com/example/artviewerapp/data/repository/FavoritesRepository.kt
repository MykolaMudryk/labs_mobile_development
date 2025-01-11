package com.example.artviewerapp.data.repository

import android.content.Context
import com.example.artviewerapp.data.entity.ObjectDetailsEntity
import com.example.artviewerapp.data.local.AppDatabase
import com.example.artviewerapp.data.local.FavoritesDao
import com.example.artviewerapp.data.entity.ObjectDetailsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object FavoritesRepository {
    private lateinit var dao: FavoritesDao

    fun initialize(context: Context) {
        dao = AppDatabase.getDatabase(context).favoritesDao()
    }

    suspend fun getFavorites(): List<ObjectDetailsResponse> = withContext(Dispatchers.IO) {
        dao.getAllFavorites().map { entity ->
            ObjectDetailsResponse(
                objectID = entity.objectID,
                title = entity.title,
                primaryImage = entity.primaryImage,
                accessionYear = entity.accessionYear,
                artistDisplayName = entity.artistDisplayName,
                artistDisplayBio = entity.artistDisplayBio
            )
        }
    }

    suspend fun addToFavorites(artwork: ObjectDetailsResponse) = withContext(Dispatchers.IO) {
        dao.addToFavorites(
            ObjectDetailsEntity(
                objectID = artwork.objectID,
                title = artwork.title,
                primaryImage = artwork.primaryImage,
                accessionYear = artwork.accessionYear,
                artistDisplayName = artwork.artistDisplayName,
                artistDisplayBio = artwork.artistDisplayBio
            )
        )
    }

    suspend fun removeFromFavorites(artwork: ObjectDetailsResponse) = withContext(Dispatchers.IO) {
        dao.removeFromFavorites(
            ObjectDetailsEntity(
                objectID = artwork.objectID,
                title = artwork.title,
                primaryImage = artwork.primaryImage,
                accessionYear = artwork.accessionYear,
                artistDisplayName = artwork.artistDisplayName,
                artistDisplayBio = artwork.artistDisplayBio
            )
        )
    }

    suspend fun isFavorite(artwork: ObjectDetailsResponse): Boolean = withContext(Dispatchers.IO) {
        dao.getAllFavorites().any { it.objectID == artwork.objectID }
    }
}
