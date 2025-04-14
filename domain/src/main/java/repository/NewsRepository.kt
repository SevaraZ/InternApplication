package repository

import com.example.core.models.news.NewsResponse

interface NewsRepository {

    suspend fun getTopHeadlines(category: String, apiKey: String, country: String): NewsResponse

}