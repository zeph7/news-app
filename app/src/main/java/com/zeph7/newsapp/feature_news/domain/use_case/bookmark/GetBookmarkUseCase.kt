package com.zeph7.newsapp.feature_news.domain.use_case.bookmark

import com.zeph7.newsapp.feature_news.domain.model.Article
import com.zeph7.newsapp.feature_news.domain.repository.NewsRepository

class GetBookmarkUseCase(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(id: Int): Article? {
        return repository.getBookmarkArticle(id)
    }
}