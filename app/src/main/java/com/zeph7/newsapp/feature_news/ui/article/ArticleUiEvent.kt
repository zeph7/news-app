package com.zeph7.newsapp.feature_news.ui.article

sealed class ArticleUiEvent {
    data class ShowToast(val message: String): ArticleUiEvent()
    data class ShowSnackBar(val message: String): ArticleUiEvent()
}