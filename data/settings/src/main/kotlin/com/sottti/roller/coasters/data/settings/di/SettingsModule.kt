package com.sottti.roller.coasters.data.settings.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.sottti.roller.coasters.data.settings.datasource.SettingsLocalDataSource
import com.sottti.roller.coasters.data.settings.datasource.SettingsLocalDataSource.Companion.DATA_STORE_NAME
import com.sottti.roller.coasters.data.settings.helpers.UiModeManager
import com.sottti.roller.coasters.data.settings.repository.SettingsRepository
import com.sottti.roller.coasters.data.settings.repository.SettingsRepositoryImpl
import com.sottti.roller.coasters.utils.device.sdk.SdkFeatures
import com.sottti.roller.coasters.utils.device.system.SystemSettings
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
        systemSettings: SystemSettings,
        uiModeManager: UiModeManager,
    ): SettingsRepository = SettingsRepositoryImpl(
        localDataSource = localDataSource,
        systemSettings = systemSettings,
        uiModeManager = uiModeManager,
    )

    @Provides
    @Singleton
    fun provideLocalDataSource(
        @ApplicationContext context: Context,
        sdkFeatures: SdkFeatures,
    ): SettingsLocalDataSource = SettingsLocalDataSource(
        dataStore = context.dataStore,
        sdkFeatures = sdkFeatures,
    )
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DATA_STORE_NAME)