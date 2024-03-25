@file:OptIn(ExperimentalMaterial3Api::class)

package com.zeph7.newsapp.feature_news.ui.headline

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HeadlineScreen(
    navController: NavController,
    viewModel: HeadlineViewModel
) {
    val context = LocalContext.current
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = Unit) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is HeadlineUiEvent.ShowToast -> Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                is HeadlineUiEvent.ShowSnackBar -> snackBarHostState.showSnackbar(event.message)
            }
        }
    }

    MainUI(
        uiState = viewModel.uiState,
        onEvent = viewModel::onEvent,
        snackBarHost = { SnackbarHost(hostState = snackBarHostState) },
        navigateToArticle = {
            navController.navigate(Screen.Article.route + "/${it.encodeToString()}")
        }
    )
}

@Composable
private fun MainUI(
    uiState: HeadlineUiState,
    onEvent: (HeadlineUserEvent) -> Unit,
    snackBarHost: @Composable () -> Unit,
    navigateToArticle: (Article) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.padding(vertical = smallPadding),
                        text = stringResource(R.string.top_headlines),
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            )
        },
        snackbarHost = snackBarHost
    ) {
        Crossfade(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            targetState = uiState.isLoading,
            label = ""
        ) { isLoading ->
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                    content = { CircularProgressIndicator() }
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(xLargePadding),
                    verticalArrangement = Arrangement.spacedBy(xLargePadding)
                ) {
                    items(uiState.articles) { item ->
                        ArticleItem(
                            article = item,
                            onClick = { navigateToArticle(item) }
                        )
                    }
                }

                if (uiState.articles.isEmpty() && uiState.message.isNotEmpty()) {
                    EmptyContent(
                        message = uiState.message,
                        icon = R.drawable.ic_network_error,
                        onRetryClick = { onEvent(HeadlineUserEvent.RefreshHeadlines) }
                    )
                }
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
            uiState = HeadlineUiState(articles = articles),
            onEvent = {},
            snackBarHost = {},
            navigateToArticle = {}
        )
    }
}