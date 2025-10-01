package com.sottti.roller.coasters.data.settings.di

import android.app.UiModeManager
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.sottti.roller.coasters.data.settings.datasource.dataStore
import com.sottti.roller.coasters.data.settings.repository.SettingsRepositoryImpl
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal fun interface SettingsDataModule {

    @Binds
    @Singleton
    fun bindRepository(
        impl: SettingsRepositoryImpl,
    ): SettingsRepository

    companion object {
        @Provides
        @Singleton
        fun provideUiModeManager(
            @ApplicationContext context: Context,
        ): UiModeManager? = context.getSystemService(UiModeManager::class.java)

        @Provides
        @Singleton
        fun provideSettingsDataStore(
            @ApplicationContext context: Context,
        ): DataStore<Preferences> = context.dataStore
    }
}
