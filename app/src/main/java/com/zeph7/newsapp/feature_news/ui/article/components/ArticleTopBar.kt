@file:OptIn(ExperimentalMaterial3Api::class)

package com.zeph7.newsapp.feature_news.ui.article.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.zeph7.newsapp.R
import com.zeph7.newsapp.core.ui.theme.NewsAppTheme

@Composable
fun ArticleTopBar(
    isBookmarked: Boolean,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    onBrowsingClick: () -> Unit,
    onBookMarkClick: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.Transparent),
        title = {},
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = null,
                )
            }
        },
        actions = {
            IconButton(onClick = onShareClick) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = null,
                )
            }
            IconButton(onClick = onBrowsingClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_browse),
                    contentDescription = null,
                )
            }
            IconButton(onClick = onBookMarkClick) {
                Icon(
                    painter = painterResource(
                        if (isBookmarked) R.drawable.ic_bookmark_filled
                        else R.drawable.ic_bookmark_outlined
                    ),
                    contentDescription = null,
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DetailsTopBarPreview() {
    NewsAppTheme {
        ArticleTopBar(
            isBookmarked = false,
            onBackClick = {},
            onShareClick = {},
            onBookMarkClick = {},
            onBrowsingClick = {}
        )
    }
}