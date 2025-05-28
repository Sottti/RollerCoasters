package com.sottti.roller.coasters.presentation.design.system.dialogs.informative

import androidx.annotation.StringRes
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sottti.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons.DismissButton
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.LightDarkThemePreview

@Composable
public fun DialogInformative(
    @StringRes title: Int,
    @StringRes text: Int,
    @StringRes dismiss: Int,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text.Vanilla(title) },
        text = { Text.Vanilla(text) },
        confirmButton = { DismissButton(text = dismiss, onDismiss = onDismiss) },
    )
}

@Composable
@LightDarkThemePreview
internal fun DialogInformativePreview(
    @PreviewParameter(DialogInformativePreviewProvider::class)
    state: DialogInformativeState,
) {
    RollerCoastersPreviewTheme {
        DialogInformative(
            title = state.title,
            text = state.text,
            dismiss = state.dismiss,
            onDismiss = {},
        )
    }
}