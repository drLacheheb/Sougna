package com.example.sougna

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.foundation.isSystemInDarkTheme

private val DarkColorPalette = darkColorScheme(
    primary = Orange700,
    secondary = Orange200,
    background = DarkOrange
)

private val LightColorPalette = lightColorScheme(
    primary = Orange500,
    secondary = Orange200,
    background = LightGray
)

@Composable
fun ItemListTheme(content: @Composable () -> Unit) {
    val colors = if (isSystemInDarkTheme()) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
