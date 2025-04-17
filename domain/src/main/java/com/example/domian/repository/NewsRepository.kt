package com.example.domian.repository

import com.example.mcore.models.news.NewsResponse
import com.example.mcore.local.news.FavoriteNewsEntity
import com.example.mcore.models.news.Article

interface NewsRepository {

    suspend fun getTopHeadlines(category: String): NewsResponse
    suspend fun getAllFavorites(): List<FavoriteNewsEntity>
    suspend fun insertFavorite(news: FavoriteNewsEntity)
    suspend fun deleteFavorite(news: FavoriteNewsEntity)

}