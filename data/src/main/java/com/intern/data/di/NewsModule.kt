package com.intern.data.di

import android.content.Context
import androidx.room.Room
import com.example.mcore.local.news.FavoriteNewsDao
import com.intern.data.local.news.database.AppDatabase
import com.intern.data.network.news.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NewsModule {

    @Provides
    @Singleton
    fun provideNewsApi(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesNewsApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "news_db"
        ).build()
    }


    @Provides
    fun provideFavoriteNewsDao(db: AppDatabase): com.example.mcore.local.news.FavoriteNewsDao =
        db.favoriteNewsDao()
}