package com.ehsansetayesh.feature.movieslist.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.ehsansetayesh.core.designsystem.compose.components.ErrorScreen
import com.ehsansetayesh.core.designsystem.compose.components.LoadingScreen
import com.ehsansetayesh.shared.movies.common.domain.model.Movie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesListScreen(
    onNavigateToDetails: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MoviesListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val basketCount by viewModel.basketCount.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.handleEvent(MoviesListUiEvent.RefreshBasketState)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Movies") },
                actions = {
                    Text(
                        text = "Basket: $basketCount",
                        modifier = Modifier.padding(end = 16.dp)
                    )
                }
            )
        }
    ) { paddingValues ->
        when (val state = uiState) {
            is MoviesListUiState.Loading -> {
                LoadingScreen(
                    modifier = Modifier.padding(paddingValues)
                )
            }
            is MoviesListUiState.Success -> {
                MoviesList(
                    movies = state.movies,
                    isBasketButtonVisible = state.isBasketButtonVisible,
                    movieBasketStates = state.movieBasketStates,
                    onMovieClick = onNavigateToDetails,
                    onBasketClick = { movie, isInBasket ->
                        if (isInBasket) {
                            viewModel.handleEvent(MoviesListUiEvent.RemoveFromBasket(movie.id))
                        } else {
                            viewModel.handleEvent(MoviesListUiEvent.AddToBasket(movie))
                        }
                    },
                    modifier = Modifier.padding(paddingValues)
                )
            }
            is MoviesListUiState.Error -> {
                ErrorScreen(
                    message = state.message,
                    onRetryClick = {
                        viewModel.handleEvent(MoviesListUiEvent.RetryClicked)
                    },
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}

@Composable
private fun MoviesList(
    movies: List<Movie>,
    isBasketButtonVisible: Boolean,
    movieBasketStates: Map<String, Boolean>,
    onMovieClick: (String) -> Unit,
    onBasketClick: (Movie, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = movies,
            key = { it.id }
        ) { movie ->
            MovieItem(
                movie = movie,
                isBasketButtonVisible = isBasketButtonVisible,
                initialIsInBasket = movieBasketStates[movie.id] ?: false,
                onMovieClick = { onMovieClick(movie.id) },
                onBasketClick = { isInBasket -> onBasketClick(movie, isInBasket) }
            )
        }
    }
}

@Composable
private fun MovieItem(
    movie: Movie,
    isBasketButtonVisible: Boolean,
    initialIsInBasket: Boolean,
    onMovieClick: () -> Unit,
    onBasketClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    var isInBasket by remember(movie.id) { mutableStateOf(initialIsInBasket) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onMovieClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = movie.poster,
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = movie.year,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "IMDb: ${movie.imdbRating}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            if (isBasketButtonVisible) {
                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        onBasketClick(isInBasket)
                        isInBasket = !isInBasket
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = if (isInBasket) "Remove from Basket"
                        else "Add to Basket"
                    )
                }
            }
        }
    }
}