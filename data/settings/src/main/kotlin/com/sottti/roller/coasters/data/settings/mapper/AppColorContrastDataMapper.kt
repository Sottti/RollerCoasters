package com.sottti.roller.coasters.data.settings.mapper

import androidx.annotation.VisibleForTesting
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast

@VisibleForTesting
internal const val COLOR_CONTRAST_KEY_HIGH = "high"

@VisibleForTesting
internal const val COLOR_CONTRAST_KEY_MEDIUM = "medium"

@VisibleForTesting
internal const val COLOR_CONTRAST_KEY_STANDARD = "standard"

@VisibleForTesting
internal const val COLOR_CONTRAST_KEY_SYSTEM = "system"

internal val AppColorContrast.key: String
    get() = when (this) {
        AppColorContrast.HighContrast -> COLOR_CONTRAST_KEY_HIGH
        AppColorContrast.MediumContrast -> COLOR_CONTRAST_KEY_MEDIUM
        AppColorContrast.StandardContrast -> COLOR_CONTRAST_KEY_STANDARD
        AppColorContrast.System -> COLOR_CONTRAST_KEY_SYSTEM
    }

internal fun String.toAppColorContrast() = when (this) {
    COLOR_CONTRAST_KEY_HIGH -> AppColorContrast.HighContrast
    COLOR_CONTRAST_KEY_MEDIUM -> AppColorContrast.MediumContrast
    COLOR_CONTRAST_KEY_STANDARD -> AppColorContrast.StandardContrast
    else -> AppColorContrast.System
}