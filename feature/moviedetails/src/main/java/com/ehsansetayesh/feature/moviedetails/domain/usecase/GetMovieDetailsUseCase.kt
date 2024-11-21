package com.ehsansetayesh.feature.moviedetails.domain.usecase

import com.ehsansetayesh.core.common.data.model.error.CustomError
import com.ehsansetayesh.core.common.data.model.result.Result
import com.ehsansetayesh.shared.movies.common.domain.model.Movie
import com.ehsansetayesh.shared.movies.common.domain.model.MovieDetails

interface GetMovieDetailsUseCase {
    suspend operator fun invoke(movieId: String): Result<CustomError, MovieDetails>
}