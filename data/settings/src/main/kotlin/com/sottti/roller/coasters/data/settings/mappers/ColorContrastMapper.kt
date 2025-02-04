package com.sottti.roller.coasters.data.settings.mappers

import com.sottti.roller.coasters.data.settings.model.ColorContrast

private const val COLOR_CONTRAST_KEY_HIGH = "high"
private const val COLOR_CONTRAST_KEY_MEDIUM = "medium"
private const val COLOR_CONTRAST_KEY_STANDARD = "standard"
private const val COLOR_CONTRAST_KEY_SYSTEM = "system"

internal val ColorContrast.key: String
    get() = when (this) {
        ColorContrast.HighContrast -> COLOR_CONTRAST_KEY_HIGH
        ColorContrast.MediumContrast -> COLOR_CONTRAST_KEY_MEDIUM
        ColorContrast.StandardContrast -> COLOR_CONTRAST_KEY_STANDARD
        ColorContrast.SystemContrast -> COLOR_CONTRAST_KEY_SYSTEM
    }