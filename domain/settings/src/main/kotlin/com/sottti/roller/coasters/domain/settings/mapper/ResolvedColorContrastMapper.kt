package com.sottti.roller.coasters.domain.settings.mapper

import androidx.annotation.VisibleForTesting
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.HighContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.MediumContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.StandardContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.System
import com.sottti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast.LowContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.SystemColorContrast

@VisibleForTesting
internal const val SYSTEM_CONTRAST_UNRESOLVED_MESSAGE = "System contrast should be resolved"

internal fun AppColorContrast.toResolvedColorContrast(): ResolvedColorContrast =
    when (this) {
        HighContrast -> ResolvedColorContrast.HighContrast
        MediumContrast -> ResolvedColorContrast.MediumContrast
        StandardContrast -> ResolvedColorContrast.StandardContrast
        System -> throw IllegalStateException(SYSTEM_CONTRAST_UNRESOLVED_MESSAGE)
    }

internal fun SystemColorContrast.toResolvedColorContrast() =
    when (this) {
        SystemColorContrast.HighContrast -> ResolvedColorContrast.HighContrast
        SystemColorContrast.LowContrast -> LowContrast
        SystemColorContrast.MediumContrast -> ResolvedColorContrast.MediumContrast
        SystemColorContrast.StandardContrast -> ResolvedColorContrast.StandardContrast
    }