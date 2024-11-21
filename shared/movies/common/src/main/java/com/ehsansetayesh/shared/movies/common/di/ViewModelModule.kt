package com.ehsansetayesh.shared.movies.common.di

import com.ehsansetayesh.shared.movies.common.domain.usecase.addbasket.AddMovieToBasketUseCase
import com.ehsansetayesh.shared.movies.common.domain.usecase.addbasket.DefaultAddMovieToBasketUseCase
import com.ehsansetayesh.shared.movies.common.domain.usecase.getbasketsize.DefaultGetBasketSizeUseCase
import com.ehsansetayesh.shared.movies.common.domain.usecase.getbasketsize.GetBasketSizeUseCase
import com.ehsansetayesh.shared.movies.common.domain.usecase.isinbasket.DefaultIsMovieInBasketUseCase
import com.ehsansetayesh.shared.movies.common.domain.usecase.isinbasket.IsMovieInBasketUseCase
import com.ehsansetayesh.shared.movies.common.domain.usecase.removebasket.DefaultRemoveMovieFromBasketUseCase
import com.ehsansetayesh.shared.movies.common.domain.usecase.removebasket.RemoveMovieFromBasketUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ViewModelModule {

    @Binds
    abstract fun bindsAddMovieToBasketUseCase(
        implementation: DefaultAddMovieToBasketUseCase
    ): AddMovieToBasketUseCase

    @Binds
    abstract fun bindsRemoveMovieFromBasketUseCase(
        implementation: DefaultRemoveMovieFromBasketUseCase
    ): RemoveMovieFromBasketUseCase

    @Binds
    abstract fun bindsIsMovieInBasketUseCase(
        implementation: DefaultIsMovieInBasketUseCase
    ): IsMovieInBasketUseCase

    @Binds
    abstract fun bindGetBasketSizeUseCase(
        defaultGetBasketSizeUseCase: DefaultGetBasketSizeUseCase
    ): GetBasketSizeUseCase
}
