package com.ehsansetayesh.feature.moviedetails.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.ehsansetayesh.feature.moviedetails.presentation.MovieDetailsViewModel.ViewEvent
import com.ehsansetayesh.shared.movies.common.domain.model.MovieDetails

@Composable
fun MovieDetailsScreen(
    movieId: String,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MovieDetailsViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val basketCount by viewModel.basketCount.collectAsStateWithLifecycle()

    // Handle one-time events (snackbars, navigation)
    LaunchedEffect(Unit) {
        viewModel.viewEvents.collect { event ->
            when (event) {
                ViewEvent.NavigateBack -> onNavigateBack()
                ViewEvent.ShowAddedToBasket -> {
                    // Show snackbar or toast
                }
                ViewEvent.ShowRemovedFromBasket -> {
                    // Show snackbar or toast
                }
                is ViewEvent.ShowError -> {
                    // Show error message
                }

                else -> {
                    /*NO OP*/
                }
            }
        }
    }

    // Initialize viewModel with movieId
    LaunchedEffect(movieId) {
        viewModel.init(movieId)
    }

    MovieDetailsContent(
        uiState = uiState,
        basketCount = basketCount,
        onBackClick = { viewModel.handleEvent(MovieDetailsUiEvent.NavigateBack) },
        onRetryClick = { viewModel.handleEvent(MovieDetailsUiEvent.RetryClicked) },
        onBasketClick = { viewModel.handleEvent(MovieDetailsUiEvent.BasketAction) },
        modifier = modifier
    )
}

@Composable
private fun MovieDetailsContent(
    uiState: MovieDetailsUiState,
    basketCount: Int,
    onBackClick: () -> Unit,
    onRetryClick: () -> Unit,
    onBasketClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            MovieDetailsTopBar(
                onBackClick = onBackClick,
                basketCount = basketCount
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (uiState) {
                is MovieDetailsUiState.Loading -> LoadingContent()
                is MovieDetailsUiState.Success -> SuccessContent(
                    movieDetails = uiState.movieDetails,
                    isInBasket = uiState.isInBasket,
                    onBasketClick = onBasketClick
                )
                is MovieDetailsUiState.Error -> ErrorContent(
                    message = uiState.message,
                    onRetryClick = onRetryClick
                )

                else -> {
                    /*NO OP*/
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MovieDetailsTopBar(
    onBackClick: () -> Unit,
    basketCount: Int
) {
    TopAppBar(
        title = { Text("Movie Details") },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Navigate back"
                )
            }
        },
        actions = {
            Text(
                text = "Basket: $basketCount",
                modifier = Modifier.padding(end = 16.dp)
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
private fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorContent(
    message: String,
    onRetryClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onRetryClick) {
            Text("Retry")
        }
    }
}

@Composable
private fun SuccessContent(
    movieDetails: MovieDetails,
    isInBasket: Boolean,
    onBasketClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Movie Poster and Header
        MovieHeader(movieDetails)

        // Movie Info
        MovieInfo(movieDetails)

        // Basket Button
        BasketButton(
            isInBasket = isInBasket,
            onBasketClick = onBasketClick,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
private fun MovieHeader(movieDetails: MovieDetails) {
    Box(modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            model = movieDetails.poster,
            contentDescription = movieDetails.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.7f)
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Text(
                text = movieDetails.title,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "${movieDetails.year} • ${movieDetails.runtime}",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White.copy(alpha = 0.8f)
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MovieInfo(movieDetails: MovieDetails) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        // Genres
        MovieInfoSection(title = "Genres") {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                movieDetails.genres.forEach { genre ->
                    GenreChip(genre)
                }
            }
        }

        // Plot
        MovieInfoSection(title = "Plot") {
            Text(
                text = movieDetails.plot,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        // Cast
        MovieInfoSection(title = "Cast") {
            Text(
                text = movieDetails.actors.joinToString(),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        // Director
        MovieInfoSection(title = "Director") {
            Text(
                text = movieDetails.director,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        // Additional Info
        MovieInfoRow("IMDb Rating", "${movieDetails.imdbRating}/10")
        MovieInfoRow("Metascore", movieDetails.metascore.toString())
        MovieInfoRow("Released", movieDetails.released)
        MovieInfoRow("Country", movieDetails.country)

        if (movieDetails.awards.isNotBlank()) {
            MovieInfoSection(title = "Awards") {
                Text(
                    text = movieDetails.awards,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
private fun MovieInfoSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        content()
    }
}

@Composable
private fun MovieInfoRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GenreChip(genre: String) {
    ElevatedAssistChip(
        onClick = { },
        label = { Text(genre) }
    )
}

@Composable
private fun BasketButton(
    isInBasket: Boolean,
    onBasketClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onBasketClick,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = if (isInBasket) "Remove from Basket" else "Add to Basket"
        )
    }
}