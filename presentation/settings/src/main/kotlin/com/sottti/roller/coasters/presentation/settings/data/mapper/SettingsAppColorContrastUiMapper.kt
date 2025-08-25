package com.sottti.roller.coasters.presentation.settings.data.mapper

import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons.DialogRadioButtonOption
import com.sottti.roller.coasters.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.presentation.settings.R
import com.sottti.roller.coasters.presentation.settings.model.AppColorContrastUi
import com.sottti.roller.coasters.presentation.settings.model.HighContrast
import com.sottti.roller.coasters.presentation.settings.model.MediumContrast
import com.sottti.roller.coasters.presentation.settings.model.StandardContrast
import com.sottti.roller.coasters.presentation.settings.model.SystemContrast

internal fun AppColorContrastUi.toRadioButtonOption(): DialogRadioButtonOption =
    DialogRadioButtonOption(text, icon, selected)

internal fun DialogRadioButtonOption.toAppColorContrastUi(
    contrasts: List<AppColorContrastUi>,
): AppColorContrastUi = contrasts.find { it.text == text } ?: contrasts.first()

internal fun AppColorContrast.toPresentationModel(
    selected: Boolean,
): AppColorContrastUi =
    when (this) {
        AppColorContrast.System -> systemContrast(selected)
        AppColorContrast.StandardContrast -> standardContrast(selected)
        AppColorContrast.MediumContrast -> mediumContrast(selected)
        AppColorContrast.HighContrast -> highContrast(selected)
    }

internal fun AppColorContrastUi.toDomain(): AppColorContrast =
    when (this) {
        is HighContrast -> AppColorContrast.HighContrast
        is MediumContrast -> AppColorContrast.MediumContrast
        is StandardContrast -> AppColorContrast.StandardContrast
        is SystemContrast -> AppColorContrast.System
    }

private fun systemContrast(
    selected: Boolean,
): SystemContrast = SystemContrast(
    icon = if (selected) Icons.Brightness.Auto.filled else Icons.Brightness.Auto.outlined,
    selected = selected,
    text = R.string.color_contrast_system_contrast,
)

private fun standardContrast(
    selected: Boolean,
): StandardContrast = StandardContrast(
    icon = if (selected) Icons.Brightness.Standard.filled else Icons.Brightness.Standard.outlined,
    selected = selected,
    text = R.string.color_contrast_standard_contrast,
)

private fun mediumContrast(
    selected: Boolean,
): MediumContrast = MediumContrast(
    icon = if (selected) Icons.Brightness.Medium.filled else Icons.Brightness.Medium.outlined,
    selected = selected,
    text = R.string.color_contrast_medium_contrast,
)

private fun highContrast(
    selected: Boolean,
): HighContrast = HighContrast(
    icon = if (selected) Icons.Brightness.High.filled else Icons.Brightness.High.outlined,
    selected = selected,
    text = R.string.color_contrast_high_contrast,
)