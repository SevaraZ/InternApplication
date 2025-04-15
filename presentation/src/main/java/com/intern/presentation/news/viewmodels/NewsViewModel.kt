package com.intern.presentation.news.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.local.news.FavoriteNewsEntity
import com.example.core.models.news.Article
import com.example.domian.usecase.DeleteFavoriteUseCase
import com.example.domian.usecase.GetAllFavoritesUseCase
import com.example.domian.usecase.GetAllNewsUseCase
import com.example.domian.usecase.InsertFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getAllNewsUseCase: GetAllNewsUseCase,
    private val insertUseCase: InsertFavoriteUseCase,
    private val deleteUseCase: DeleteFavoriteUseCase,
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase

) : ViewModel() {
    private val _news = MutableStateFlow<List<Article>>(emptyList())
    val news: StateFlow<List<Article>> = _news.asStateFlow()

    private val _favorites = MutableStateFlow<List<FavoriteNewsEntity>>(emptyList())
    val favorites: StateFlow<List<FavoriteNewsEntity>> = _favorites.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    init {
        loadNews()
        loadFavorites()
    }

    fun loadNews(category: String = "general") {
        viewModelScope.launch {
            _loading.value = true
            try {
                val response = getAllNewsUseCase(category)
                _news.value = response.articles
            } catch (e: Exception) {
                //todo
            } finally {
                _loading.value = false
            }
        }
    }

    fun addToFavorites(article: Article) {
        viewModelScope.launch {
            insertUseCase(FavoriteNewsEntity(article.url, article.title, article.description))
            loadFavorites()
        }
    }

    fun removeFromFavorites(fav: FavoriteNewsEntity) {
        viewModelScope.launch {
            deleteUseCase(fav)
            loadFavorites()
        }
    }

    fun loadFavorites() {
        viewModelScope.launch {
            _favorites.value = getAllFavoritesUseCase()
        }
    }
}