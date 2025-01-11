package data.server

import data.ArtObject
import data.entity.response.CategoryResponse
import data.entity.response.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArtApiService {

    @GET("public/collection/v1/objects/{objectID}")
    suspend fun getArtObjectById(
        @Path("objectID") objectId: Int
    ): Response<ArtObject>

    @GET("public/collection/v1/search")
    suspend fun getCategoryObjects(
        @Query("q") category: String
    ): Response<CategoryResponse>

    @GET("public/collection/v1/search")
    suspend fun searchArtObjects(
        @Query("q") query: String
    ): Response<SearchResponse>
}
