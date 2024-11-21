package com.ehsansetayesh.feature.moviedetails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ehsansetayesh.core.common.data.model.error.CustomError
import com.ehsansetayesh.core.common.data.model.result.Result
import com.ehsansetayesh.core.common.di.IoDispatcher
import com.ehsansetayesh.feature.moviedetails.domain.usecase.GetMovieDetailsUseCase
import com.ehsansetayesh.shared.movies.common.domain.model.MovieDetails
import com.ehsansetayesh.shared.movies.common.domain.model.toMovie
import com.ehsansetayesh.shared.movies.common.domain.usecase.addbasket.AddMovieToBasketUseCase
import com.ehsansetayesh.shared.movies.common.domain.usecase.getbasketsize.GetBasketSizeUseCase
import com.ehsansetayesh.shared.movies.common.domain.usecase.isinbasket.IsMovieInBasketUseCase
import com.ehsansetayesh.shared.movies.common.domain.usecase.removebasket.RemoveMovieFromBasketUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val addMovieToBasketUseCase: AddMovieToBasketUseCase,
    private val removeMovieFromBasketUseCase: RemoveMovieFromBasketUseCase,
    private val isMovieInBasketUseCase: IsMovieInBasketUseCase,
    private val getBasketSizeUseCase: GetBasketSizeUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow<MovieDetailsUiState>(MovieDetailsUiState.Loading)
    val uiState = _uiState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        MovieDetailsUiState.Loading
    )

    private val _basketCount = MutableStateFlow(0)

    private val _viewEvents = MutableSharedFlow<ViewEvent>()
    val viewEvents = _viewEvents.asSharedFlow()

    private var currentMovieId: String? = null

    val basketCount = getBasketSizeUseCase()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            0
        )

    fun init(movieId: String) {
        if (currentMovieId != movieId) {
            currentMovieId = movieId
            loadMovieDetails(movieId)
        }
    }

    fun handleEvent(event: MovieDetailsUiEvent) {
        when (event) {
            is MovieDetailsUiEvent.BasketAction -> handleBasketAction()
            MovieDetailsUiEvent.RetryClicked -> currentMovieId?.let { loadMovieDetails(it) }
            MovieDetailsUiEvent.NavigateBack -> emitViewEvent(ViewEvent.NavigateBack)
            else -> {
                /*NO OP*/
            }
        }
    }

    private fun handleBasketAction() {
        val currentState = _uiState.value as? MovieDetailsUiState.Success ?: return

        if (currentState.isInBasket) {
            removeFromBasket(currentState)
        } else {
            addToBasket(currentState)
        }

    }



    private fun addToBasket(currentState: MovieDetailsUiState.Success) {
        viewModelScope.launch(ioDispatcher) {
            when (val result = addMovieToBasketUseCase(currentState.movieDetails.toMovie())) {
                is Result.Value -> {
                    updateBasketState(
                        currentState = currentState,
                        isInBasket = true
                    )
                    emitViewEvent(ViewEvent.ShowAddedToBasket)
                }
                is Result.Error -> {
                    emitViewEvent(
                        ViewEvent.ShowError(
                            result.error.message ?: "Failed to add to basket"
                        )
                    )
                }
            }
        }
    }

    private fun removeFromBasket(currentState: MovieDetailsUiState.Success) {
        viewModelScope.launch(ioDispatcher) {
            when (val result = removeMovieFromBasketUseCase(currentState.movieDetails.id)) {
                is Result.Value -> {
                    updateBasketState(
                        currentState = currentState,
                        isInBasket = false
                    )
                    emitViewEvent(ViewEvent.ShowRemovedFromBasket)
                }
                is Result.Error -> {
                    emitViewEvent(
                        ViewEvent.ShowError(
                            result.error.message ?: "Failed to remove from basket"
                        )
                    )
                }
            }
        }
    }

    private fun updateBasketState(
        currentState: MovieDetailsUiState.Success,
        isInBasket: Boolean
    ) {
        _uiState.value = currentState.copy(isInBasket = isInBasket)
    }

    private fun loadMovieDetails(movieId: String) {
        viewModelScope.launch(ioDispatcher) {
            _uiState.value = MovieDetailsUiState.Loading

            when (val detailsResult = getMovieDetailsUseCase(movieId)) {
                is Result.Value -> handleMovieDetailsSuccess(detailsResult.value, movieId)
                is Result.Error -> handleMovieDetailsError(detailsResult.error)
            }
        }
    }

    private suspend fun handleMovieDetailsSuccess(
        movieDetails: MovieDetails,
        movieId: String
    ) {
        when (val basketResult = isMovieInBasketUseCase(movieId)) {
            is Result.Value -> _uiState.value = MovieDetailsUiState.Success(
                movieDetails = movieDetails,
                isInBasket = basketResult.value
            )
            is Result.Error -> {
                _uiState.value = MovieDetailsUiState.Error(
                    message = basketResult.error.message ?: "Unknown error occurred"
                )
                emitViewEvent(
                    ViewEvent.ShowError(
                        basketResult.error.message ?: "Error checking basket status"
                    )
                )
            }
        }
    }

    private fun handleMovieDetailsError(error: CustomError) {
        _uiState.value = MovieDetailsUiState.Error(
            message = error.message ?: "Unknown error occurred"
        )
        emitViewEvent(
            ViewEvent.ShowError(
                error.message ?: "Error loading movie details"
            )
        )
    }

    private fun emitViewEvent(event: ViewEvent) {
        viewModelScope.launch {
            _viewEvents.emit(event)
        }
    }

    sealed interface ViewEvent {
        data object NavigateBack : ViewEvent
        data object ShowAddedToBasket : ViewEvent
        data object ShowRemovedFromBasket : ViewEvent
        data class ShowError(val message: String) : ViewEvent
    }
}

sealed interface MovieDetailsUiState {
    data object Loading : MovieDetailsUiState
    data class Success(
        val movieDetails: MovieDetails,
        val isInBasket: Boolean
    ) : MovieDetailsUiState
    data class Error(
        val message: String
    ) : MovieDetailsUiState
}

sealed interface MovieDetailsUiEvent {
    data object NavigateBack : MovieDetailsUiEvent
    data object BasketAction : MovieDetailsUiEvent
    data object RetryClicked : MovieDetailsUiEvent
}