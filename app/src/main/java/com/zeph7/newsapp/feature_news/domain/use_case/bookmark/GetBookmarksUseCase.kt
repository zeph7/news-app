package com.zeph7.newsapp.feature_news.domain.use_case.bookmark

import com.zeph7.newsapp.feature_news.domain.model.Article
import com.zeph7.newsapp.feature_news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetBookmarksUseCase(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<List<Article>> {
        return repository.getBookmarkArticles()
    }
}