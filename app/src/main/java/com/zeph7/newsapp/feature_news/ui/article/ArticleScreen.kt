package com.zeph7.newsapp.feature_news.ui.article

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.zeph7.newsapp.core.ui.theme.NewsAppTheme
import com.zeph7.newsapp.core.ui.theme.xLargePadding
import com.zeph7.newsapp.feature_news.domain.model.Article
import com.zeph7.newsapp.feature_news.ui.article.components.ArticleTopBar

@Composable
fun ArticleScreen(
    navController: NavController,
    viewModel: ArticleViewModel
) {
    MainUI(
        uiState = viewModel.uiState,
        event = viewModel::onEvent,
        navigateUp = { navController.navigateUp() }
    )
}

@Composable
fun MainUI(
    uiState: ArticleUiState,
    event: (ArticleUserEvent) -> Unit,
    navigateUp: () -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ArticleTopBar(
                isBookmarked = uiState.isBookmarked,
                onBackClick = navigateUp,
                onBrowsingClick = {
                    Intent(Intent.ACTION_VIEW).also {
                        it.data = Uri.parse(uiState.article?.url)
                        if (it.resolveActivity(context.packageManager) != null) {
                            context.startActivity(it)
                        }
                    }
                },
                onShareClick = {
                    Intent(Intent.ACTION_SEND).also {
                        it.putExtra(Intent.EXTRA_TEXT, uiState.article?.url)
                        it.type = "text/plain"
                        if (it.resolveActivity(context.packageManager) != null) {
                            context.startActivity(it)
                        }
                    }
                },
                onBookMarkClick = {
                    event(ArticleUserEvent.BookmarkArticle)
                },
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
            item {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(MaterialTheme.shapes.large)
                        .background(color = Color.Gray),
                    model = ImageRequest.Builder(context).data(uiState.article?.urlToImage).build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }

            item {
                Text(
                    text = uiState.article?.title ?: "",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            uiState.article?.content?.let { content ->
                item {
                    Text(
                        text = content,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DetailsScreenPreview() {
    NewsAppTheme {
        MainUI(
            uiState = ArticleUiState(
                article = Article(
                    author = "",
                    title = "Coinbase says Apple blocked its last app release on NFTs in Wallet ... - CryptoSaurus",
                    description = "Coinbase says Apple blocked its last app release on NFTs in Wallet ... - CryptoSaurus",
                    content = "We use cookies and data to Deliver and maintain Google services Track outages and protect against spam, fraud, and abuse Measure audience engagement and site statistics to unde… [+1131 chars]",
                    publishedAt = "2023-06-16T22:24:33Z",
                    source = "bbc",
                    url = "https://consent.google.com/ml?continue=https://news.google.com/rss/articles/CBMiaWh0dHBzOi8vY3J5cHRvc2F1cnVzLnRlY2gvY29pbmJhc2Utc2F5cy1hcHBsZS1ibG9ja2VkLWl0cy1sYXN0LWFwcC1yZWxlYXNlLW9uLW5mdHMtaW4td2FsbGV0LXJldXRlcnMtY29tL9IBAA?oc%3D5&gl=FR&hl=en-US&cm=2&pc=n&src=1",
                    urlToImage = "https://media.wired.com/photos/6495d5e893ba5cd8bbdc95af/191:100/w_1280,c_limit/The-EU-Rules-Phone-Batteries-Must-Be-Replaceable-Gear-2BE6PRN.jpg"
                ),
            ),
            event = {},
            navigateUp = {}
        )
    }
}