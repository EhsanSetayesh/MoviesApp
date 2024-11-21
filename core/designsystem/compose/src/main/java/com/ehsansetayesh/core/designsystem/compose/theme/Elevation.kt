package com.ehsansetayesh.core.designsystem.compose.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
class Elevation(
    val tiny: Dp = 1.dp,
    val small: Dp = 3.dp,
    val medium: Dp = 6.dp,
    val large: Dp = 8.dp,
    val xLarge: Dp = 12.dp,
    val categoryIcon: Dp = 32.dp // TODO: Update this with design system
)

internal val LocalElevation = staticCompositionLocalOf { Elevation() }

val MaterialTheme.elevation: Elevation
    @Composable
    @ReadOnlyComposable
    get() = LocalElevation.current
