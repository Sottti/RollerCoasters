package com.sottti.roller.coasters.utils.device.di

import com.sottti.roller.coasters.utils.device.accesibility.DeviceAccessibility
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
internal interface DeviceAccessibilityEntryPoint {
    fun getDeviceAccessibility(): DeviceAccessibility
}