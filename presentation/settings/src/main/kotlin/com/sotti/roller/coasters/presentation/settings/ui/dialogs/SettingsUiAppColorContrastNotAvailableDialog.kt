package com.sotti.roller.coasters.presentation.settings.ui.dialogs

import androidx.compose.runtime.Composable
import com.sotti.roller.coasters.presentation.design.system.dialogs.informative.DialogInformative
import com.sotti.roller.coasters.presentation.settings.model.AppColorContrastNotAvailableMessageState
import com.sotti.roller.coasters.presentation.settings.model.SettingsAction
import com.sotti.roller.coasters.presentation.settings.model.SettingsAction.DismissAppColorContrastNotAvailableMessage

@Composable
internal fun AppColorContrastNotAvailableDialog(
    state: AppColorContrastNotAvailableMessageState,
    onAction: (SettingsAction) -> Unit,
) {
    DialogInformative(
        title = state.title,
        text = state.text,
        dismiss = state.dismiss,
        onDismiss = { onAction(DismissAppColorContrastNotAvailableMessage) },
    )
}
