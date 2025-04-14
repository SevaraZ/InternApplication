package com.example.core.models.news

data class NewsResponse(val status: String, val articles: List<Article>)
data class Article(
    val source: SourceData,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)