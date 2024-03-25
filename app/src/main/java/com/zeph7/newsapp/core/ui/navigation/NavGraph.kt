package com.zeph7.newsapp.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.zeph7.newsapp.feature_auth.ui.onboarding.OnboardingScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Section.Auth.route
    ) {
        navigation(
            route = Section.Auth.route,
            startDestination = Screen.Onboarding.route
        ) {
            composable(route = Screen.Onboarding.route) {
                OnboardingScreen(navController, hiltViewModel())
            }
        }

        composable(route = Section.News.route) {
            NewsNavigator()
        }
    }
}