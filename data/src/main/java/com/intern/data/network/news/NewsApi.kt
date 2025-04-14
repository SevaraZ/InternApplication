package com.example.internapp.network.news

import com.intern.data.models.news.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("category") category: String,
        @Query("apiKey") apiKey: String = "f86ca5a425794a5db895072f22afef66",
        @Query("country") country: String = "us"
    ): NewsResponse
}