package com.ehsansetayesh.feature.moviedetails.domain.usecase

import com.ehsansetayesh.core.common.data.model.error.CustomError
import com.ehsansetayesh.core.common.data.model.result.Result
import com.ehsansetayesh.shared.movies.common.data.repository.MoviesRepository
import com.ehsansetayesh.shared.movies.common.domain.model.MovieDetails
import javax.inject.Inject
import javax.inject.Singleton

class DefaultGetMovieDetailsUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) : GetMovieDetailsUseCase {
    override suspend fun invoke(movieId: String): Result<CustomError, MovieDetails> {
        return moviesRepository.getMovieDetails(movieId = movieId)
    }
}