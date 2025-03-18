package com.sottti.roller.coasters.utils.device.di

import android.app.UiModeManager
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object UtilsDeviceModule {

    @Provides
    @Singleton
    fun provideUiModeManager(
        @ApplicationContext context: Context
    ): UiModeManager? = context.getSystemService(UiModeManager::class.java)
}