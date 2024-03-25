@file:OptIn(ExperimentalMaterial3Api::class)

package com.zeph7.newsapp.feature_news.ui.search

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.zeph7.newsapp.R
import com.zeph7.newsapp.core.ui.components.EmptyContent
import com.zeph7.newsapp.core.ui.navigation.Screen
import com.zeph7.newsapp.core.ui.theme.NewsAppTheme
import com.zeph7.newsapp.core.ui.theme.mediumPadding
import com.zeph7.newsapp.core.ui.theme.smallPadding
import com.zeph7.newsapp.feature_news.domain.model.Article
import com.zeph7.newsapp.feature_news.ui.search.components.ArticlesList
import com.zeph7.newsapp.feature_news.ui.search.components.SearchBar
import com.zeph7.newsapp.feature_news.util.encodeToString

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel,
) {
    MainUI(
        uiState = viewModel.uiState,
        onEvent = viewModel::onEvent,
        navigateToArticle = {
            navController.navigate(Screen.Article.route + "/${it.encodeToString()}")
        }
    )
}

@Composable
private fun MainUI(
    uiState: SearchUiState,
    onEvent: (SearchUserEvent) -> Unit,
    navigateToArticle: (Article) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.padding(vertical = smallPadding),
                        text = stringResource(R.string.search_news),
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.spacedBy(mediumPadding)
        ) {
            SearchBar(
                modifier = Modifier.padding(horizontal = mediumPadding),
                text = uiState.searchQuery,
                onSearch = { query -> onEvent(SearchUserEvent.SearchNews(query)) },
            )

            uiState.articles?.let { articles ->
                ArticlesList(
                    articles = articles.collectAsLazyPagingItems(),
                    onClick = navigateToArticle
                )
            }

            if (uiState.articles == null) {
                EmptyContent(
                    message = "Type to search!",
                    icon = R.drawable.ic_browse,
                    onRetryClick = null
                )
            }
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
            uiState = SearchUiState(),
            onEvent = {},
            navigateToArticle = {}
        )
    }
}