package com.sottti.roller.coasters.data.settings.di

import android.content.Context
import com.sottti.roller.coasters.data.settings.dataStore
import com.sottti.roller.coasters.data.settings.repository.SettingsRepository
import com.sottti.roller.coasters.data.settings.repository.SettingsRepositoryImpl
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
        @ApplicationContext context: Context,
    ): SettingsRepository = SettingsRepositoryImpl(context.dataStore)
}