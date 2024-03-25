package com.zeph7.newsapp.feature_news.domain.use_case.bookmark

import com.zeph7.newsapp.feature_news.domain.model.Article
import com.zeph7.newsapp.feature_news.domain.repository.NewsRepository

class DeleteBookmarkUseCase(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(article: Article) {
        repository.deleteBookmarkArticle(article)
    }
}