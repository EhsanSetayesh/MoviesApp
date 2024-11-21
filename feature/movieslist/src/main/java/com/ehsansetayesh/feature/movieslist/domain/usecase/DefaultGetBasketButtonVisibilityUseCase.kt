package com.ehsansetayesh.feature.movieslist.domain.usecase

import com.ehsansetayesh.core.common.base.Constants.ADD_TO_BASKET_KEY
import com.ehsansetayesh.core.common.data.model.error.CustomError
import com.ehsansetayesh.core.common.data.model.result.Result
import com.ehsansetayesh.core.featureflag.domain.FeatureFlagManager
import javax.inject.Inject

class DefaultGetBasketButtonVisibilityUseCase @Inject constructor(
    private val featureFlagManager: FeatureFlagManager
) : GetBasketButtonVisibilityUseCase {
    override suspend fun invoke(): Result<CustomError, Boolean> {
        return featureFlagManager.isFeatureEnabled(ADD_TO_BASKET_KEY)
    }
}