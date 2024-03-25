package com.zeph7.newsapp.feature_news.ui.headline

sealed class HeadlineUiEvent {
    data class ShowToast(val message: String): HeadlineUiEvent()
    data class ShowSnackBar(val message: String): HeadlineUiEvent()
}