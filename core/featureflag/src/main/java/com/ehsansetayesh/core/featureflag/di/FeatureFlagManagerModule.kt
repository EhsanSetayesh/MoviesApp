package com.ehsansetayesh.core.featureflag.di

import com.ehsansetayesh.core.featureflag.domain.FeatureFlagManager
import com.ehsansetayesh.core.featureflag.domain.FeatureFlagProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FeatureFlagManagerModule {

    @Provides
    @Singleton
    fun provideFeatureFlagManager(
        @Named("GrowthBook") growthBookFeatureFlag: FeatureFlagProvider,
        @Named("Firebase") firebaseFeatureFlag: FeatureFlagProvider
    ): FeatureFlagManager {
        return FeatureFlagManager(growthBookFeatureFlag, firebaseFeatureFlag)
    }
}