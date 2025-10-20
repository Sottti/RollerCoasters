package com.sottti.roller.coasters.domain.settings.mapper

import com.sottti.roller.coasters.domain.settings.model.theme.ResolvedTheme
import com.sottti.roller.coasters.domain.settings.model.theme.SystemTheme

internal fun SystemTheme.toResolvedTheme(): ResolvedTheme =
    when (this) {
        SystemTheme.LightAppTheme -> ResolvedTheme.LightAppTheme
        SystemTheme.DarkAppTheme -> ResolvedTheme.DarkAppTheme
    }
