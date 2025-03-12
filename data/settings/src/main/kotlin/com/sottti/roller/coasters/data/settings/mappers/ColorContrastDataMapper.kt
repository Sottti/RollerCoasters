package com.sottti.roller.coasters.data.settings.mappers

import com.sottti.roller.coasters.domain.model.ColorContrast
import org.jetbrains.annotations.VisibleForTesting

@VisibleForTesting
internal const val COLOR_CONTRAST_KEY_HIGH = "high"
@VisibleForTesting
internal const val COLOR_CONTRAST_KEY_MEDIUM = "medium"
@VisibleForTesting
internal const val COLOR_CONTRAST_KEY_STANDARD = "standard"
@VisibleForTesting
internal const val COLOR_CONTRAST_KEY_SYSTEM = "system"

internal val ColorContrast.key: String
    get() = when (this) {
        ColorContrast.HighContrast -> COLOR_CONTRAST_KEY_HIGH
        ColorContrast.MediumContrast -> COLOR_CONTRAST_KEY_MEDIUM
        ColorContrast.StandardContrast -> COLOR_CONTRAST_KEY_STANDARD
        ColorContrast.SystemContrast -> COLOR_CONTRAST_KEY_SYSTEM
    }