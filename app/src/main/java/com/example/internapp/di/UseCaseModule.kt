package com.example.internapp.di

import com.intern.domain.usecase.GetAllNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import repository.NewsRepository

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideGetAllNewsUseCase(
        newsRepository: NewsRepository
    ): GetAllNewsUseCase {
        return GetAllNewsUseCase(newsRepository)
    }

}