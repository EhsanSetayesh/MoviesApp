package com.ehsansetayesh.navigation

import androidx.compose.runtime.Composable

interface MovieDetailsNavigationProvider {
    fun movieDetailsScreen(
        movieId: String,
        onNavigateBack: () -> Unit
    ): @Composable () -> Unit
}