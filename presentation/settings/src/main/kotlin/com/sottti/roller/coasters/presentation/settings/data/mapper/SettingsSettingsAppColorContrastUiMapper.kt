package com.sottti.roller.coasters.presentation.settings.data.mapper

import androidx.compose.runtime.Composable
import co.cuvva.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons.DialogRadioButtonOption
import com.sottti.roller.coasters.presentation.settings.R
import com.sottti.roller.coasters.presentation.settings.model.AppColorContrastUi
import com.sottti.roller.coasters.presentation.settings.model.AppColorContrastUi.HighContrast
import com.sottti.roller.coasters.presentation.settings.model.AppColorContrastUi.MediumContrast
import com.sottti.roller.coasters.presentation.settings.model.AppColorContrastUi.StandardContrast
import com.sottti.roller.coasters.presentation.settings.model.AppColorContrastUi.SystemContrast

@Composable
internal fun AppColorContrastUi.toRadioButtonOption(): DialogRadioButtonOption =
    DialogRadioButtonOption(text, icon, selected)

internal fun DialogRadioButtonOption.toAppColorContrastUi(
    contrasts: List<AppColorContrastUi>,
): AppColorContrastUi = contrasts.find { it.text == text } ?: contrasts.first()

internal fun AppColorContrast.toPresentationModel(selected: Boolean): AppColorContrastUi =
    when (this) {
        AppColorContrast.SystemContrast -> systemContrast(selected)
        AppColorContrast.StandardContrast -> standardContrast(selected)
        AppColorContrast.MediumContrast -> mediumContrast(selected)
        AppColorContrast.HighContrast -> highContrast(selected)
    }

private fun systemContrast(
    selected: Boolean,
): SystemContrast = SystemContrast(
    icon = if (selected) Icons.BrightnessAuto.Filled else Icons.BrightnessAuto.Outlined,
    selected = selected,
    text = R.string.color_contrast_system_contrast,
)

private fun standardContrast(
    selected: Boolean,
): StandardContrast = StandardContrast(
    icon = if (selected) Icons.BrightnessStandard.Filled else Icons.BrightnessStandard.Outlined,
    selected = selected,
    text = R.string.color_contrast_standard_contrast,
)

private fun mediumContrast(
    selected: Boolean,
): MediumContrast = MediumContrast(
    icon = if (selected) Icons.BrightnessMedium.Filled else Icons.BrightnessMedium.Outlined,
    selected = selected,
    text = R.string.color_contrast_medium_contrast,
)

private fun highContrast(
    selected: Boolean,
): HighContrast = HighContrast(
    icon = if (selected) Icons.BrightnessHigh.Filled else Icons.BrightnessHigh.Outlined,
    selected = selected,
    text = R.string.color_contrast_high_contrast,
)

internal fun AppColorContrastUi.toDomain(): AppColorContrast =
    when (this) {
        is HighContrast -> AppColorContrast.HighContrast
        is MediumContrast -> AppColorContrast.MediumContrast
        is StandardContrast -> AppColorContrast.StandardContrast
        is SystemContrast -> AppColorContrast.SystemContrast
    }