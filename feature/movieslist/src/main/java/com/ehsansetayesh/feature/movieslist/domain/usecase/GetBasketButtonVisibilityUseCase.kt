package com.ehsansetayesh.feature.movieslist.domain.usecase

import com.ehsansetayesh.core.common.data.model.error.CustomError
import com.ehsansetayesh.core.common.data.model.result.Result

interface GetBasketButtonVisibilityUseCase {
    suspend operator fun invoke(): Result<CustomError, Boolean>
}