package com.example.artviewerapp.data.remote

import com.example.artviewerapp.data.entity.ObjectDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.example.artviewerapp.data.entity.SearchResponse
import kotlin.random.Random


interface ApiService {
    @GET("public/collection/v1/objects/{objectID}")
    suspend fun getObjectDetailsByObjectID(
        @Path("objectID") objectID: Int
    ): ObjectDetailsResponse

    @GET("public/collection/v1/search")
    suspend fun searchArtworks(
        @Query("q") query: String
    ): SearchResponse
}

suspend fun fetchRandomArtworks(limit: Int): List<ObjectDetailsResponse> {
    val randomIDs = List(limit) {Random.nextInt(1, 460000) }
    println("Generated random IDs: $randomIDs")

    return randomIDs.mapNotNull { id ->
        try {
            val artwork = RetrofitInstance.api.getObjectDetailsByObjectID(id)
            if (!artwork.primaryImage.isNullOrEmpty()) {
                artwork
            } else {
                null
            }
        } catch (e: Exception) {
            println("Error fetching details for ID: $id - ${e.message}")
            null
        }
    }
}

suspend fun fetchArtworksWithQuery(query: String, limit: Int): List<ObjectDetailsResponse> {
    val searchResponse = RetrofitInstance.api.searchArtworks(query)
    val objectIDs = searchResponse.objectIDs.orEmpty().take(limit)

    return objectIDs.mapNotNull { id ->
        try {
            RetrofitInstance.api.getObjectDetailsByObjectID(id)
        } catch (e: Exception) {
            null
        }
    }
}