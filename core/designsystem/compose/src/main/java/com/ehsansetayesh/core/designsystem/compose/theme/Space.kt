package com.ehsansetayesh.core.designsystem.compose.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * TODO: Ensure that these sizes are scalable and consistent with the design system.
 * Define and add different sizes for spacing in the application.
 * This should cover margins, paddings, and other spatial elements.
 */

@Immutable
class Space(
    val zero: Dp = 0.dp,
    val little: Dp = 1.dp,
    val tiny: Dp = 2.dp,
    val xSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val smallish: Dp = 12.dp,
    val medium: Dp = 16.dp,
    val mediumPlus: Dp = 20.dp,
    val xMedium: Dp = 24.dp,
    val mediumLarge: Dp = 28.dp,
    val large: Dp = 32.dp,
    val largePlus: Dp = 40.dp,
    val xLarge: Dp = 48.dp,
    val xxLarge: Dp = 56.dp,
    val xxxLarge: Dp = 64.dp,
    val giant: Dp = 72.dp
)

internal val LocalSpace = staticCompositionLocalOf { Space() }

val MaterialTheme.space: Space
    @Composable
    @ReadOnlyComposable
    get() = LocalSpace.current
