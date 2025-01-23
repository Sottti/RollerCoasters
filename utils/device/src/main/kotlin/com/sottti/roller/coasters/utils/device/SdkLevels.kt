package com.sottti.roller.coasters.utils.device

import android.os.Build

@androidx.annotation.ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
public fun isAtLeastSdk31(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

public fun isBelowSdk31(): Boolean = !isAtLeastSdk31()