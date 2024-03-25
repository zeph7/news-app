package com.zeph7.newsapp.feature_news.ui.search

import androidx.paging.PagingData
import com.zeph7.newsapp.feature_news.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchUiState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
)