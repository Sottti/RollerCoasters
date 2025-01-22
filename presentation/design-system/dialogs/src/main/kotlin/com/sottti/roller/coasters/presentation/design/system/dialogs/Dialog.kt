package com.sottti.roller.coasters.presentation.design.system.dialogs

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import co.cuvva.presentation.design.system.text.Text

public object Dialog {

    @Composable
    public fun WithRadioButtons(
        @StringRes title: Int,
        @StringRes confirm: Int,
        @StringRes dismiss: Int,
        @StringRes options: List<Int>,
        @StringRes initialSelection: Int,
        onConfirm: (Int) -> Unit,
        onDismiss: () -> Unit,
    ) {
        var selection by remember { mutableIntStateOf(initialSelection) }

        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(title) },
            text = {
                OptionsList(
                    options = options,
                    selection = selection,
                    onOptionSelected = { selection = it })
            },
            confirmButton = { ConfirmButton(text = confirm, onConfirm = { onConfirm(selection) }) },
            dismissButton = { DismissButton(text = dismiss, onDismiss = onDismiss) },
        )
    }
}

@Composable
private fun OptionsList(
    @StringRes options: List<Int>,
    @StringRes selection: Int,
    onOptionSelected: (Int) -> Unit,
) {
    Column {
        options.forEach { option ->
            RadioButtonRow(
                text = option,
                selected = option == selection,
                onOptionSelected = onOptionSelected,
            )
        }
    }
}

@Composable
private fun RadioButtonRow(
    selected: Boolean,
    @StringRes text: Int,
    onOptionSelected: (Int) -> Unit,
) {
    ListItem(
        headlineContent = { Text(text) },
        trailingContent = {
            RadioButton(
                selected = selected,
                onClick = { onOptionSelected(text) })
        },
        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .clickable { onOptionSelected(text) },
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