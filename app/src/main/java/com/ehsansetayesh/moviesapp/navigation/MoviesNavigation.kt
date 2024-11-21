package com.ehsansetayesh.moviesapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ehsansetayesh.feature.movieslist.presentation.MoviesListScreen
import com.ehsansetayesh.feature.movieslist.presentation.MoviesListViewModel
import com.ehsansetayesh.navigation.MovieDetailsNavigationProvider
import com.ehsansetayesh.navigation.MoviesDestinations

@Composable
fun MoviesNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    movieDetailsNavigationProvider: MovieDetailsNavigationProvider
) {
    NavHost(
        navController = navController,
        startDestination = MoviesDestinations.MOVIES_LIST_ROUTE,
        modifier = modifier
    ) {
        composable(route = MoviesDestinations.MOVIES_LIST_ROUTE) {
            val viewModel: MoviesListViewModel = hiltViewModel()

            MoviesListScreen(
                onNavigateToDetails = { movieId ->
                    navController.navigate(MoviesDestinations.movieDetailsRoute(movieId))
                },
                viewModel = viewModel
            )
        }

        composable(
            route = MoviesDestinations.MOVIE_DETAILS_FULL_ROUTE,
            arguments = listOf(
                navArgument("movieId") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { backStackEntry ->
            val movieId = requireNotNull(backStackEntry.arguments?.getString("movieId")) {
                "movieId parameter wasn't found. Please make sure it's set!"
            }

            movieDetailsNavigationProvider.movieDetailsScreen(
                movieId = movieId,
                onNavigateBack = {
                    navController.popBackStack()
                }
            ).invoke()
        }
    }
}