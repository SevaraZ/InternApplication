package com.example.domian.usecase

import com.example.mcore.local.news.FavoriteNewsEntity
import com.example.domian.repository.NewsRepository
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke (news: FavoriteNewsEntity) = newsRepository.deleteFavorite(news)
}