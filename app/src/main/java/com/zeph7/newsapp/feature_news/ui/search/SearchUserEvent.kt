package com.zeph7.newsapp.feature_news.ui.search

sealed class SearchUserEvent {
    data class SearchNews(val query: String): SearchUserEvent()
}