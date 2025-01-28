package com.sottti.roller.coasters.utils.device.sdk

public fun isColorContrastAvailable(): Boolean = isAtLeastSdk34()
public fun isDynamicColorAvailable(): Boolean = isAtLeastSdk31()
public fun isLightDarkThemeSystemAvailable(): Boolean = isAtLeastSdk29()