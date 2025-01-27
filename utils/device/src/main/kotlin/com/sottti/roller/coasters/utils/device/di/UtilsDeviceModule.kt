package com.sottti.roller.coasters.utils.device.di

import android.app.UiModeManager
import android.content.Context
import android.os.Build
import com.sottti.roller.coasters.utils.device.sdk.SdkLevel
import com.sottti.roller.coasters.utils.device.system.SystemSettings
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

    @Provides
    @Singleton
    fun provideSystemSettings(
        @ApplicationContext context: Context,
        uiModeManager: UiModeManager?,
    ): SystemSettings = SystemSettings(
        sdkLevel = SdkLevel(Build.VERSION.SDK_INT),
        uiModeManager = uiModeManager,
    )
}