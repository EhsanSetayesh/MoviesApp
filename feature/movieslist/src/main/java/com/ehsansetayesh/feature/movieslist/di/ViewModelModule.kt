package com.ehsansetayesh.feature.movieslist.di

import com.ehsansetayesh.feature.movieslist.domain.usecase.DefaultGetBasketButtonVisibilityUseCase
import com.ehsansetayesh.feature.movieslist.domain.usecase.DefaultGetMoviesUseCase
import com.ehsansetayesh.feature.movieslist.domain.usecase.GetBasketButtonVisibilityUseCase
import com.ehsansetayesh.feature.movieslist.domain.usecase.GetMoviesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ViewModelModule {
    @Binds
    fun bindsGetMoviesUseCase(
        implementation: DefaultGetMoviesUseCase
    ): GetMoviesUseCase

    @Binds
    fun bindsGetBasketButtonVisibilityUseCase(
        implementation: DefaultGetBasketButtonVisibilityUseCase
    ): GetBasketButtonVisibilityUseCase
}
