package com.zeph7.newsapp.feature_auth.ui.onboarding

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeph7.newsapp.feature_auth.domain.use_case.OnboardingUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val useCases: OnboardingUseCases
) : ViewModel() {

    var uiState by mutableStateOf(OnboardingUiState())
        private set

    init {
        getAppEntry()
    }

    fun onEvent(event: OnboardingUserEvent) {
        when (event) {
            OnboardingUserEvent.SaveFirstLaunch -> setAppEntry()
        }
    }

    private fun setAppEntry() {
        viewModelScope.launch {
            useCases.setAppEntry()
        }
    }

    private fun getAppEntry() {
        viewModelScope.launch {
            uiState = uiState.copy(appEntry = useCases.getAppEntry())
        }
    }

}