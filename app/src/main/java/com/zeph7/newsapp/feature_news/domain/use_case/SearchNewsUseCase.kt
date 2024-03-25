package com.zeph7.newsapp.feature_news.domain.use_case

import androidx.paging.PagingData
import com.zeph7.newsapp.feature_news.domain.model.Article
import com.zeph7.newsapp.feature_news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNewsUseCase(
    private val repository: NewsRepository
) {
    operator fun invoke(searchQuery: String): Flow<PagingData<Article>> {
        return repository.getSearchedNews(searchQuery)
    }
}