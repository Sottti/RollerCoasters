package com.sottti.roller.coasters.data.features

import com.sottti.roller.coasters.domain.features.Features
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface FeaturesModule {

    @Binds
    @Singleton
    fun bindSdkFeatures(
        impl: FeaturesImpl,
    ): Features
}
