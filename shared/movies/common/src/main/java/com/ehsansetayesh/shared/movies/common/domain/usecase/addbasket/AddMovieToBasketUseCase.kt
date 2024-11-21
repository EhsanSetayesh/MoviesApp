package com.ehsansetayesh.shared.movies.common.domain.usecase.addbasket

import com.ehsansetayesh.core.common.data.model.error.CustomError
import com.ehsansetayesh.core.common.data.model.result.Result
import com.ehsansetayesh.shared.movies.common.domain.model.Movie

interface AddMovieToBasketUseCase {
    suspend operator fun invoke(movie: Movie): Result<CustomError, Unit>
}