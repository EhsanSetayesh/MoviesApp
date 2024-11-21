package com.ehsansetayesh.feature.moviedetails.di

import com.ehsansetayesh.feature.moviedetails.navigation.DefaultMovieDetailsNavigationProvider
import com.ehsansetayesh.navigation.MovieDetailsNavigationProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationModule {
    @Binds
    abstract fun bindMovieDetailsNavigationProvider(
        impl: DefaultMovieDetailsNavigationProvider
    ): MovieDetailsNavigationProvider
}