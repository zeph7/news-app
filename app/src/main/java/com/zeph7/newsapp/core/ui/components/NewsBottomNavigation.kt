package com.zeph7.newsapp.core.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zeph7.newsapp.R
import com.zeph7.newsapp.core.ui.theme.NewsAppTheme

@Composable
fun NewsBottomNavigation(
    items: List<BottomNavigationItem>,
    selectedItem: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, item ->
            val selected = index == selectedItem

            NavigationBarItem(
                selected = selected,
                onClick = { onItemClick(index) },
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = null,
                    )
                },
                label = {
                    Text(
                        text = stringResource(item.text),
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Medium
                    )
                },
            )
        }
    }
}

data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    @StringRes val text: Int
)

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun NewsBottomNavigationPreview() {
    val items = listOf(
        BottomNavigationItem(icon = R.drawable.ic_headline, text = R.string.headlines),
        BottomNavigationItem(icon = R.drawable.ic_search, text = R.string.search),
        BottomNavigationItem(icon = R.drawable.ic_bookmark_outlined, text = R.string.bookmark),
    )
    NewsAppTheme {
        NewsBottomNavigation(
            items = items,
            selectedItem = 0,
            onItemClick = {}
        )
    }
}