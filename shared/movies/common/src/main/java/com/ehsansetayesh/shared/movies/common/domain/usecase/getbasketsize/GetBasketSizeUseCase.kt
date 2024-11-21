package com.ehsansetayesh.shared.movies.common.domain.usecase.getbasketsize

import kotlinx.coroutines.flow.StateFlow

interface GetBasketSizeUseCase {
    operator fun invoke(): StateFlow<Int>
}