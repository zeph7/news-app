package com.zeph7.newsapp.feature_news.domain.use_case.bookmark

import com.zeph7.newsapp.feature_news.domain.model.Article
import com.zeph7.newsapp.feature_news.domain.repository.NewsRepository

class UpsertBookmarkUseCase(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(article: Article) {
        repository.upsertBookmarkArticle(article)
    }
}