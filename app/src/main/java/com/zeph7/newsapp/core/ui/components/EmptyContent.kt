package com.zeph7.newsapp.core.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zeph7.newsapp.R

@Composable
fun EmptyContent(
    message: String,
    icon: Int,
    onRetryClick: (() -> Unit)?
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(120.dp),
            painter = painterResource(icon),
            tint = if (isSystemInDarkTheme()) LightGray else DarkGray,
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(10.dp),
            text = message,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium,
            color = if (isSystemInDarkTheme()) LightGray else DarkGray,
        )
        onRetryClick?.let {
            Button(onClick = it) {
                Text(
                    text = "Retry",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun EmptyScreenPreview() {
    EmptyContent(
        message = "Internet Unavailable.",
        icon = R.drawable.ic_network_error,
        onRetryClick = {}
    )
}