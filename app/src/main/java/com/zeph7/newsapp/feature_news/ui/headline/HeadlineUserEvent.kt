package com.zeph7.newsapp.feature_news.ui.headline

sealed class HeadlineUserEvent {
    data object RefreshHeadlines: HeadlineUserEvent()
}