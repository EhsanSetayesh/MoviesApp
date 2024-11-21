package com.ehsansetayesh.shared.movies.common.domain.usecase.isinbasket

import com.ehsansetayesh.core.common.data.model.error.CustomError
import com.ehsansetayesh.core.common.data.model.result.Result

interface IsMovieInBasketUseCase {
    suspend operator fun invoke(movieId: String): Result<CustomError, Boolean>
}