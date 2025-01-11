package com.example.artviewerapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class ObjectDetailsEntity(
    @PrimaryKey val objectID: Int,
    val title: String?,
    val primaryImage: String?,
    val accessionYear: String?,
    val artistDisplayName: String?,
    val artistDisplayBio: String?
)
