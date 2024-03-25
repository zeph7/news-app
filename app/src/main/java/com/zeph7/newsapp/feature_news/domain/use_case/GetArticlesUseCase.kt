package com.zeph7.newsapp.feature_news.domain.use_case

import com.zeph7.newsapp.core.data.util.Resource
import com.zeph7.newsapp.feature_news.domain.model.Article
import com.zeph7.newsapp.feature_news.domain.repository.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetArticlesUseCase(
    private val repository: NewsRepository,
    private val ioDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(): Resource<List<Article>> {
        return withContext(ioDispatcher) {
            repository.getHeadlines()
        }
    }
}