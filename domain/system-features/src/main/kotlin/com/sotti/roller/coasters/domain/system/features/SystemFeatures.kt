package com.sotti.roller.coasters.domain.system.features

import androidx.annotation.ChecksSdkIntAtLeast

public interface SystemFeatures {
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
