package com.sotti.roller.coasters.data.settings.mapper

import com.sotti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor

internal fun Boolean.toAppDynamicColor(): AppDynamicColor =
    if (this) AppDynamicColor.Enabled else AppDynamicColor.Disabled

internal fun AppDynamicColor.toBoolean(): Boolean =
    when (this) {
        AppDynamicColor.Enabled -> true
        AppDynamicColor.Disabled -> false
    }
