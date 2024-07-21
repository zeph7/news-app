package com.zeph7.newsapp.feature_auth.ui.onboarding

sealed class OnboardingUserEvent {
    data object SaveFirstLaunch: OnboardingUserEvent()
}

data class OnboardingUiState(
    val appEntry: Boolean = false
)