package com.ehsansetayesh.shared.movies.common.data.remote.api

import com.ehsansetayesh.core.common.data.model.result.NetworkResult
import com.ehsansetayesh.shared.movies.common.data.remote.model.response.MovieDTO
import com.ehsansetayesh.shared.movies.common.data.remote.model.response.MovieDetailsDTO
import com.ehsansetayesh.shared.movies.common.data.remote.model.response.MoviesResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movies")
    suspend fun getMovies(@Query("page") page: Int = 1): NetworkResult<MoviesResponseDTO>

    @GET("movies/{id}")
    suspend fun getMovieDetails(@Path("id") movieId: String): NetworkResult<MovieDetailsDTO>
}