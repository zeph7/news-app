package com.zeph7.newsapp.feature_news.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zeph7.newsapp.feature_news.data.remote.dto.ArticleDto

class SearchNewsPagingSource(
    private val api: NewsApi,
    private val searchQuery: String,
) : PagingSource<Int, ArticleDto>() {

    override fun getRefreshKey(state: PagingState<Int, ArticleDto>): Int? {
        return state.anchorPosition?.let { anchorPage ->
            val page = state.closestPageToPosition(anchorPage)
            page?.nextKey?.minus(1) ?: page?.prevKey?.plus(1)
        }
    }

    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleDto> {
        val page = params.key ?: 1
        return try {
            val newsResponse = api.getSearchedNews(
                searchQuery = searchQuery,
                page = page,
            )
            totalNewsCount += newsResponse.articles.size
            val articles = newsResponse.articles.distinctBy { it.title } //Remove duplicates

            LoadResult.Page(
                data = articles,
                nextKey = if (totalNewsCount == newsResponse.totalResults) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }

}