package com.intern.domain.usecase

import com.example.core.models.news.NewsResponse
import repository.NewsRepository

class GetAllNewsUseCase(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(
        category: String, apiKey: String, country: String
    ): NewsResponse {
        return newsRepository.getTopHeadlines(
            category = category,
            apiKey = apiKey,
            country = country
        )
    }

}