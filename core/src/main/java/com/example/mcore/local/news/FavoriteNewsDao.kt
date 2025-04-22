package com.example.mcore.local.news

import androidx.annotation.Keep
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteNewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(news: FavoriteNewsEntity)

    @Query("SELECT * FROM favorite_news")
    suspend fun getAllFavorites(): List<FavoriteNewsEntity>

    @Delete
    suspend fun delete(news: FavoriteNewsEntity)

}