package com.sottti.roller.coasters.presentation.settings.data.mapper

import androidx.compose.runtime.Composable
import co.cuvva.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons.DialogRadioButtonOption
import com.sottti.roller.coasters.presentation.settings.R
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastUi
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastUi.HighContrast
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastUi.MediumContrast
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastUi.StandardContrast
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastUi.SystemContrast

@Composable
internal fun ColorContrastUi.toRadioButtonOption(): DialogRadioButtonOption =
    DialogRadioButtonOption(text, icon, selected)

internal fun DialogRadioButtonOption.toColorContrastUi(
    contrasts: List<ColorContrastUi>,
): ColorContrastUi = contrasts.find { it.text == text } ?: contrasts.first()

internal fun AppColorContrast.toPresentationModel(isSelected: Boolean): ColorContrastUi =
    when (this) {
        AppColorContrast.SystemContrast -> systemContrast(isSelected)
        AppColorContrast.StandardContrast -> standardContrast(isSelected)
        AppColorContrast.MediumContrast -> mediumContrast(isSelected)
        AppColorContrast.HighContrast -> highContrast(isSelected)
    }

private fun systemContrast(
    isSelected: Boolean,
): SystemContrast = SystemContrast(
    icon = if (isSelected) Icons.BrightnessAuto.Filled else Icons.BrightnessAuto.Outlined,
    selected = isSelected,
    text = R.string.color_contrast_system_contrast,
)

private fun standardContrast(
    isSelected: Boolean,
): StandardContrast = StandardContrast(
    icon = if (isSelected) Icons.BrightnessStandard.Filled else Icons.BrightnessStandard.Outlined,
    selected = isSelected,
    text = R.string.color_contrast_standard_contrast,
)

private fun mediumContrast(
    isSelected: Boolean,
): MediumContrast = MediumContrast(
    icon = if (isSelected) Icons.BrightnessMedium.Filled else Icons.BrightnessMedium.Outlined,
    selected = isSelected,
    text = R.string.color_contrast_medium_contrast,
)

private fun highContrast(
    isSelected: Boolean,
): HighContrast = HighContrast(
    icon = if (isSelected) Icons.BrightnessHigh.Filled else Icons.BrightnessHigh.Outlined,
    selected = isSelected,
    text = R.string.color_contrast_high_contrast,
)

internal fun ColorContrastUi.toDomain(): AppColorContrast =
    when (this) {
        is HighContrast -> AppColorContrast.HighContrast
        is MediumContrast -> AppColorContrast.MediumContrast
        is StandardContrast -> AppColorContrast.StandardContrast
        is SystemContrast -> AppColorContrast.SystemContrast
    }