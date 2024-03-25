package com.zeph7.newsapp.core.ui.navigation

sealed class Screen(val route: String) {
    data object Onboarding: Screen("onboarding")
    data object Headline: Screen("headline")
    data object Article: Screen("article")
    data object Search: Screen("search")
    data object Bookmark: Screen("bookmark")
}