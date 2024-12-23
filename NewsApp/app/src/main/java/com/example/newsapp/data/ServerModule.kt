package com.example.newsapp.data

import com.example.newsapp.data.entity.response.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "779e47d803fb44f89403b5ee16c0b750"

interface ServerApi {
    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String
    ): Response<NewsResponse>
}