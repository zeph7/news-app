package com.zeph7.newsapp.feature_news.data.remote

import com.zeph7.newsapp.BuildConfig
import com.zeph7.newsapp.core.util.Constants
import com.zeph7.newsapp.feature_news.data.remote.dto.HeadlineDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "in",
        @Query("apiKey") apiKey: String = API_KEY,
    ): HeadlineDto

    @GET("everything")
    suspend fun getSearchedNews(
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = Constants.PAGE_SIZE,
        @Query("apiKey") apiKey: String = API_KEY,
    ): HeadlineDto

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
        const val API_KEY = BuildConfig.API_KEY
    }

}