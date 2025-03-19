package com.sottti.roller.coasters.utils.device.mappers

import androidx.annotation.FloatRange
import com.sottti.roller.coasters.domain.settings.model.SystemColorContrast

internal fun toSystemColorContrast(
    @FloatRange(from = -1.0, to = 1.0) contrast: Float,
): SystemColorContrast =
    when {
        contrast < 0f -> SystemColorContrast.LowContrast
        contrast < 0.5f -> SystemColorContrast.StandardContrast
        contrast < 1.0f -> SystemColorContrast.MediumContrast
        contrast >= 1.0f -> SystemColorContrast.HighContrast
        else -> SystemColorContrast.StandardContrast
    }