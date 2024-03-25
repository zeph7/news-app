package com.zeph7.newsapp.core.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.zeph7.newsapp.R
import com.zeph7.newsapp.core.ui.components.BottomNavigationItem
import com.zeph7.newsapp.core.ui.components.NewsBottomNavigation
import com.zeph7.newsapp.feature_news.ui.article.ArticleScreen
import com.zeph7.newsapp.feature_news.ui.bookmark.BookmarkScreen
import com.zeph7.newsapp.feature_news.ui.headline.HeadlineScreen
import com.zeph7.newsapp.feature_news.ui.search.SearchScreen
import com.zeph7.newsapp.feature_news.util.Constants

@Composable
fun NewsNavigator() {
    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }

    LaunchedEffect(backStackState?.destination?.route) {
        selectedItem = when (backStackState?.destination?.route) {
            Screen.Headline.route -> 0
            Screen.Search.route -> 1
            Screen.Bookmark.route -> 2
            else -> 0
        }
    }

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_headline, text = R.string.headlines),
            BottomNavigationItem(icon = R.drawable.ic_search, text = R.string.search),
            BottomNavigationItem(icon = R.drawable.ic_bookmark_outlined, text = R.string.bookmark),
        )
    }
    val isBottomBarVisible = remember(backStackState) {
        backStackState?.destination?.route == Screen.Headline.route ||
        backStackState?.destination?.route == Screen.Search.route ||
        backStackState?.destination?.route == Screen.Bookmark.route
    }

    Scaffold(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize(),
        bottomBar = {
            AnimatedVisibility(
                visible = isBottomBarVisible,
                enter = fadeIn(tween(durationMillis = 200)) + slideInVertically(tween(durationMillis = 200)) { it },
                exit = fadeOut(tween(delayMillis = 100)) + slideOutVertically(tween(delayMillis = 100)) { it }
            ) {
                NewsBottomNavigation(
                    items = bottomNavigationItems,
                    selectedItem = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTab(
                                navController = navController,
                                route = Screen.Headline.route
                            )

                            1 -> navigateToTab(
                                navController = navController,
                                route = Screen.Search.route
                            )

                            2 -> navigateToTab(
                                navController = navController,
                                route = Screen.Bookmark.route
                            )
                        }
                    }
                )
            }
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = Screen.Headline.route
        ) {
            composable(route = Screen.Headline.route) {
                HeadlineScreen(navController, hiltViewModel())
            }

            composable(route = Screen.Search.route) {
                OnBackClickStateSaver(navController)
                SearchScreen(navController, hiltViewModel())
            }

            composable(route = Screen.Bookmark.route) {
                OnBackClickStateSaver(navController)
                BookmarkScreen(navController, hiltViewModel())
            }

            composable(
                route = Screen.Article.route + "/{${Constants.ARTICLE_ARG}}",
                arguments = listOf(navArgument(Constants.ARTICLE_ARG) { type = NavType.StringType }),
                content = { ArticleScreen(navController, hiltViewModel()) }
            )
        }
    }
}

@Composable
fun OnBackClickStateSaver(navController: NavController) {
    BackHandler {
        navigateToTab(
            navController = navController,
            route = Screen.Headline.route
        )
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let {
            popUpTo(it) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}