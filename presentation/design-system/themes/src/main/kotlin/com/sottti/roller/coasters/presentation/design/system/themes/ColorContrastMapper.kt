package com.sottti.roller.coasters.presentation.design.system.themes

import com.sottti.roller.coasters.domain.model.ColorContrast
import com.sottti.roller.coasters.domain.model.SystemColorContrast
import com.sottti.roller.coasters.presentation.design.system.colors.color.AppColorContrast

internal fun ColorContrast.toAppColorContrast(
    systemColorContrast: SystemColorContrast,
): AppColorContrast =
    when (this) {
        ColorContrast.HighContrast -> AppColorContrast.HighContrast
        ColorContrast.MediumContrast -> AppColorContrast.MediumContrast
        ColorContrast.StandardContrast -> AppColorContrast.StandardContrast
        ColorContrast.SystemContrast -> systemColorContrast.toAppColorContrast()
    }

private fun SystemColorContrast.toAppColorContrast(): AppColorContrast =
    when (this) {
        SystemColorContrast.LowContrast -> AppColorContrast.StandardContrast
        SystemColorContrast.StandardContrast -> AppColorContrast.StandardContrast
        SystemColorContrast.MediumContrast -> AppColorContrast.MediumContrast
        SystemColorContrast.HighContrast -> AppColorContrast.HighContrast
    }