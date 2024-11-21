package com.ehsansetayesh.shared.movies.common.domain.usecase.removebasket

import com.ehsansetayesh.core.common.data.model.error.CustomError
import com.ehsansetayesh.core.common.data.model.result.Result

interface RemoveMovieFromBasketUseCase {
    suspend operator fun invoke(movieId: String): Result<CustomError, Unit>
}