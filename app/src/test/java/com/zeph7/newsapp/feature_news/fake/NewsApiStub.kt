package com.zeph7.newsapp.feature_news.fake

import com.zeph7.newsapp.feature_news.data.remote.NewsApi
import com.zeph7.newsapp.feature_news.data.remote.dto.HeadlineDto

abstract class NewsApiStub : NewsApi {

    override suspend fun getTopHeadlines(country: String, apiKey: String): HeadlineDto {
        TODO("Not yet implemented")
    }

    override suspend fun getSearchedNews(searchQuery: String, page: Int, pageSize: Int, apiKey: String): HeadlineDto {
        TODO("Not yet implemented")
    }
}