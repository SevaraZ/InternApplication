package com.example.domian.usecase

import com.example.domian.repository.NewsRepository
import com.example.mcore.models.news.NewsResponse
import javax.inject.Inject

class GetAllNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(
        category: String
    ): NewsResponse {
        return newsRepository.getTopHeadlines(
            category = category
        )
    }

}