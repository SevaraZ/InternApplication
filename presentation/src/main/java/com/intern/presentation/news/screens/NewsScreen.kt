package com.intern.presentation.news.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.mcore.local.news.FavoriteNewsEntity
import com.example.mcore.models.news.Article
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.intern.presentation.news.viewmodels.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    viewModel: NewsViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val news by viewModel.news.collectAsState()
    val favorites by viewModel.favorites.collectAsState()
    var selectedTab by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "News",
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.primary
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
                0 -> NewsList(
                    viewModel = viewModel,
                    news = news,
                    favorites = favorites,
                    navController = navController,
                    onclickItem = { article ->
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            "article",
                            article
                        )
                        navController.navigate("news_detail")


                    }
                )

                1 -> FavoritesList(viewModel, favorites)
            }
        }
    }
}

@Composable
fun NewsList(
    viewModel: NewsViewModel,
    news: List<Article>,
    favorites: List<FavoriteNewsEntity>,
    navController: NavHostController,
    onclickItem: (Article) -> Unit
) {
    val categories = listOf(
        "technology", "sports", "health",
        "business", "science", "entertainment"
    )
    var selectedCategory by remember { mutableStateOf("general") }

    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val isLoading by viewModel.loading.collectAsState()

    Column {
        LazyRow(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(categories) { category ->
                val isSelected = category == selectedCategory
                Box(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                        .alpha(0.5F)
                        .background(
                            color = if (isSelected) Color.Gray else Color.Transparent,
                            shape = MaterialTheme.shapes.medium
                        )
                ) {
                    Text(
                        text = category.replaceFirstChar { it.uppercase() },
                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .clickable {
                                selectedCategory = category
                                viewModel.loadNews(category)
                            }
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    )
                }
            }
        }

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 32.dp),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                androidx.compose.material3.CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary
                )
            }
        } else {
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing),
                onRefresh = { viewModel.refreshNews() }
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
                            },
                            onclickItem = { onclickItem(article) }
                        )
                    }
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadNews(selectedCategory)
    }


    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = { viewModel.refreshNews() }
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(news) { article ->
                NewsItem(
                    article = article,
                    isFavorite = favorites.any { it.url == article.url },
                    onFavoriteClick = {
                        if (favorites.any { it.url == article.url }) {
                            favorites.firstOrNull { it.url == article.url }?.let {
                                viewModel.removeFromFavorites(
                                    fav = it
                                )
                            }
                        } else {
                            viewModel.addToFavorites(article)
                        }
                    },
                    onclickItem = { onclickItem(article) }
                )
            }
        }
    }
}


@Composable
fun NewsItem(
    article: Article,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onclickItem: (Article) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onclickItem(article)
            }
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            article.urlToImage?.let { url ->
                val painter = rememberAsyncImagePainter(article.urlToImage)
                val painterState = painter.state

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    if (painterState is coil.compose.AsyncImagePainter.State.Loading) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.LightGray.copy(alpha = 0.3f)),
                            contentAlignment = androidx.compose.ui.Alignment.Center
                        ) {
                            androidx.compose.material3.CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = article.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(4.dp))

            article.description?.let {
                Text(
                    text = it,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = article.publishedAt.take(10),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 12.sp
                )

                IconButton(onClick = onFavoriteClick) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite
                        else Icons.Default.FavoriteBorder,
                        contentDescription = "Add to favorites",
                        tint = if (isFavorite) Color.Red else Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun FavoritesList(
    viewModel: NewsViewModel,
    favorites: List<FavoriteNewsEntity>
) {
    if (favorites.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Text(
                text = "No favorites yet",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 20.sp
            )
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(favorites) { favorite ->
                FavoriteItem(
                    favorite = favorite,
                    onRemove = { viewModel.removeFromFavorites(favorite) }
                )
            }
        }
    }
}


@Composable
fun FavoriteItem(favorite: FavoriteNewsEntity, onRemove: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = favorite.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(4.dp))

            favorite.description?.let {
                Text(
                    text = it,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary
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
                        tint = Color.Gray
                    )
                }
            }
        }
    }
}