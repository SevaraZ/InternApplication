package com.example.domian.repository

import com.example.core.models.news.NewsResponse
import com.example.core.local.news.FavoriteNewsEntity

interface NewsRepository {

    suspend fun getTopHeadlines(category: String): NewsResponse

    suspend fun getAllFavorites(): List<FavoriteNewsEntity>



    suspend fun insertFavorite(news: FavoriteNewsEntity)
    suspend fun deleteFavorite(news: FavoriteNewsEntity)

}