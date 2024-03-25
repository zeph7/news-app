package com.zeph7.newsapp.feature_news.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.zeph7.newsapp.core.data.util.Resource
import com.zeph7.newsapp.core.util.Constants
import com.zeph7.newsapp.feature_news.data.local.NewsDao
import com.zeph7.newsapp.feature_news.data.mapper.toArticle
import com.zeph7.newsapp.feature_news.data.mapper.toArticleEntity
import com.zeph7.newsapp.feature_news.data.remote.NewsApi
import com.zeph7.newsapp.feature_news.data.remote.SearchNewsPagingSource
import com.zeph7.newsapp.feature_news.domain.model.Article
import com.zeph7.newsapp.feature_news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao,
) : NewsRepository {

    override suspend fun getHeadlines(): Resource<List<Article>> {
        return try {
            val response = newsApi.getTopHeadlines()
            if (response.status == "ok") Resource.Success(response.articles.map { it.toArticle() })
            else Resource.Error("Server error!")
        } catch (e: HttpException) {
            Resource.Error("Something went wrong!")
        } catch (e: IOException) {
            Resource.Error("No Internet Connection!")
        }
    }

    override fun getSearchedNews(searchQuery: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.PAGE_SIZE),
            pagingSourceFactory = { SearchNewsPagingSource(newsApi, searchQuery) }
        ).flow.map { data ->
            data.map { articleDto ->
                articleDto.toArticle()
            }
        }
    }

    override suspend fun upsertBookmarkArticle(article: Article) {
        newsDao.upsertBookmarkArticle(article.toArticleEntity())
    }

    override suspend fun deleteBookmarkArticle(article: Article) {
        newsDao.deleteBookmarkArticle(article.toArticleEntity())
    }

    override suspend fun getBookmarkArticle(id: Int): Article? {
        return newsDao.getBookmarkArticle(id)?.toArticle()
    }

    override fun getBookmarkArticles(): Flow<List<Article>> {
        return newsDao.getBookmarkArticles().map { list ->
            list.map { entity ->
                entity.toArticle()
            }
        }
    }

}