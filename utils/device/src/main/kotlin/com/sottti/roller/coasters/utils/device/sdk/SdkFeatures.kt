package com.sottti.roller.coasters.utils.device.sdk

public fun isDynamicColorEnabled(): Boolean = isAtLeastSdk31()

public fun isLightDarkThemeSystemEnabled(): Boolean = isAtLeastSdk29()