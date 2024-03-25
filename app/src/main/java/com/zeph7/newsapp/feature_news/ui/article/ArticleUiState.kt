package com.zeph7.newsapp.feature_news.ui.article

import com.zeph7.newsapp.feature_news.domain.model.Article

data class ArticleUiState(
    val article: Article? = null,
    val isBookmarked: Boolean = false,
)
