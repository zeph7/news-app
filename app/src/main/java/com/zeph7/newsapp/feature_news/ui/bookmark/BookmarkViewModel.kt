package com.zeph7.newsapp.feature_news.ui.bookmark

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeph7.newsapp.feature_news.domain.use_case.bookmark.BookmarkUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val bookmarkUseCases: BookmarkUseCases
): ViewModel() {

    var uiState by mutableStateOf(BookmarkUiState())
        private set

    init {
        getBookmarks()
    }

    private fun getBookmarks() {
        viewModelScope.launch {
            uiState = uiState.copy(articles = bookmarkUseCases.getBookmarks())
        }
    }

}