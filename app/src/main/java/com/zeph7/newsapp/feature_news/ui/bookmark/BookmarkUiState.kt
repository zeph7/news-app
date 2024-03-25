package com.zeph7.newsapp.feature_news.ui.bookmark

import com.zeph7.newsapp.feature_news.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class BookmarkUiState(
    val articles: Flow<List<Article>>? = null
)