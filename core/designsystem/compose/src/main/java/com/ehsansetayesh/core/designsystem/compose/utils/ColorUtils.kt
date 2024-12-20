package ir.hafhashtad.android780.core.designsystem.compose.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalDarkTheme = staticCompositionLocalOf { false }

val isDarkTheme: Boolean
    @Composable
    @ReadOnlyComposable
    get() = LocalDarkTheme.current

@Composable
internal fun colorPack(lightColor: Color, darkColor: Color): Color {
    return if (isDarkTheme) {
        darkColor
    } else {
        lightColor
    }
}