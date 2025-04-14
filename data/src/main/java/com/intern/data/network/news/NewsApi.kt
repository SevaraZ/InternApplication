package com.example.internapp.network.news

import com.example.core.models.news.Article
import com.example.core.models.news.NewsResponse
import com.intern.data.local.news.FavoriteNewsEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("category") category: String,
        @Query("apiKey") apiKey: String = "f86ca5a425794a5db895072f22afef66",
        @Query("country") country: String = "us"
    ): com.example.core.models.news.NewsResponse
    fun getAllFavorites(): Flow<List<FavoriteNewsEntity>>
    suspend fun addToFavorites(article: Article)
    suspend fun removeFromFavorites(favorite: FavoriteNewsEntity)
}