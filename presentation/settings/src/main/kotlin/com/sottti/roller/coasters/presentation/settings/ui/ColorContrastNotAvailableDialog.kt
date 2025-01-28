package com.sottti.roller.coasters.presentation.settings.ui

import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.design.system.dialogs.Dialog
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastNotAvailableMessageState
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DismissColorContrastNotAvailableMessage

@Composable
internal fun ColorContrastNotAvailableDialog(
    state: ColorContrastNotAvailableMessageState,
    onAction: (SettingsAction) -> Unit,
) {
    Dialog.Informative(
        title = state.title,
        text = state.text,
        dismiss = state.dismiss,
        onDismiss = { onAction(DismissColorContrastNotAvailableMessage) },
    )
}