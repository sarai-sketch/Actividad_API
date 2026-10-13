package com.cyberqhatu.attackontitan.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val DarkRed = Color(0xFFD32F2F)
val GoldAmber = Color(0xFFFFB300)
val DarkBackground = Color(0xFF121212)
val CardSurface = Color(0xFF1E1E1E)
val CardBorder = Color(0xFF383838)
val TextPrimary = Color(0xFFF5F5F5)
val TextSecondary = Color(0xFFB0B0B0)

private val DarkColorScheme = darkColorScheme(
    primary = DarkRed,
    secondary = GoldAmber,
    background = DarkBackground,
    surface = CardSurface,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = TextPrimary,
    onSurface = TextPrimary
)

@Composable
fun AttackOnTitanTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        content = content
    )
}
