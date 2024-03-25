package com.zeph7.newsapp.feature_news.ui.headline

import com.zeph7.newsapp.feature_news.domain.model.Article

data class HeadlineUiState(
    val isLoading: Boolean = false,
    val message: String = "",
    val articles: List<Article> = listOf()
)