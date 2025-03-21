package com.sottti.roller.coasters.domain.features

import androidx.annotation.ChecksSdkIntAtLeast

public interface Features {
    @ChecksSdkIntAtLeast(api = 34)
    public fun systemColorContrastAvailable(): Boolean

    @ChecksSdkIntAtLeast(api = 31)
    public fun systemDynamicColorAvailable(): Boolean

    @ChecksSdkIntAtLeast(api = 29)
    public fun lightDarkSystemThemingAvailable(): Boolean

    @ChecksSdkIntAtLeast(api = 34)
    public fun measurementSystemAvailable(): Boolean

    @ChecksSdkIntAtLeast(api = 31)
    public fun setPersistentNightModeAvailable(): Boolean
}