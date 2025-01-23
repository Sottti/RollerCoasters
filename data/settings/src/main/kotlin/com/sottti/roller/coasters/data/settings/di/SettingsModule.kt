package com.sottti.roller.coasters.data.settings.di

import android.content.Context
import com.sottti.roller.coasters.data.settings.ThemeManager
import com.sottti.roller.coasters.data.settings.dataStore
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
        themeManager: ThemeManager,
    ): SettingsRepository = SettingsRepositoryImpl(
        localDataSource = localDataSource,
        themeManager = themeManager,
    )

    @Provides
    @Singleton
    fun provideLocalDataSource(
        @ApplicationContext context: Context,
    ): SettingsLocalDataSource = SettingsLocalDataSource(context.dataStore)
}