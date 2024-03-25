package com.zeph7.newsapp.feature_news.ui.article

sealed class ArticleUserEvent {
    data object BookmarkArticle: ArticleUserEvent()
}