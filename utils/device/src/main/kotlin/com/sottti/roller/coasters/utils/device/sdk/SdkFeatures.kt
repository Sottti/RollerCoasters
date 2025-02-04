package com.sottti.roller.coasters.utils.device.sdk

import android.os.Build
import android.os.Build.VERSION_CODES.Q
import android.os.Build.VERSION_CODES.S
import android.os.Build.VERSION_CODES.UPSIDE_DOWN_CAKE
import androidx.annotation.ChecksSdkIntAtLeast
import javax.inject.Inject

public class SdkFeatures @Inject constructor() {
    public fun colorContrastAvailable(): Boolean = isAtLeastSdk34()
    public fun dynamicColorAvailable(): Boolean = isAtLeastSdk31()
    public fun lightDarkSystemThemingAvailable(): Boolean = isAtLeastSdk29()
    public fun setPersistentNightModeAvailable(): Boolean = isAtLeastSdk31()
}

@ChecksSdkIntAtLeast(api = UPSIDE_DOWN_CAKE)
private fun isAtLeastSdk34(): Boolean = Build.VERSION.SDK_INT >= UPSIDE_DOWN_CAKE

@ChecksSdkIntAtLeast(api = S)
private fun isAtLeastSdk31(): Boolean = Build.VERSION.SDK_INT >= S

@ChecksSdkIntAtLeast(api = Q)
private fun isAtLeastSdk29(): Boolean = Build.VERSION.SDK_INT >= Q