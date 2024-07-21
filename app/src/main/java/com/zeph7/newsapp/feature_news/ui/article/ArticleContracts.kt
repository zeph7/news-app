package com.zeph7.newsapp.feature_news.ui.article

import com.zeph7.newsapp.feature_news.domain.model.Article

sealed class ArticleUiEvent {
    data class ShowToast(val message: String): ArticleUiEvent()
    data class ShowSnackBar(val message: String): ArticleUiEvent()
}

sealed class ArticleUserEvent {
    data object BookmarkArticle: ArticleUserEvent()
}

data class ArticleUiState(
    val article: Article? = null,
    val isBookmarked: Boolean = false,
)