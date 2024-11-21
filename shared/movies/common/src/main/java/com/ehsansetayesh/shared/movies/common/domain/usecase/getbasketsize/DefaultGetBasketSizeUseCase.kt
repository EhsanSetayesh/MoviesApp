package com.ehsansetayesh.shared.movies.common.domain.usecase.getbasketsize

import com.ehsansetayesh.shared.movies.common.data.repository.MoviesRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

class DefaultGetBasketSizeUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) : GetBasketSizeUseCase {
    override operator fun invoke(): StateFlow<Int> = moviesRepository.basketSize
}