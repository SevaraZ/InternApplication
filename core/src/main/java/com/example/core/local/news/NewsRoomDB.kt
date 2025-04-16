package com.example.core.local.news

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorite_news")
data class FavoriteNewsEntity(
    @PrimaryKey val url: String,
    val title: String,
    val description: String?

)