package com.ehsansetayesh.feature.movieslist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ehsansetayesh.core.common.data.model.result.Result.*
import com.ehsansetayesh.core.common.di.IoDispatcher
import com.ehsansetayesh.feature.movieslist.domain.usecase.GetBasketButtonVisibilityUseCase
import com.ehsansetayesh.feature.movieslist.domain.usecase.GetMoviesUseCase
import com.ehsansetayesh.shared.movies.common.domain.model.Movie
import com.ehsansetayesh.shared.movies.common.domain.usecase.addbasket.AddMovieToBasketUseCase
import com.ehsansetayesh.shared.movies.common.domain.usecase.getbasketsize.GetBasketSizeUseCase
import com.ehsansetayesh.shared.movies.common.domain.usecase.isinbasket.IsMovieInBasketUseCase
import com.ehsansetayesh.shared.movies.common.domain.usecase.removebasket.RemoveMovieFromBasketUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val addMovieToBasketUseCase: AddMovieToBasketUseCase,
    private val removeMovieFromBasketUseCase: RemoveMovieFromBasketUseCase,
    private val isMovieInBasketUseCase: IsMovieInBasketUseCase,
    private val getBasketSizeUseCase: GetBasketSizeUseCase,
    private val getBasketButtonVisibilityUseCase: GetBasketButtonVisibilityUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow<MoviesListUiState>(MoviesListUiState.Loading)
    val uiState = _uiState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        MoviesListUiState.Loading
    )

    val basketCount = getBasketSizeUseCase()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            0
        )

    private val _viewEvents = MutableSharedFlow<ViewEvent>()

    init {
        loadMovies()
    }

    fun handleEvent(event: MoviesListUiEvent) {
        when (event) {
            is MoviesListUiEvent.MovieClicked -> emitViewEvent(ViewEvent.NavigateToDetail(event.movieId))
            is MoviesListUiEvent.AddToBasket -> addToBasket(event.movie)
            is MoviesListUiEvent.RemoveFromBasket -> removeFromBasket(event.movieId)
            MoviesListUiEvent.RetryClicked -> loadMovies()
            MoviesListUiEvent.RefreshBasketState -> refreshBasketState()
        }
    }

    private fun refreshBasketState() {
        viewModelScope.launch(ioDispatcher) {
            val currentState = _uiState.value
            if (currentState is MoviesListUiState.Success && currentState.movies.isNotEmpty()) {
                val updatedBasketStates = loadBasketStates(currentState.movies)
                _uiState.value = currentState.copy(
                    movieBasketStates = updatedBasketStates
                )
            }
        }
    }

    private fun loadMovies() {
        viewModelScope.launch(ioDispatcher) {
            _uiState.value = MoviesListUiState.Loading

            try {
                // Load movies and feature flag state in parallel
                val (moviesResult, featureFlagResult) = coroutineScope {
                    val movies = async { getMoviesUseCase() }
                    val featureFlag = async { getBasketButtonVisibilityUseCase() }
                    Pair(movies.await(), featureFlag.await())
                }

                // Get basket states for all movies
                when {
                    moviesResult is Error -> {
                        _uiState.value = MoviesListUiState.Error(
                            message = moviesResult.error.message ?: "Failed to load movies"
                        )
                    }
                    featureFlagResult is Error -> {
                        _uiState.value = MoviesListUiState.Error(
                            message = featureFlagResult.error.message ?: "Failed to check feature flags"
                        )
                    }
                    moviesResult is Value && featureFlagResult is Value -> {
                        val basketStates = loadBasketStates(moviesResult.value)
                        _uiState.value = MoviesListUiState.Success(
                            movies = moviesResult.value,
                            isBasketButtonVisible = featureFlagResult.value,
                            movieBasketStates = basketStates
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.value = MoviesListUiState.Error(
                    message = e.message ?: "Unknown error occurred"
                )
            }
        }
    }

    private suspend fun loadBasketStates(movies: List<Movie>): Map<String, Boolean> {
        return coroutineScope {
            movies.map { movie ->
                async {
                    movie.id to when (val result = isMovieInBasketUseCase(movie.id)) {
                        is Value -> result.value
                        is Error -> false
                    }
                }
            }.awaitAll().toMap()
        }
    }

    private fun addToBasket(movie: Movie) {
        viewModelScope.launch(ioDispatcher) {
            when (val result = addMovieToBasketUseCase(movie)) {
                is Value -> updateMovieBasketState(movie.id, true)
                is Error -> emitViewEvent(
                    ViewEvent.ShowError(result.error.message ?: "Failed to add to basket")
                )
            }
        }
    }

    private fun removeFromBasket(movieId: String) {
        viewModelScope.launch(ioDispatcher) {
            when (val result = removeMovieFromBasketUseCase(movieId)) {
                is Value -> updateMovieBasketState(movieId, false)
                is Error -> emitViewEvent(
                    ViewEvent.ShowError(result.error.message ?: "Failed to remove from basket")
                )
            }
        }
    }

    private fun updateMovieBasketState(movieId: String, isInBasket: Boolean) {
        val currentState = _uiState.value as? MoviesListUiState.Success ?: return
        _uiState.value = currentState.copy(
            movieBasketStates = currentState.movieBasketStates + (movieId to isInBasket)
        )
    }

    private fun emitViewEvent(event: ViewEvent) {
        viewModelScope.launch {
            _viewEvents.emit(event)
        }
    }

    sealed interface ViewEvent {
        data class NavigateToDetail(val movieId: String) : ViewEvent
        data class ShowError(val message: String) : ViewEvent
    }
}

sealed interface MoviesListUiState {
    data object Loading : MoviesListUiState
    data class Success(
        val movies: List<Movie>,
        val isBasketButtonVisible: Boolean,
        val movieBasketStates: Map<String, Boolean>
    ) : MoviesListUiState
    data class Error(val message: String) : MoviesListUiState
}

sealed interface MoviesListUiEvent {
    data class MovieClicked(val movieId: String) : MoviesListUiEvent
    data class AddToBasket(val movie: Movie) : MoviesListUiEvent
    data class RemoveFromBasket(val movieId: String) : MoviesListUiEvent
    data object RetryClicked : MoviesListUiEvent
    data object RefreshBasketState : MoviesListUiEvent
}
