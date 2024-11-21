package com.ehsansetayesh.shared.movies.common.data.repository

import com.ehsansetayesh.core.common.data.model.error.CustomError
import com.ehsansetayesh.core.common.data.model.error.CustomException
import com.ehsansetayesh.core.common.data.model.result.NetworkResult
import com.ehsansetayesh.core.common.data.model.result.Result
import com.ehsansetayesh.shared.movies.common.data.remote.datasource.MovieRemoteDataSource
import com.ehsansetayesh.shared.movies.common.data.remote.model.response.toDomainModel
import com.ehsansetayesh.shared.movies.common.domain.model.Movie
import com.ehsansetayesh.shared.movies.common.domain.model.MovieDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultMoviesRepository @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MoviesRepository {

    // Mutex for thread-safe basket operations
    private val mutex = Mutex()

    // In-memory storage for basket items
    private val basketMovies = mutableSetOf<Movie>()
    private val _basketSize = MutableStateFlow(0)
    override val basketSize = _basketSize.asStateFlow()

    override suspend fun getMovies(): Result<CustomError, List<Movie>> = Result.build {
        when (val response = movieRemoteDataSource.getMovies()) {
            is NetworkResult.Success -> response.data.toDomainModel()
            is NetworkResult.Error -> throw CustomException(
                code = response.code,
                message = response.message
            )
            is NetworkResult.Exception -> throw response.e
        }
    }

    override suspend fun getMovieDetails(movieId: String): Result<CustomError, MovieDetails> =
        Result.build {
            when (val response = movieRemoteDataSource.getMovieDetails(movieId)) {
                is NetworkResult.Success -> response.data.toDomainModel()
                is NetworkResult.Error -> throw CustomException(
                    code = response.code,
                    message = response.message
                )
                is NetworkResult.Exception -> throw response.e
            }
        }

    override suspend fun addMovieToBasket(movie: Movie): Result<CustomError, Unit> = Result.build {
        mutex.withLock {
            basketMovies.add(movie)
            updateBasketSize()
        }
    }

    override suspend fun removeMovieFromBasket(movieId: String): Result<CustomError, Unit> = Result.build {
        mutex.withLock {
            basketMovies.removeAll { it.id == movieId }
            updateBasketSize()
        }
    }

    override suspend fun isMovieInBasket(movieId: String): Result<CustomError, Boolean> = Result.build {
        mutex.withLock {
            basketMovies.any { it.id == movieId }
        }
    }

    private fun updateBasketSize() {
        _basketSize.value = basketMovies.size
    }
}