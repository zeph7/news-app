package com.zeph7.newsapp.feature_news.ui.article

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeph7.newsapp.feature_news.domain.use_case.bookmark.BookmarkUseCases
import com.zeph7.newsapp.feature_news.util.Constants
import com.zeph7.newsapp.feature_news.util.decodeFromString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val bookmarkUseCases: BookmarkUseCases,
): ViewModel() {

    var uiState by mutableStateOf(ArticleUiState())
        private set

    init {
        val data = savedStateHandle.get<String>(Constants.ARTICLE_ARG)
        uiState = uiState.copy(article = data?.decodeFromString())
        isArticleBookmark()
    }

    fun onEvent(event: ArticleUserEvent) {
        when (event) {
            ArticleUserEvent.BookmarkArticle -> bookmarkArticle()
        }
    }

    private fun isArticleBookmark() {
        viewModelScope.launch {
            bookmarkUseCases.getBookmark(uiState.article.hashCode())?.let {
                uiState = uiState.copy(isBookmarked = true)
            }
        }
    }

    private fun bookmarkArticle() {
        viewModelScope.launch {
            if (!uiState.isBookmarked) uiState.article?.let { bookmarkUseCases.upsertBookmark(it) }
            else uiState.article?.let { bookmarkUseCases.deleteBookmark(it) }
            uiState = uiState.copy(isBookmarked = !uiState.isBookmarked)
        }
    }

}