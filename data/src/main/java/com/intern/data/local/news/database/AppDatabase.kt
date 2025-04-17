package com.intern.data.local.news.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mcore.local.news.FavoriteNewsDao
import com.example.mcore.local.news.FavoriteNewsEntity

@Database(entities = [com.example.mcore.local.news.FavoriteNewsEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteNewsDao(): com.example.mcore.local.news.FavoriteNewsDao
}
