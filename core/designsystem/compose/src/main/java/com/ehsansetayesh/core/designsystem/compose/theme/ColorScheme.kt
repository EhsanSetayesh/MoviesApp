package com.ehsansetayesh.core.designsystem.compose.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.staticCompositionLocalOf

internal val LightColorsScheme = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primary_container,
    onPrimaryContainer = md_theme_light_onPrimary_container,
    inversePrimary = md_theme_light_inverse_primary,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondary_container,
    onSecondaryContainer = md_theme_light_onSecondary_container,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiary_container,
    onTertiaryContainer = md_theme_light_onTertiary_container,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surface_variant,
    onSurfaceVariant = md_theme_light_onSurface_variant,
    surfaceTint = md_theme_light_surface_tint,
    inverseSurface = md_theme_light_inverse_surface,
    inverseOnSurface = md_theme_light_inverse_on_surface,
    error = md_theme_light_error,
    onError = md_theme_light_onError,
    errorContainer = md_theme_light_error_container,
    onErrorContainer = md_theme_light_onError_container,
    outline = md_theme_light_outline,
    outlineVariant = md_theme_light_outline_variant,
    scrim = md_theme_light_scrim,
    surfaceBright = md_theme_light_surface_bright,
    surfaceContainer = md_theme_light_surface_container,
    surfaceContainerHigh = md_theme_light_surface_container_high,
    surfaceContainerHighest = md_theme_light_surface_container_highest,
    surfaceContainerLow = md_theme_light_surface_container_low,
    surfaceContainerLowest = md_theme_light_surface_container_lowest,
    surfaceDim = md_theme_light_surface_dim
)

internal val DarkColorsScheme = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primary_container,
    onPrimaryContainer = md_theme_dark_onPrimary_container,
    inversePrimary = md_theme_dark_inverse_primary,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondary_container,
    onSecondaryContainer = md_theme_dark_onSecondary_container,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiary_container,
    onTertiaryContainer = md_theme_dark_onTertiary_container,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surface_variant,
    onSurfaceVariant = md_theme_dark_onSurface_variant,
    surfaceTint = md_theme_dark_surface_tint,
    inverseSurface = md_theme_dark_inverse_surface,
    inverseOnSurface = md_theme_dark_inverse_on_surface,
    error = md_theme_dark_error,
    onError = md_theme_dark_onError,
    errorContainer = md_theme_dark_error_container,
    onErrorContainer = md_theme_dark_onError_container,
    outline = md_theme_dark_outline,
    outlineVariant = md_theme_dark_outline_variant,
    scrim = md_theme_dark_scrim,
    surfaceBright = md_theme_dark_surface_bright,
    surfaceContainer = md_theme_dark_surface_container,
    surfaceContainerHigh = md_theme_dark_surface_container_high,
    surfaceContainerHighest = md_theme_dark_surface_container_highest,
    surfaceContainerLow = md_theme_dark_surface_container_low,
    surfaceContainerLowest = md_theme_dark_surface_container_lowest,
    surfaceDim = md_theme_dark_surface_dim
)

internal val Dark2ColorsScheme = darkColorScheme(
    primary = md_theme_dark2_primary,
    onPrimary = md_theme_dark2_onPrimary,
    primaryContainer = md_theme_dark2_primary_container,
    onPrimaryContainer = md_theme_dark2_onPrimary_container,
    inversePrimary = md_theme_dark2_inverse_primary,
    secondary = md_theme_dark2_secondary,
    onSecondary = md_theme_dark2_onSecondary,
    secondaryContainer = md_theme_dark2_secondary_container,
    onSecondaryContainer = md_theme_dark2_onSecondary_container,
    tertiary = md_theme_dark2_tertiary,
    onTertiary = md_theme_dark2_onTertiary,
    tertiaryContainer = md_theme_dark2_tertiary_container,
    onTertiaryContainer = md_theme_dark2_onTertiary_container,
    background = md_theme_dark2_background,
    onBackground = md_theme_dark2_onBackground,
    surface = md_theme_dark2_surface,
    onSurface = md_theme_dark2_onSurface,
    surfaceVariant = md_theme_dark2_surface_variant,
    onSurfaceVariant = md_theme_dark2_onSurface_variant,
    surfaceTint = md_theme_dark2_surface_tint,
    inverseSurface = md_theme_dark2_inverse_surface,
    inverseOnSurface = md_theme_dark2_inverse_on_surface,
    error = md_theme_dark2_error,
    onError = md_theme_dark2_onError,
    errorContainer = md_theme_dark2_error_container,
    onErrorContainer = md_theme_dark2_onError_container,
    outline = md_theme_dark2_outline,
    outlineVariant = md_theme_dark2_outline_variant,
    scrim = md_theme_dark2_scrim,
    surfaceBright = md_theme_dark2_surface_bright,
    surfaceContainer = md_theme_dark2_surface_container,
    surfaceContainerHigh = md_theme_dark2_surface_container_high,
    surfaceContainerHighest = md_theme_dark2_surface_container_highest,
    surfaceContainerLow = md_theme_dark2_surface_container_low,
    surfaceContainerLowest = md_theme_dark2_surface_container_lowest,
    surfaceDim = md_theme_dark2_surface_dim
)

/**
 * CompositionLocal used to pass [ColorScheme] down the tree.
 *
 * Setting the value here is typically done as part of [MaterialTheme], which will automatically
 * handle efficiently updating any changed colors without causing unnecessary recompositions, using
 * [ColorScheme.updateColorSchemeFrom]. To retrieve the current value of this CompositionLocal, use
 * [MaterialTheme.colorScheme].
 */
internal val LocalColorScheme = staticCompositionLocalOf { LightColorsScheme }
