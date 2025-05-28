package com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons

import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.design.system.text.Text

@Composable
internal fun ConfirmButton(
    text: Int,
    onConfirm: () -> Unit,
) {
    TextButton(onClick = { onConfirm() }) {
        Text.Vanilla(text)
    }
}

@Composable
internal fun DismissButton(
    text: Int,
    onDismiss: () -> Unit,
) {
    TextButton(onClick = { onDismiss() }) {
        Text.Vanilla(text)
    }
}