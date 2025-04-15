package com.example.domian.usecase

import com.example.core.local.news.FavoriteNewsEntity
import com.example.domian.repository.NewsRepository
import javax.inject.Inject

class InsertFavoriteUseCase @Inject constructor(
    private val newsRepository: NewsRepository
){
    suspend operator fun invoke (news: FavoriteNewsEntity) = newsRepository.insertFavorite(news)
}