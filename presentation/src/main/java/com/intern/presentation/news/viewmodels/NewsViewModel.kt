package com.intern.presentation.news.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.internapp.network.news.ApiObject
import com.intern.data.models.news.Article
import com.intern.data.local.news.FavoriteNewsDao
import com.intern.data.local.news.FavoriteNewsEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsViewModel(
    private val apiObject: ApiObject,
    private val dao: FavoriteNewsDao
) : ViewModel() {
    private val _news = MutableStateFlow<List<Article>>(emptyList())
    val news: StateFlow<List<Article>> = _news

    private val _favorites = MutableStateFlow<List<FavoriteNewsEntity>>(emptyList())
    val favorites: StateFlow<List<FavoriteNewsEntity>> = _favorites

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    fun loadNews(category: String = "general") {
        viewModelScope.launch {
            _loading.value = true
            try {
                val response = apiObject.api.getTopHeadlines(category)
                _news.value = response.articles
            } catch (e: Exception) {


            } finally {
                _loading.value = false
            }
        }
    }

    fun addToFavorites(article: Article) {
        viewModelScope.launch {
            dao.insert(FavoriteNewsEntity(article.url, article.title, article.description))
            loadFavorites()
        }
    }

    fun removeFromFavorites(fav: FavoriteNewsEntity) {
        viewModelScope.launch {
            dao.delete(fav)
            loadFavorites()
        }
    }

    fun loadFavorites() {
        viewModelScope.launch {
            _favorites.value = dao.getAllFavorites()
        }
    }
}