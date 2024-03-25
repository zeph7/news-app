package com.zeph7.newsapp.feature_news.ui.headline

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeph7.newsapp.core.data.util.Resource
import com.zeph7.newsapp.feature_news.domain.use_case.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeadlineViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase
) : ViewModel() {

    var uiState by mutableStateOf(HeadlineUiState())
        private set

    private var _uiEvent = MutableSharedFlow<HeadlineUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        getHeadlines()
    }

    fun onEvent(event: HeadlineUserEvent) {
        when (event) {
            HeadlineUserEvent.RefreshHeadlines -> getHeadlines()
        }
    }

    private fun getHeadlines() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            uiState = when (val response = getArticlesUseCase()) {
                is Resource.Loading -> {
                    uiState.copy(isLoading = true)
                }
                is Resource.Success -> {
                    uiState.copy(isLoading = false, articles = response.data!!)
                }
                is Resource.Error -> {
                    _uiEvent.emit(HeadlineUiEvent.ShowSnackBar(response.message!!))
                    uiState.copy(isLoading = false, message = response.message)
                }
            }
        }
    }

}