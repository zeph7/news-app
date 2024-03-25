@file:OptIn(ExperimentalMaterial3Api::class)

package com.zeph7.newsapp.feature_news.ui.bookmark

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.zeph7.newsapp.R
import com.zeph7.newsapp.core.ui.components.EmptyContent
import com.zeph7.newsapp.core.ui.navigation.Screen
import com.zeph7.newsapp.core.ui.theme.NewsAppTheme
import com.zeph7.newsapp.core.ui.theme.smallPadding
import com.zeph7.newsapp.core.ui.theme.xLargePadding
import com.zeph7.newsapp.feature_news.domain.model.Article
import com.zeph7.newsapp.feature_news.ui.common.ArticleItem
import com.zeph7.newsapp.feature_news.util.encodeToString
import kotlinx.coroutines.flow.flowOf

@Composable
fun BookmarkScreen(
    navController: NavController,
    viewModel: BookmarkViewModel,
) {
    MainUI(
        uiState = viewModel.uiState,
        navigateToArticle = {
            navController.navigate(Screen.Article.route + "/${it.encodeToString()}")
        }
    )
}

@Composable
private fun MainUI(
    uiState: BookmarkUiState,
    navigateToArticle: (Article) -> Unit
) {
    val articles = uiState.articles?.collectAsState(initial = listOf())?.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.padding(vertical = smallPadding),
                        text = stringResource(R.string.bookmarked),
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            contentPadding = PaddingValues(xLargePadding),
            verticalArrangement = Arrangement.spacedBy(xLargePadding)
        ) {
            if (!articles.isNullOrEmpty()) {
                items(articles) { item ->
                    ArticleItem(
                        article = item,
                        onClick = { navigateToArticle(item) }
                    )
                }
            }
        }

        if (articles.isNullOrEmpty()) {
            EmptyContent(
                message = "No bookmarks yet!",
                icon = R.drawable.ic_bookmark_outlined,
                onRetryClick = null
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MainUIPreview() {
    val articles = mutableListOf<Article>()

    repeat(5) {
        articles.add(
            Article(
                source = "My news",
                author = "The author",
                title = "This is the main news title headline. This is the main news title headline.",
                description = "This is the main news description. This is the main news description. This is the main news description",
                url = "",
                urlToImage = "",
                publishedAt = "",
                content = "What is the content?"
            )
        )
    }

    NewsAppTheme {
        MainUI(
            uiState = BookmarkUiState(articles = flowOf(articles)),
            navigateToArticle = {}
        )
    }
}