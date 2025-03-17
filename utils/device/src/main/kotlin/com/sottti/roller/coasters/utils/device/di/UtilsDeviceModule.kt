package com.sottti.roller.coasters.utils.device.di

import android.app.UiModeManager
import android.content.Context
import com.sottti.roller.coasters.utils.device.sdk.SdkFeatures
import com.sottti.roller.coasters.utils.device.system.AndroidLocaleProvider
import com.sottti.roller.coasters.utils.device.system.LocaleProvider
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
    fun provideSystemSettings(
        @ApplicationContext context: Context,
        localeProvider: LocaleProvider,
        sdkFeatures: SdkFeatures,
        uiModeManager: UiModeManager?,
    ): SystemSettings = SystemSettings(
        localeProvider = localeProvider,
        sdkFeatures = sdkFeatures,
        uiModeManager = uiModeManager,
    )

    @Provides
    @Singleton
    fun provideUiModeManager(
        @ApplicationContext context: Context
    ): UiModeManager? = context.getSystemService(UiModeManager::class.java)

    @Provides
    @Singleton
    fun provideUiLocaleProvider(): LocaleProvider = AndroidLocaleProvider()
}