package com.zeph7.newsapp.feature_news.domain.repository

import androidx.paging.PagingData
import com.zeph7.newsapp.core.data.util.Resource
import com.zeph7.newsapp.feature_news.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getHeadlines(): Resource<List<Article>>

    fun getSearchedNews(searchQuery: String): Flow<PagingData<Article>>

    suspend fun upsertBookmarkArticle(article: Article)

    suspend fun deleteBookmarkArticle(article: Article)

    suspend fun getBookmarkArticle(id: Int): Article?

    fun getBookmarkArticles(): Flow<List<Article>>

}