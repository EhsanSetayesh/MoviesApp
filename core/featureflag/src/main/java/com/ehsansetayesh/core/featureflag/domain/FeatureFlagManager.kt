package com.ehsansetayesh.core.featureflag.domain

import com.ehsansetayesh.core.common.data.model.error.CustomError
import com.ehsansetayesh.core.common.data.model.result.Result
import javax.inject.Inject

class FeatureFlagManager @Inject constructor(
    private val growthBookFeatureFlag: FeatureFlagProvider,
    private val firebaseFeatureFlag: FeatureFlagProvider
) {
    /**
     * Checks if a feature is enabled based on the priority rules
     * GrowthBook has priority over Firebase
     */
    fun isFeatureEnabled(key: String): Result<CustomError, Boolean> {
        val growthBookValue = growthBookFeatureFlag.getValue(key)
        return if (!growthBookValue) {
            Result.build {
                false
            }
        } else {
            Result.build {
                firebaseFeatureFlag.getValue(key)
            }
        }
    }
}
