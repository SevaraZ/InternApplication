package com.intern.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.internapp.network.news.ApiObject
import com.example.internapp.network.news.NewsApi
import com.intern.data.local.news.FavoriteNewsDao
import com.intern.data.local.news.FavoriteNewsEntity
import com.intern.data.repository.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import repository.NewsRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindsNewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl
    ): NewsRepository

    companion object {
        @Provides
        @Singleton
        fun provideNewsApi(): NewsApi {
            return Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsApi::class.java)
        }

        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
            return databaseBuilder(
                context,
                AppDatabase::class.java,
                "news_db"
            ).build()
        }

        @Provides
        fun provideFavoriteNewsDao(db: AppDatabase): FavoriteNewsDao = db.favoriteNewsDao()
    }


}


