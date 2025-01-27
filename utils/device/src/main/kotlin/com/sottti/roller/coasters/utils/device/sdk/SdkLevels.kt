package com.sottti.roller.coasters.utils.device.sdk

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
public fun SdkLevel.isAtLeastSdk31(): Boolean = value >= Build.VERSION_CODES.S

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
public fun isAtLeastSdk31(): Boolean = SdkLevel(Build.VERSION.SDK_INT).isAtLeastSdk31()

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
public fun SdkLevel.isAtLeastSdk34(): Boolean = value >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
public fun isAtLeastSdk34(): Boolean = SdkLevel(Build.VERSION.SDK_INT).isAtLeastSdk34()

public fun isBelowSdk31(): Boolean = !isAtLeastSdk31()