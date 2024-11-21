package com.ehsansetayesh.shared.movies.common.domain.usecase.removebasket

import com.ehsansetayesh.core.common.data.model.error.CustomError
import com.ehsansetayesh.core.common.data.model.result.Result
import com.ehsansetayesh.shared.movies.common.data.repository.MoviesRepository
import javax.inject.Inject
import javax.inject.Singleton

class DefaultRemoveMovieFromBasketUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) : RemoveMovieFromBasketUseCase {
    override suspend fun invoke(movieId: String): Result<CustomError, Unit> {
        return moviesRepository.removeMovieFromBasket(movieId = movieId)
    }
}