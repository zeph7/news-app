package com.zeph7.newsapp.feature_news.data.remote.dto

data class HeadlineDto(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleDto>
)