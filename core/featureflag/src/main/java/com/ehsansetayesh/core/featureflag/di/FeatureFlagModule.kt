package com.ehsansetayesh.core.featureflag.di

import com.ehsansetayesh.core.featureflag.data.DefaultFirebaseFeatureFlag
import com.ehsansetayesh.core.featureflag.data.DefaultGrowthBookFeatureFlag
import com.ehsansetayesh.core.featureflag.domain.FeatureFlagProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object FeatureFlagModule {
    @Provides
    @Named("GrowthBook")
    fun provideGrowthBookFeatureFlag(): FeatureFlagProvider {
        return DefaultGrowthBookFeatureFlag()
    }

    @Provides
    @Named("Firebase")
    fun provideFirebaseFeatureFlag(): FeatureFlagProvider {
        return DefaultFirebaseFeatureFlag()
    }
}