package com.ehsansetayesh.shared.movies.common.data.remote.datasource

import com.ehsansetayesh.core.common.data.model.result.NetworkResult
import com.ehsansetayesh.shared.movies.common.data.remote.model.response.MovieDTO
import com.ehsansetayesh.shared.movies.common.data.remote.model.response.MovieDetailsDTO
import com.ehsansetayesh.shared.movies.common.data.remote.model.response.MoviesResponseDTO

interface MovieRemoteDataSource {
    suspend fun getMovies(): NetworkResult<MoviesResponseDTO>
    suspend fun getMovieDetails(movieId: String): NetworkResult<MovieDetailsDTO>
}