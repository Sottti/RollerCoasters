package com.sottti.roller.coasters.utils.device

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
internal interface DeviceAccessibilityEntryPoint {
    fun getDeviceAccessibility(): DeviceAccessibility
}