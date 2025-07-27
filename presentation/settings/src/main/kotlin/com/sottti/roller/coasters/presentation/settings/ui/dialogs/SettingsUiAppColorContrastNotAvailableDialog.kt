package com.sottti.roller.coasters.presentation.settings.ui.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.sottti.roller.coasters.presentation.design.system.dialogs.informative.DialogInformative
import com.sottti.roller.coasters.presentation.settings.model.AppColorContrastNotAvailableMessageState
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppColorContrastActions.DismissAppColorContrastNotAvailableMessage

@Composable
internal fun AppColorContrastNotAvailableDialog(
    state: AppColorContrastNotAvailableMessageState,
    onAction: (SettingsAction) -> Unit,
) {
    val onDismiss = remember(onAction) {
        { onAction(DismissAppColorContrastNotAvailableMessage) }
    }
    DialogInformative(
        title = state.title,
        text = state.text,
        dismiss = state.dismiss,
        onDismiss = onDismiss,
    )
}
