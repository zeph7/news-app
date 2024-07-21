package com.zeph7.newsapp.feature_news.ui.headline

import com.zeph7.newsapp.feature_news.domain.model.Article

sealed class HeadlineUiEvent {
    data class ShowToast(val message: String): HeadlineUiEvent()
    data class ShowSnackBar(val message: String): HeadlineUiEvent()
}

sealed class HeadlineUserEvent {
    data object RefreshHeadlines: HeadlineUserEvent()
}

data class HeadlineUiState(
    val isLoading: Boolean = false,
    val message: String = "",
    val articles: List<Article> = listOf()
)