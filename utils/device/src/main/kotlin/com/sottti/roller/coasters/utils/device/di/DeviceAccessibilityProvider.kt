package com.sottti.roller.coasters.utils.device.di

import android.content.Context
import com.sottti.roller.coasters.utils.device.accesibility.DeviceAccessibility
import dagger.hilt.android.EntryPointAccessors

public fun provideDeviceAccessibility(
    context: Context,
): DeviceAccessibility =
    EntryPointAccessors.fromApplication(
        context = context.applicationContext,
        entryPoint = DeviceAccessibilityEntryPoint::class.java,
    ).getDeviceAccessibility()