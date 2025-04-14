package com.intern.data.di

import androidx.room.Database
import androidx.room.RoomDatabase
import com.intern.data.local.news.FavoriteNewsDao
import com.intern.data.local.news.FavoriteNewsEntity

@Database(entities = [FavoriteNewsEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteNewsDao(): FavoriteNewsDao
}
