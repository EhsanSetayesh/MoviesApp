package com.ehsansetayesh.shared.movies.common.di

import com.ehsansetayesh.shared.movies.common.data.remote.datasource.DefaultMovieRemoteDataSource
import com.ehsansetayesh.shared.movies.common.data.remote.datasource.MovieRemoteDataSource
import com.ehsansetayesh.shared.movies.common.data.repository.DefaultMoviesRepository
import com.ehsansetayesh.shared.movies.common.data.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMoviesRepository(
        implementation: DefaultMoviesRepository
    ): MoviesRepository

    @Binds
    abstract fun bindMovieRemoteDataSource(
        implementation: DefaultMovieRemoteDataSource
    ): MovieRemoteDataSource
}
