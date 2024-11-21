package com.ehsansetayesh.feature.movieslist.domain.usecase

import com.ehsansetayesh.core.common.data.model.error.CustomError
import com.ehsansetayesh.core.common.data.model.result.Result
import com.ehsansetayesh.shared.movies.common.domain.model.Movie

interface GetMoviesUseCase {
    suspend operator fun invoke(): Result<CustomError, List<Movie>>
}