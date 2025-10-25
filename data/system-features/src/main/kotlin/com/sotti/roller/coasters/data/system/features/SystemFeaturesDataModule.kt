package com.sotti.roller.coasters.data.system.features

import com.sotti.roller.coasters.domain.system.features.SystemFeatures
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal fun interface SystemFeaturesDataModule {

    @Binds
    @Singleton
    fun bindSdkSystemFeatures(
        impl: SystemFeaturesImpl,
    ): SystemFeatures
}
