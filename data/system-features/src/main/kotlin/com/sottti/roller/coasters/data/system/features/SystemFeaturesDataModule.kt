package com.sottti.roller.coasters.data.system.features

import com.sottti.roller.coasters.domain.system.features.SystemFeatures
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface SystemFeaturesDataModule {

    @Binds
    @Singleton
    fun bindSystemFeatures(
        impl: SystemFeaturesImpl,
    ): SystemFeatures
}
