package com.ehsansetayesh.feature.moviedetails.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ehsansetayesh.feature.moviedetails.presentation.MovieDetailsScreen
import com.ehsansetayesh.feature.moviedetails.presentation.MovieDetailsViewModel
import com.ehsansetayesh.navigation.MovieDetailsNavigationProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultMovieDetailsNavigationProvider @Inject constructor() : MovieDetailsNavigationProvider {
    override fun movieDetailsScreen(
        movieId: String,
        onNavigateBack: () -> Unit
    ): @Composable () -> Unit = {
        val viewModel: MovieDetailsViewModel = hiltViewModel()

        MovieDetailsScreen(
            movieId = movieId,
            onNavigateBack = onNavigateBack,
            viewModel = viewModel
        )
    }
}
