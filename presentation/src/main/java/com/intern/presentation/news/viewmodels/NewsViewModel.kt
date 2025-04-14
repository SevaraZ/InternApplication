package com.intern.presentation.news.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.internapp.network.news.ApiObject
import com.intern.data.local.news.FavoriteNewsDao
import com.intern.data.local.news.FavoriteNewsEntity
import com.intern.domain.usecase.GetAllNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(
    private val apiObject: ApiObject,
    private val dao: FavoriteNewsDao,
    private val getAllNewsUseCase: GetAllNewsUseCase
) : ViewModel() {
    private val _news = MutableStateFlow<List<com.example.core.models.news.Article>>(emptyList())
    val news: StateFlow<List<com.example.core.models.news.Article>> = _news

    private val _favorites = MutableStateFlow<List<FavoriteNewsEntity>>(emptyList())
    val favorites: StateFlow<List<FavoriteNewsEntity>> = _favorites

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    fun loadNews(category: String = "general") {
        viewModelScope.launch {
            _loading.value = true
            try {
                val t = getAllNewsUseCase("", "", "")

                val response = apiObject.api.getTopHeadlines(category)
                _news.value = response.articles
            } catch (e: Exception) {


            } finally {
                _loading.value = false
            }
        }
    }

    fun addToFavorites(article: com.example.core.models.news.Article) {
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