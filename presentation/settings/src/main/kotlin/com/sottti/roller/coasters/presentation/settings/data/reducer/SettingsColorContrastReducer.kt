package com.sottti.roller.coasters.presentation.settings.data.reducer

import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.HighContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.MediumContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.StandardContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.SystemContrast
import com.sottti.roller.coasters.presentation.settings.R
import com.sottti.roller.coasters.presentation.settings.data.mapper.toPresentationModel
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastNotAvailableMessageState
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastPickerState
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastUi
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorCheckedState
import com.sottti.roller.coasters.presentation.settings.model.SelectedColorContrastState
import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal fun MutableStateFlow<SettingsState>.updateAppColorContrast(
    appColorContrast: AppColorContrast,
) {
    update { currentState ->
        currentState.copy(
            colorContrast = currentState.colorContrast.copy(
                listItem = currentState.colorContrast.listItem.copy(
                    selectedColorContrast = SelectedColorContrastState.Loaded(
                        appColorContrast.toPresentationModel(isSelected = true),
                    )
                ),
            )
        )
    }
}

internal fun MutableStateFlow<SettingsState>.showColorContrastPicker(
    appColorContrast: AppColorContrast,
    colorContrastAvailable: Boolean,
) {
    update { currentState ->
        when {
            currentState.isDynamicColorChecked() -> currentState.copy(
                currentState.colorContrast.copy(
                    picker = null,
                    notAvailableMessage = contrastNotAvailableMessageState(),
                )
            )

            else -> currentState.copy(
                colorContrast = currentState.colorContrast.copy(
                    notAvailableMessage = null,
                    picker = colorContrastPickerState(
                        colorContrastAvailable = colorContrastAvailable,
                        selectedColorContrast = appColorContrast.toPresentationModel(isSelected = true)
                    )
                )
            )
        }
    }
}

private fun SettingsState.isDynamicColorChecked() =
    dynamicColor?.checkedState is DynamicColorCheckedState.Loaded && dynamicColor.checkedState.checked

internal fun MutableStateFlow<SettingsState>.updateColorContrastPicker(
    colorContrastAvailable: Boolean,
    selectedContrast: ColorContrastUi,
) {
    update { currentState ->
        currentState.copy(
            colorContrast = currentState.colorContrast.copy(
                picker = colorContrastPickerState(
                    colorContrastAvailable = colorContrastAvailable,
                    selectedColorContrast = selectedContrast,
                )
            )
        )
    }
}

internal fun MutableStateFlow<SettingsState>.hideColorContrastPicker() {
    update { currentState ->
        currentState.copy(colorContrast = currentState.colorContrast.copy(picker = null))
    }
}

private fun contrastNotAvailableMessageState(): ColorContrastNotAvailableMessageState =
    ColorContrastNotAvailableMessageState(
        dismiss = R.string.picker_dismiss,
        text = R.string.color_contrast_not_available_message_text,
        title = R.string.color_contrast_not_available_message_title,
    )

private fun colorContrastPickerState(
    colorContrastAvailable: Boolean,
    selectedColorContrast: ColorContrastUi,
) = ColorContrastPickerState(
    title = R.string.color_contrast_picker_title,
    confirm = R.string.picker_confirm,
    dismiss = R.string.picker_dismiss,
    contrasts = colorContrastsList(colorContrastAvailable, selectedColorContrast),
)

private fun colorContrastsList(
    colorContrastAvailable: Boolean,
    selectedColorContrast: ColorContrastUi,
) = listOfNotNull(
    SystemContrast.toPresentationModel(
        isSelected = selectedColorContrast is ColorContrastUi.SystemContrast,
    ).takeIf { colorContrastAvailable },
    StandardContrast.toPresentationModel(
        isSelected = selectedColorContrast is ColorContrastUi.StandardContrast,
    ),
    MediumContrast.toPresentationModel(
        isSelected = selectedColorContrast is ColorContrastUi.MediumContrast,
    ),
    HighContrast.toPresentationModel(
        isSelected = selectedColorContrast is ColorContrastUi.HighContrast,
    ),
)

internal fun MutableStateFlow<SettingsState>.hideColorContrastNotAvailableMessage() {
    update { currentState ->
        currentState.copy(
            colorContrast = currentState.colorContrast.copy(notAvailableMessage = null)
        )
    }
}