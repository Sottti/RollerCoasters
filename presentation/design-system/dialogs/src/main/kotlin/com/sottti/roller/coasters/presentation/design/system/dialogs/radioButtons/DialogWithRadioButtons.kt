package com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameter
import co.sotti.roller.coasters.presentation.design.system.icons.ui.icon.Icon
import co.sotti.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.LightDarkThemePreview

@Composable
public fun DialogWithRadioButtons(
    @StringRes title: Int,
    @StringRes confirm: Int,
    @StringRes dismiss: Int,
    options: List<DialogRadioButtonOption>,
    onOptionSelected: (DialogRadioButtonOption) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text.Vanilla(title) },
        text = {
            OptionsList(
                options = options,
                onOptionSelected = onOptionSelected
            )
        },
        confirmButton = { ConfirmButton(text = confirm, onConfirm = { onConfirm() }) },
        dismissButton = { DismissButton(text = dismiss, onDismiss = onDismiss) },
    )
}

@Composable
private fun OptionsList(
    options: List<DialogRadioButtonOption>,
    onOptionSelected: (DialogRadioButtonOption) -> Unit,
) {
    LazyColumn {
        options.forEach { option ->
            item {
                RadioButtonRow(
                    option = option,
                    onOptionSelected = onOptionSelected,
                )
            }
        }
    }
}

@Composable
private fun RadioButtonRow(
    option: DialogRadioButtonOption,
    onOptionSelected: (DialogRadioButtonOption) -> Unit,
) {
    ListItem(
        leadingContent = { Icon(iconState = option.icon, crossfade = true) },
        headlineContent = { Text.Vanilla(option.text) },
        trailingContent = {
            RadioButton(
                selected = option.selected,
                onClick = { onOptionSelected(option) })
        },
        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .clickable { onOptionSelected(option) },
    )
}

@Composable
@LightDarkThemePreview
internal fun DialogWithRadioButtonsPreview(
    @PreviewParameter(DialogWithRadioButtonsPreviewProvider::class)
    state: DialogWithRadioButtonsState,
) {
    RollerCoastersPreviewTheme {
        DialogWithRadioButtons(
            title = state.title,
            confirm = state.confirm,
            dismiss = state.dismiss,
            options = state.options,
            onOptionSelected = {},
            onConfirm = {},
            onDismiss = {},
        )
    }
}