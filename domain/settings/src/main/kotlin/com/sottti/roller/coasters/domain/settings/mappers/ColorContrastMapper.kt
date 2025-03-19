package com.sottti.roller.coasters.domain.settings.mappers

import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.HighContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.MediumContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.StandardContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.SystemContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast.LowContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.SystemColorContrast

internal fun AppColorContrast.toResolvedColorContrast(): ResolvedColorContrast =
    when (this) {
        HighContrast -> ResolvedColorContrast.HighContrast
        MediumContrast -> ResolvedColorContrast.MediumContrast
        StandardContrast -> ResolvedColorContrast.StandardContrast
        SystemContrast -> throw IllegalStateException("System contrast should be resolved")
    }

internal fun SystemColorContrast.toResolvedColorContrast() =
    when (this) {
        SystemColorContrast.HighContrast -> ResolvedColorContrast.HighContrast
        SystemColorContrast.LowContrast -> LowContrast
        SystemColorContrast.MediumContrast -> ResolvedColorContrast.MediumContrast
        SystemColorContrast.StandardContrast -> ResolvedColorContrast.StandardContrast
    }