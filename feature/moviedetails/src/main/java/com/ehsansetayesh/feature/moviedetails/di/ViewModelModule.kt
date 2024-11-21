package com.ehsansetayesh.feature.moviedetails.di

import com.ehsansetayesh.feature.moviedetails.domain.usecase.DefaultGetMovieDetailsUseCase
import com.ehsansetayesh.feature.moviedetails.domain.usecase.GetMovieDetailsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ViewModelModule {
    @Binds
    fun bindsGetMovieDetailsUseCase(
        implementation: DefaultGetMovieDetailsUseCase
    ): GetMovieDetailsUseCase
}
