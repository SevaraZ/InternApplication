package com.intern.data.repository

import com.example.core.models.news.NewsResponse
import com.example.internapp.network.news.ApiObject
import repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor() : NewsRepository {

    override suspend fun getTopHeadlines(
        category: String,
        apiKey: String,
        country: String
    ): NewsResponse {
        return ApiObject.api.getTopHeadlines(
            category = category,
            apiKey = apiKey,
            country = country
        )
    }

}
