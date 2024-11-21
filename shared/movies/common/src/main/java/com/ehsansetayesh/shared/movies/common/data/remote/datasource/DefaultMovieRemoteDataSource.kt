package com.ehsansetayesh.shared.movies.common.data.remote.datasource

import com.ehsansetayesh.core.common.data.model.result.NetworkResult
import com.ehsansetayesh.shared.movies.common.data.remote.api.MovieApi
import com.ehsansetayesh.shared.movies.common.data.remote.model.response.MovieDTO
import com.ehsansetayesh.shared.movies.common.data.remote.model.response.MovieDetailsDTO
import com.ehsansetayesh.shared.movies.common.data.remote.model.response.MoviesResponseDTO
import javax.inject.Inject

class DefaultMovieRemoteDataSource @Inject constructor(
    private val movieApi: MovieApi
) : MovieRemoteDataSource {
    override suspend fun getMovies(): NetworkResult<MoviesResponseDTO> =
        movieApi.getMovies()

    override suspend fun getMovieDetails(movieId: String): NetworkResult<MovieDetailsDTO> =
        movieApi.getMovieDetails(movieId = movieId)
}