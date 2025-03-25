package com.sottti.roller.coasters.domain.settings.mapper

import com.sottti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.ResolvedDynamicColor

internal fun AppDynamicColor.toResolvedDynamicColor() = when (this) {
    AppDynamicColor.Enabled -> ResolvedDynamicColor(enabled = true)
    AppDynamicColor.Disabled -> ResolvedDynamicColor(enabled = false)
}