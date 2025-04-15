package com.example.domian.usecase

import com.example.core.local.news.FavoriteNewsEntity
import com.example.domian.repository.NewsRepository
import javax.inject.Inject

class GetAllFavoritesUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(): List<FavoriteNewsEntity> {
        return newsRepository.getAllFavorites()
    }
}