package com.sottti.roller.coasters.utils.device

import android.content.Context
import dagger.hilt.android.EntryPointAccessors

public fun provideDeviceAccessibility(
    context: Context,
): DeviceAccessibility =
    EntryPointAccessors.fromApplication(
        context = context.applicationContext,
        entryPoint = DeviceAccessibilityEntryPoint::class.java,
    ).getDeviceAccessibility()