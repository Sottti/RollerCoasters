package com.sottti.roller.coasters.presentation.design.system.dialogs

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import co.cuvva.presentation.design.system.icons.ui.Icon
import co.cuvva.presentation.design.system.text.Text

public object Dialog {

    @Composable
    public fun Informative(
        @StringRes title: Int,
        @StringRes text: Int,
        @StringRes dismiss: Int,
        onDismiss: () -> Unit,
    ) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(title) },
            text = { Text(text) },
            confirmButton = { DismissButton(text = dismiss, onDismiss = onDismiss) },
        )
    }

    @Composable
    public fun WithRadioButtons(
        @StringRes title: Int,
        @StringRes confirm: Int,
        @StringRes dismiss: Int,
        options: List<RadioButtonOption>,
        onOptionSelected: (RadioButtonOption) -> Unit,
        onConfirm: () -> Unit,
        onDismiss: () -> Unit,
    ) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(title) },
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
}

@Composable
private fun OptionsList(
    options: List<RadioButtonOption>,
    onOptionSelected: (RadioButtonOption) -> Unit,
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
    option: RadioButtonOption,
    onOptionSelected: (RadioButtonOption) -> Unit,
) {
    ListItem(
        leadingContent = { Icon(option.icon) },
        headlineContent = { Text(option.text) },
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
private fun ConfirmButton(
    text: Int,
    onConfirm: () -> Unit,
) {
    TextButton(onClick = { onConfirm() }) {
        Text(text)
    }
}

@Composable
private fun DismissButton(
    text: Int,
    onDismiss: () -> Unit,
) {
    TextButton(onClick = { onDismiss() }) {
        Text(text)
    }
}