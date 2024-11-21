package com.ehsansetayesh.core.designsystem.compose.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import ir.hafhashtad.android780.core.designsystem.compose.utils.LocalDarkTheme

private val STATUS_BAR_COLOR = Color(0xFF105FAE).toArgb()
private val NAVIGATION_BAR_LIGHT_COLOR = Color(0xFFfdfdfd).toArgb()
private val NAVIGATION_BAR_DARK_COLOR = Color(0xFF252C36).toArgb()

/**
 * The main theme composable function for the Hafhashtad application.
 *
 * This function provides a consistent theme across the application, including colors,
 * typography, shapes, spacing, radius, and elevation. It also establishes a
 * `CompositionLocalProvider` to make these theme values accessible to composable
 * throughout the app.
 *
 * @param darkTheme A boolean value indicating whether to use the dark theme. Defaults to the system setting.
 * @param content The content to be displayed within the theme.
 */
@Composable
fun HafhashtadTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorsScheme else LightColorsScheme

    /**
     * Applies status bar and navigation bar colors based on the current theme.
     * This effect only modifies the system bars if called from an Activity context.
     * In edit mode or other contexts, no changes are applied.
     */
    val view = LocalView.current
    if (!view.isInEditMode) {
        DisposableEffect(darkTheme) {
            (view.context as? Activity)?.window?.let { window ->
                window.statusBarColor = STATUS_BAR_COLOR
                window.navigationBarColor = if (darkTheme) NAVIGATION_BAR_DARK_COLOR else NAVIGATION_BAR_LIGHT_COLOR

                WindowCompat.getInsetsController(
                    window,
                    view
                ).isAppearanceLightNavigationBars = !darkTheme
            }
            onDispose {
                // no-op
            }
        }
    }

    CompositionLocalProvider(
        LocalSpace provides Space(),
        LocalRadius provides Radius(),
        LocalElevation provides Elevation(),
        LocalDarkTheme provides darkTheme,
        LocalColorScheme provides colorScheme
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

/**
 * A special theme composable function for components with a static dark theme.
 *
 * This function provides a consistent dark theme ("Dark2") for specific components
 * that do not need to dynamically switch between light and dark modes. It uses
 * the `Dark2ColorsScheme` and provides the same theming elements as the main
 * `HafhashtadTheme` (spacing, radius, elevation).
 *
 * @param content The content to be displayed within the Dark2 theme.
 */
@Composable
fun HafhashtadDark2Theme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalSpace provides Space(),
        LocalRadius provides Radius(),
        LocalElevation provides Elevation(),
        LocalColorScheme provides Dark2ColorsScheme
    ) {
        MaterialTheme(
            colorScheme = Dark2ColorsScheme,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}
