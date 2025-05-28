package com.sottti.roller.coasters.presentation.settings.data.reducer

import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sottti.roller.coasters.presentation.settings.R
import com.sottti.roller.coasters.presentation.settings.data.mapper.toPresentationModel
import com.sottti.roller.coasters.presentation.settings.model.AppColorContrastNotAvailableMessageState
import com.sottti.roller.coasters.presentation.settings.model.AppColorContrastPickerState
import com.sottti.roller.coasters.presentation.settings.model.AppColorContrastUi
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorCheckedState
import com.sottti.roller.coasters.presentation.settings.model.SelectedAppColorContrastState
import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal fun MutableStateFlow<SettingsState>.updateAppColorContrast(
    appColorContrast: AppColorContrast,
) {
    update { currentState ->
        currentState.copy(
            appColorContrast = currentState.appColorContrast.copy(
                listItem = currentState.appColorContrast.listItem.copy(
                    selectedAppColorContrast = SelectedAppColorContrastState.Loaded(
                        appColorContrast.toPresentationModel(selected = true),
                    )
                ),
            )
        )
    }
}

internal fun MutableStateFlow<SettingsState>.showAppColorContrastPicker(
    appColorContrast: AppColorContrast,
    appColorContrastAvailable: Boolean,
) {
    update { currentState ->
        when {
            currentState.isDynamicColorChecked() -> currentState.copy(
                currentState.appColorContrast.copy(
                    picker = null,
                    notAvailableMessage = appColorContrastNotAvailableMessageState(),
                )
            )

            else -> currentState.copy(
                appColorContrast = currentState.appColorContrast.copy(
                    notAvailableMessage = null,
                    picker = appColorContrastPickerState(
                        appColorContrastAvailable = appColorContrastAvailable,
                        selectedAppColorContrast =
                            appColorContrast.toPresentationModel(selected = true)
                    )
                )
            )
        }
    }
}

private fun SettingsState.isDynamicColorChecked() =
    dynamicColor?.checkedState is DynamicColorCheckedState.Loaded
            && dynamicColor.checkedState.checked

internal fun MutableStateFlow<SettingsState>.updateAppColorContrastPicker(
    appColorContrastAvailable: Boolean,
    selectedAppColorContrast: AppColorContrastUi,
) {
    update { currentState ->
        currentState.copy(
            appColorContrast = currentState.appColorContrast.copy(
                picker = appColorContrastPickerState(
                    appColorContrastAvailable = appColorContrastAvailable,
                    selectedAppColorContrast = selectedAppColorContrast,
                )
            )
        )
    }
}

internal fun MutableStateFlow<SettingsState>.hideAppColorContrastPicker() {
    update { currentState ->
        currentState.copy(appColorContrast = currentState.appColorContrast.copy(picker = null))
    }
}

private fun appColorContrastNotAvailableMessageState(): AppColorContrastNotAvailableMessageState =
    AppColorContrastNotAvailableMessageState(
        dismiss = R.string.picker_dismiss,
        text = R.string.color_contrast_not_available_message_text,
        title = R.string.color_contrast_not_available_message_title,
    )

private fun appColorContrastPickerState(
    appColorContrastAvailable: Boolean,
    selectedAppColorContrast: AppColorContrastUi,
) = AppColorContrastPickerState(
    title = R.string.color_contrast_picker_title,
    confirm = R.string.picker_confirm,
    dismiss = R.string.picker_dismiss,
    appColorContrasts = colorContrastsList(appColorContrastAvailable, selectedAppColorContrast),
)

private fun colorContrastsList(
    appColorContrastAvailable: Boolean,
    selectedAppColorContrast: AppColorContrastUi,
) = listOfNotNull(
    AppColorContrast.SystemContrast.toPresentationModel(
        selected = selectedAppColorContrast is AppColorContrastUi.SystemContrast
    ).takeIf { appColorContrastAvailable },
    AppColorContrast.StandardContrast.toPresentationModel(
        selected = selectedAppColorContrast is AppColorContrastUi.StandardContrast
    ),
    AppColorContrast.MediumContrast.toPresentationModel(
        selected = selectedAppColorContrast is AppColorContrastUi.MediumContrast
    ),
    AppColorContrast.HighContrast.toPresentationModel(
        selected = selectedAppColorContrast is AppColorContrastUi.HighContrast
    ),
)

internal fun MutableStateFlow<SettingsState>.hideAppColorContrastNotAvailableMessage() {
    update { currentState ->
        currentState.copy(
            appColorContrast = currentState.appColorContrast.copy(notAvailableMessage = null)
        )
    }
}