package com.ehsansetayesh.core.featureflag.domain

interface FeatureFlagProvider {
    /**
     * Returns the value of a feature flag
     * @param key The feature flag key
     * @return Boolean value indicating if the feature is enabled
     */
    fun getValue(key: String): Boolean
}