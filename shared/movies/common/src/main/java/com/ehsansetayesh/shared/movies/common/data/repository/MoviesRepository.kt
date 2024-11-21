package com.ehsansetayesh.shared.movies.common.data.repository

import com.ehsansetayesh.core.common.data.model.error.CustomError
import com.ehsansetayesh.core.common.data.model.result.Result
import com.ehsansetayesh.shared.movies.common.domain.model.Movie
import com.ehsansetayesh.shared.movies.common.domain.model.MovieDetails
import kotlinx.coroutines.flow.StateFlow

interface MoviesRepository {
    suspend fun getMovies(): Result<CustomError, List<Movie>>
    suspend fun getMovieDetails(movieId: String): Result<CustomError, MovieDetails>
    suspend fun addMovieToBasket(movie: Movie): Result<CustomError, Unit>
    suspend fun removeMovieFromBasket(movieId: String): Result<CustomError, Unit>
    suspend fun isMovieInBasket(movieId: String): Result<CustomError, Boolean>
    val basketSize: StateFlow<Int>
}