package com.zeph7.newsapp.feature_auth.ui.onboarding

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zeph7.newsapp.R
import com.zeph7.newsapp.core.ui.navigation.Section
import com.zeph7.newsapp.core.ui.theme.NewsAppTheme
import com.zeph7.newsapp.core.ui.theme.xLargePadding

@Composable
fun OnboardingScreen(
    navController: NavController,
    viewModel: OnboardingViewModel
) {
    LaunchedEffect(key1 = viewModel.uiState.appEntry) {
        if (viewModel.uiState.appEntry) {
            navController.navigate(Section.News.route) {
                navController.graph.startDestinationRoute?.let {
                    popUpTo(it) { inclusive = true }
                }
            }
        }
    }

    MainUI(
        onEvent = viewModel::onEvent
    ) {
        navController.navigate(Section.News.route) {
            navController.graph.startDestinationRoute?.let {
                popUpTo(it) { inclusive = true }
            }
        }
    }
}

@Composable
private fun MainUI(
    onEvent: (OnboardingUserEvent) -> Unit,
    navigateToNews: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(xLargePadding)
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(300.dp),
                painter = painterResource(R.drawable.ic_browse),
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Explore the world!",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
            )
        }
        Button(
            modifier = Modifier
                .padding(bottom = xLargePadding)
                .fillMaxWidth()
                .heightIn(min = 50.dp),
            onClick = {
                onEvent(OnboardingUserEvent.SaveFirstLaunch)
                navigateToNews()
            }
        ) {
            Text(
                text = "Next",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MainUiPreview() {
    NewsAppTheme {
        MainUI(
            onEvent = {},
            navigateToNews = {}
        )
    }
}