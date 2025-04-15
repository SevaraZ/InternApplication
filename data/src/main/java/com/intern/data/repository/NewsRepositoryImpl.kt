package com.intern.data.repository

import com.example.core.local.news.FavoriteNewsDao
import com.example.core.local.news.FavoriteNewsEntity
import com.example.core.models.news.NewsResponse
import com.intern.data.network.news.ApiObject
import com.example.domian.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val dao: FavoriteNewsDao
) : NewsRepository {

    override suspend fun getTopHeadlines(
        category: String
    ): NewsResponse {
        return ApiObject.api.getTopHeadlines(
            category = category
        )
    }

    override suspend fun getAllFavorites(): List<FavoriteNewsEntity> {
        return dao.getAllFavorites()

    }

    override suspend fun insertFavorite(news: FavoriteNewsEntity) {
        dao.insert(news)
    }

    override suspend fun deleteFavorite(news: FavoriteNewsEntity) {
        dao.delete(news)
    }

}
