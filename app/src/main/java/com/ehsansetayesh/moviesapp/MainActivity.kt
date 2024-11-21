package com.ehsansetayesh.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ehsansetayesh.moviesapp.navigation.MoviesNavigation
import com.ehsansetayesh.moviesapp.ui.theme.MoviesAppTheme
import com.ehsansetayesh.navigation.MovieDetailsNavigationProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var movieDetailsNavigationProvider: MovieDetailsNavigationProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoviesAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MoviesNavigation(
                        movieDetailsNavigationProvider = movieDetailsNavigationProvider
                    )
                }
            }
        }
    }
}