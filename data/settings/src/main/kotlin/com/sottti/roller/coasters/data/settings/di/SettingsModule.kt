package com.sottti.roller.coasters.data.settings.di

import android.content.Context
import com.sottti.roller.coasters.data.settings.datasource.SettingsLocalDataSource
import com.sottti.roller.coasters.data.settings.datasource.dataStore
import com.sottti.roller.coasters.data.settings.repository.SettingsRepositoryImpl
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import com.sottti.roller.coasters.utils.device.managers.ColorContrastManager
import com.sottti.roller.coasters.utils.device.managers.LocaleManager
import com.sottti.roller.coasters.utils.device.managers.MeasurementSystemManager
import com.sottti.roller.coasters.utils.device.managers.ThemeManager
import com.sottti.roller.coasters.utils.device.sdk.SdkFeatures
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object SettingsModule {

    @Provides
    @Singleton
    fun provideRepository(
        localDataSource: SettingsLocalDataSource,
    ): SettingsRepository = SettingsRepositoryImpl(
        localDataSource = localDataSource,
    )

    @Provides
    @Singleton
    fun provideLocalDataSource(
        @ApplicationContext context: Context,
        colorContrastManager: ColorContrastManager,
        localeManager: LocaleManager,
        measurementSystemManager: MeasurementSystemManager,
        sdkFeatures: SdkFeatures,
        themeManager: ThemeManager,
    ): SettingsLocalDataSource = SettingsLocalDataSource(
        colorContrastManager = colorContrastManager,
        dataStore = context.dataStore,
        localeManager = localeManager,
        measurementSystemManager = measurementSystemManager,
        sdkFeatures = sdkFeatures,
        themeManager = themeManager,
    )
}