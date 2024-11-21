package com.ehsansetayesh.shared.movies.common.domain.usecase.addbasket

import com.ehsansetayesh.core.common.data.model.error.CustomError
import com.ehsansetayesh.core.common.data.model.result.Result
import com.ehsansetayesh.shared.movies.common.data.repository.MoviesRepository
import com.ehsansetayesh.shared.movies.common.domain.model.Movie
import javax.inject.Inject
import javax.inject.Singleton

class DefaultAddMovieToBasketUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) : AddMovieToBasketUseCase {
    override suspend fun invoke(movie: Movie): Result<CustomError, Unit> {
        return moviesRepository.addMovieToBasket(movie = movie)
    }
}