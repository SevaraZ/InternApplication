package com.intern.presentation.news.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.intern.data.local.news.FavoriteNewsEntity
import com.intern.data.models.news.Article
import com.intern.presentation.news.viewmodels.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(viewModel: NewsViewModel) {
    val news by viewModel.news.collectAsState()
    val favorites by viewModel.favorites.collectAsState()
    var selectedTab by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        viewModel.loadNews()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("News", color = Color.Black) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = Color.White,
                contentColor = Color.Black
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = { Text("All") }
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = { Text("Favorites") }
                )
            }

            when (selectedTab) {
                0 -> NewsList(viewModel, news, favorites)
                1 -> FavoritesList(viewModel, favorites)
            }
        }
    }
}

@Composable
fun NewsList(
    viewModel: NewsViewModel,
    news: List<Article>,
    favorites: List<FavoriteNewsEntity>
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(news) { article ->
            NewsItem(
                article = article,
                isFavorite = favorites.any { it.url == article.url },
                onFavoriteClick = {
                    if (favorites.any { it.url == article.url }) {
                        favorites.firstOrNull { it.url == article.url }?.let {
                            viewModel.removeFromFavorites(it)
                        }
                    } else {
                        viewModel.addToFavorites(article)
                    }
                }
            )
            Divider(color = Color.LightGray, thickness = 0.5.dp)
        }
    }
}

@Composable
fun NewsItem(
    article: Article,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            article.urlToImage?.let { url ->
                Image(
                    painter = rememberAsyncImagePainter(url),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = article.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(4.dp))

            article.description?.let {
                Text(
                    text = it,
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = article.publishedAt.take(10),
                    color = Color.Gray,
                    fontSize = 12.sp
                )

                IconButton(onClick = onFavoriteClick) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Add to favorites",
                        tint = if (isFavorite) Color.Black else Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun FavoritesList(viewModel: NewsViewModel, favorites: List<FavoriteNewsEntity>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(favorites) { favorite ->
            FavoriteItem(
                favorite = favorite,
                onRemove = { viewModel.removeFromFavorites(favorite) }
            )
            Divider(color = Color.LightGray, thickness = 0.5.dp)
        }
    }
}

@Composable
fun FavoriteItem(favorite: FavoriteNewsEntity, onRemove: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = favorite.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(4.dp))

            favorite.description?.let {
                Text(
                    text = it,
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onRemove) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Remove",
                        tint = Color.Black
                    )
                }
            }
        }
    }
}