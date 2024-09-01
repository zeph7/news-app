package com.zeph7.newsapp.feature_news.fake

import com.zeph7.newsapp.feature_news.data.local.NewsDao
import com.zeph7.newsapp.feature_news.data.local.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

abstract class NewsDaoStub : NewsDao {

    override suspend fun upsertBookmarkArticle(article: ArticleEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteBookmarkArticle(article: ArticleEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun getBookmarkArticle(id: Int): ArticleEntity? {
        TODO("Not yet implemented")
    }

    override fun getBookmarkArticles(): Flow<List<ArticleEntity>> {
        TODO("Not yet implemented")
    }
}