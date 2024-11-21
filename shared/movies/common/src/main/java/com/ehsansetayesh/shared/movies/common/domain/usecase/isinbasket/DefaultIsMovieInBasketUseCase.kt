package com.ehsansetayesh.shared.movies.common.domain.usecase.isinbasket

import com.ehsansetayesh.core.common.data.model.error.CustomError
import com.ehsansetayesh.core.common.data.model.result.Result
import com.ehsansetayesh.shared.movies.common.data.repository.MoviesRepository
import javax.inject.Inject

class DefaultIsMovieInBasketUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) : IsMovieInBasketUseCase {
    override suspend fun invoke(movieId: String): Result<CustomError, Boolean> {
        return moviesRepository.isMovieInBasket(movieId = movieId)
    }
}