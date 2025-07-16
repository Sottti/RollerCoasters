package com.sottti.roller.coasters.presentation.settings.data.reducer

import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sottti.roller.coasters.presentation.settings.R
import com.sottti.roller.coasters.presentation.settings.data.mapper.toPresentationModel
import com.sottti.roller.coasters.presentation.settings.model.AppColorContrastNotAvailableMessageState
import com.sottti.roller.coasters.presentation.settings.model.AppColorContrastPickerState
import com.sottti.roller.coasters.presentation.settings.model.AppColorContrastUi
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorCheckedState
import com.sottti.roller.coasters.presentation.settings.model.HighContrast
import com.sottti.roller.coasters.presentation.settings.model.MediumContrast
import com.sottti.roller.coasters.presentation.settings.model.SelectedAppColorContrastState
import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import com.sottti.roller.coasters.presentation.settings.model.StandardContrast
import com.sottti.roller.coasters.presentation.settings.model.SystemContrast

internal fun SettingsState.updateAppColorContrast(
    newAppColorContrast: AppColorContrast,
) = copy(
    appColorContrast = this.appColorContrast.copy(
        listItem = this.appColorContrast.listItem.copy(
            selectedAppColorContrast = SelectedAppColorContrastState.Loaded(
                newAppColorContrast.toPresentationModel(selected = true),
            )
        ),
    ),
)

internal fun SettingsState.showAppColorContrastPicker(
    selectedAppColorContrast: AppColorContrast,
    appColorContrastAvailable: Boolean,
): SettingsState = when {
    isDynamicColorChecked() -> copy(
        appColorContrast.copy(
            picker = null,
            notAvailableMessage = appColorContrastNotAvailableMessageState(),
        )
    )

    else -> copy(
        appColorContrast = appColorContrast.copy(
            notAvailableMessage = null,
            picker = appColorContrastPickerState(
                appColorContrastAvailable = appColorContrastAvailable,
                selectedAppColorContrast =
                    selectedAppColorContrast.toPresentationModel(selected = true)
            )
        )
    )
}

private fun SettingsState.isDynamicColorChecked() =
    dynamicColor?.checkedState is DynamicColorCheckedState.Loaded
            && dynamicColor.checkedState.checked

internal fun SettingsState.updateAppColorContrastPicker(
    appColorContrastAvailable: Boolean,
    selectedAppColorContrast: AppColorContrastUi,
) = copy(
    appColorContrast = appColorContrast.copy(
        picker = appColorContrastPickerState(
            appColorContrastAvailable = appColorContrastAvailable,
            selectedAppColorContrast = selectedAppColorContrast,
        )
    )
)

internal fun SettingsState.hideAppColorContrastPicker() =
    copy(appColorContrast = appColorContrast.copy(picker = null))

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
    AppColorContrast.System.toPresentationModel(
        selected = selectedAppColorContrast is SystemContrast
    ).takeIf { appColorContrastAvailable },
    AppColorContrast.StandardContrast.toPresentationModel(
        selected = selectedAppColorContrast is StandardContrast
    ),
    AppColorContrast.MediumContrast.toPresentationModel(
        selected = selectedAppColorContrast is MediumContrast
    ),
    AppColorContrast.HighContrast.toPresentationModel(
        selected = selectedAppColorContrast is HighContrast
    ),
)

internal fun SettingsState.hideAppColorContrastNotAvailableMessage() =
    copy(appColorContrast = appColorContrast.copy(notAvailableMessage = null))