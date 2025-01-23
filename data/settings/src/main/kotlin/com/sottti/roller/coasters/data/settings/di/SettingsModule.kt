package com.sottti.roller.coasters.data.settings.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.sottti.roller.coasters.data.settings.AppThemeManager
import com.sottti.roller.coasters.data.settings.datasource.SettingsLocalDataSource
import com.sottti.roller.coasters.data.settings.repository.SettingsRepositoryImpl
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
        appThemeManager: AppThemeManager,
    ): SettingsRepository = SettingsRepositoryImpl(
        localDataSource = localDataSource,
        appThemeManager = appThemeManager,
    )

    @Provides
    @Singleton
    fun provideLocalDataSource(
        @ApplicationContext context: Context,
    ): SettingsLocalDataSource = SettingsLocalDataSource(context.dataStore)
}

private const val DATA_STORE_NAME = "settings"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DATA_STORE_NAME)