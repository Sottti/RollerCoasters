package com.sottti.roller.coasters.data.settings.di

import android.app.UiModeManager
import android.content.Context
import com.sottti.roller.coasters.data.settings.datasource.ActivityLifecycleEmitter
import com.sottti.roller.coasters.data.settings.datasource.SettingsLocalDataSource
import com.sottti.roller.coasters.data.settings.datasource.dataStore
import com.sottti.roller.coasters.data.settings.managers.LocaleManager
import com.sottti.roller.coasters.data.settings.managers.MeasurementSystemManager
import com.sottti.roller.coasters.data.settings.managers.SystemColorContrastManager
import com.sottti.roller.coasters.data.settings.managers.ThemeManager
import com.sottti.roller.coasters.data.settings.repository.SettingsRepositoryImpl
import com.sottti.roller.coasters.domain.features.Features
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
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
    fun provideUiModeManager(
        @ApplicationContext context: Context,
    ): UiModeManager? = context.getSystemService(UiModeManager::class.java)

    @Provides
    @Singleton
    fun provideLocalDataSource(
        @ApplicationContext context: Context,
        activityLifecycleEmitter: ActivityLifecycleEmitter,
        features: Features,
        localeManager: LocaleManager,
        measurementSystemManager: MeasurementSystemManager,
        systemColorContrastManager: SystemColorContrastManager,
        themeManager: ThemeManager,
    ): SettingsLocalDataSource = SettingsLocalDataSource(
        activityLifecycleEmitter = activityLifecycleEmitter,
        dataStore = context.dataStore,
        features = features,
        localeManager = localeManager,
        measurementSystemManager = measurementSystemManager,
        systemColorContrastManager = systemColorContrastManager,
        themeManager = themeManager,
    )
}