package com.ehsansetayesh.navigation

object MoviesDestinations {
    const val MOVIES_LIST_ROUTE = "movies_list"
    const val MOVIE_DETAILS_ROUTE = "movie_details"
    const val MOVIE_DETAILS_FULL_ROUTE = "$MOVIE_DETAILS_ROUTE/{movieId}"

    fun movieDetailsRoute(movieId: String) = "$MOVIE_DETAILS_ROUTE/$movieId"
}