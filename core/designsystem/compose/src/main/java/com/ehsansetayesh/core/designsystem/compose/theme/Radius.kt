package com.ehsansetayesh.core.designsystem.compose.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// TODO: Ensure that Radius is consistent with the Design System

@Immutable
class Radius(
    val small: Dp = 4.dp,
    val medium: Dp = 8.dp,
    val xMedium: Dp = 12.dp,
    val large: Dp = 16.dp,
    val xLarge: Dp = 20.dp
)

internal val LocalRadius = staticCompositionLocalOf { Radius() }

val MaterialTheme.radius: Radius
    @Composable
    @ReadOnlyComposable
    get() = LocalRadius.current