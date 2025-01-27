package com.sottti.roller.coasters.utils.device.di

import android.content.Context
import com.sottti.roller.coasters.utils.device.system.SystemSettings
import dagger.hilt.android.EntryPointAccessors

public fun provideSystemSettings(
    context: Context,
): SystemSettings =
    EntryPointAccessors.fromApplication(
        context = context.applicationContext,
        entryPoint = SystemSettingsEntryPoint::class.java,
    ).getSystemSettings()