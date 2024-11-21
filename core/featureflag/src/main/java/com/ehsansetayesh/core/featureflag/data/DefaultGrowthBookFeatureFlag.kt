package com.ehsansetayesh.core.featureflag.data

import com.ehsansetayesh.core.common.base.Constants.ADD_TO_BASKET_KEY
import com.ehsansetayesh.core.featureflag.domain.FeatureFlagProvider
import javax.inject.Inject

class DefaultGrowthBookFeatureFlag @Inject constructor() : FeatureFlagProvider {
    private val ADD_TO_BASKET_FLAG = true

    override fun getValue(key: String): Boolean =
        if (key == ADD_TO_BASKET_KEY) ADD_TO_BASKET_FLAG else false
}