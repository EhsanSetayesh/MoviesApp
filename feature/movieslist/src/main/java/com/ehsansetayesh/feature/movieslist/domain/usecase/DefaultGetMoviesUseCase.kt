package com.ehsansetayesh.feature.movieslist.domain.usecase

import com.ehsansetayesh.core.common.data.model.error.CustomError
import com.ehsansetayesh.core.common.data.model.result.Result
import com.ehsansetayesh.shared.movies.common.data.repository.MoviesRepository
import com.ehsansetayesh.shared.movies.common.domain.model.Movie
import javax.inject.Inject

class DefaultGetMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) : GetMoviesUseCase {
    override suspend fun invoke(): Result<CustomError, List<Movie>> {
        return moviesRepository.getMovies()
    }
}