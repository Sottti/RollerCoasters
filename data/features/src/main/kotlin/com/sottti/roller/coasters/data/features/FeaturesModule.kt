package com.sottti.roller.coasters.data.features

import com.sottti.roller.coasters.domain.features.Features
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object FeaturesModule {

    @Provides
    @Singleton
    fun provideSdkFeatures(): Features = FeaturesImpl()
}
