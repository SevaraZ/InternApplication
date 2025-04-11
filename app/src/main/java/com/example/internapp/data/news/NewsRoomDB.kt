package com.example.internapp.data.news

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query


@Entity(tableName = "favorite_news")
data class FavoriteNews(
    @PrimaryKey val url: String,
    val title: String,
    val description: String?
)

@Dao
interface FavoriteNewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(news: FavoriteNews)

    @Query("SELECT * FROM favorite_news")
    suspend fun getAllFavorites(): List<FavoriteNews>

    @Delete
    suspend fun delete(news: FavoriteNews)

}