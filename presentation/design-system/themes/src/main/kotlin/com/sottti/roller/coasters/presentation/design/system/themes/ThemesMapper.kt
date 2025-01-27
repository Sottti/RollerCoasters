package com.sottti.roller.coasters.presentation.design.system.themes

import com.sottti.roller.coasters.data.settings.model.ColorContrast
import com.sottti.roller.coasters.presentation.design.system.colors.color.ColorContrastTheme
import com.sottti.roller.coasters.utils.device.accesibility.DeviceColorContrast

internal fun DeviceColorContrast.toColorContrastTheme(): ColorContrastTheme =
    when (this) {
        DeviceColorContrast.LowContrast -> ColorContrastTheme.StandardContrast
        DeviceColorContrast.StandardContrast -> ColorContrastTheme.StandardContrast
        DeviceColorContrast.MediumContrast -> ColorContrastTheme.MediumContrast
        DeviceColorContrast.HighContrast -> ColorContrastTheme.HighContrast
    }

internal fun ColorContrast.toColorContrastTheme(
    deviceColorContrast: DeviceColorContrast,
): ColorContrastTheme =
    when (this) {
        ColorContrast.HighContrast -> ColorContrastTheme.HighContrast
        ColorContrast.MediumContrast -> ColorContrastTheme.MediumContrast
        ColorContrast.StandardContrast -> ColorContrastTheme.StandardContrast
        ColorContrast.SystemContrast -> deviceColorContrast.toColorContrastTheme()
    }