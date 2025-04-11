package com.example.internapp.data.news

data class NewsResponse(val status: String, val articles: List<Article>)
data class Article(
    val source: Source,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)
data class Source(val name: String)
