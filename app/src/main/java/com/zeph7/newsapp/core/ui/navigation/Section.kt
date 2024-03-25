package com.zeph7.newsapp.core.ui.navigation

sealed class Section(val route: String) {
    data object Auth: Section("auth")
    data object News: Section("news")
}