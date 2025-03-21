package com.sottti.roller.coasters.data.settings.di

import android.content.Context
import com.sottti.roller.coasters.data.settings.datasource.SettingsLocalDataSource
import com.sottti.roller.coasters.data.settings.datasource.dataStore
import com.sottti.roller.coasters.data.settings.repository.SettingsRepositoryImpl
import com.sottti.roller.coasters.domain.features.Features
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import com.sottti.roller.coasters.utils.device.managers.LocaleManager
import com.sottti.roller.coasters.utils.device.managers.MeasurementSystemManager
import com.sottti.roller.coasters.utils.device.managers.SystemColorContrastManager
import com.sottti.roller.coasters.utils.device.managers.ThemeManager
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
        systemColorContrastManager: SystemColorContrastManager,
        localeManager: LocaleManager,
        measurementSystemManager: MeasurementSystemManager,
        features: Features,
        themeManager: ThemeManager,
    ): SettingsLocalDataSource = SettingsLocalDataSource(
        systemColorContrastManager = systemColorContrastManager,
        dataStore = context.dataStore,
        localeManager = localeManager,
        measurementSystemManager = measurementSystemManager,
        features = features,
        themeManager = themeManager,
    )
}