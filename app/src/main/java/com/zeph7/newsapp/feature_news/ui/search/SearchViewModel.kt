package com.zeph7.newsapp.feature_news.ui.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.zeph7.newsapp.feature_news.domain.use_case.SearchNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchNewsUseCase: SearchNewsUseCase,
): ViewModel() {

    var uiState by mutableStateOf(SearchUiState())
        private set

    fun onEvent(event: SearchUserEvent) {
        when (event) {
            is SearchUserEvent.SearchNews -> searchNews(event.query)
        }
    }

    private fun searchNews(query: String) {
        uiState = uiState.copy(searchQuery = query)
        val articles = searchNewsUseCase(uiState.searchQuery).cachedIn(viewModelScope)
        uiState = uiState.copy(articles = articles)
    }

}