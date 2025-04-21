package com.example.domian.usecase

import com.example.domian.repository.NewsRepository
import javax.inject.Inject

class InsertFavoriteUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(news: com.example.mcore.local.news.FavoriteNewsEntity) =
        newsRepository.insertFavorite(news)
}