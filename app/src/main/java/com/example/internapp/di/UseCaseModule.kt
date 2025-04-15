package com.example.internapp.di

import com.example.domian.usecase.GetAllNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.domian.repository.NewsRepository

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