package com.sottti.roller.coasters.presentation.app.shell.data

import com.sottti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast
import com.sottti.roller.coasters.presentation.design.system.colors.color.ColorContrast

internal fun ResolvedColorContrast.toColorContrast() =
    when (this) {
        ResolvedColorContrast.HighContrast -> ColorContrast.High
        ResolvedColorContrast.MediumContrast -> ColorContrast.Medium
        ResolvedColorContrast.StandardContrast -> ColorContrast.Standard
        ResolvedColorContrast.LowContrast -> ColorContrast.Low
    }
