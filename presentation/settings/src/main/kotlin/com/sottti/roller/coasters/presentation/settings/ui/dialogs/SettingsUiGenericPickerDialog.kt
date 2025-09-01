package com.sottti.roller.coasters.presentation.settings.ui.dialogs

import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons.DialogRadioButtonOption
import com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons.DialogWithRadioButtons

@Composable
internal fun <T> GenericPickerDialog(
    title: Int,
    confirm: Int,
    dismiss: Int,
    items: List<T>,
    toOption: (T) -> DialogRadioButtonOption,
    fromOption: (DialogRadioButtonOption, List<T>) -> T,
    findSelected: (List<T>) -> T,
    onSelect: (T) -> Unit,
    onConfirm: (T) -> Unit,
    onDismiss: () -> Unit,
) {
    val options = items.map(toOption)
    val selectedItem = findSelected(items)
    val onOptionSelected: (DialogRadioButtonOption) -> Unit = { opt ->
        val itm = fromOption(opt, items)
        onSelect(itm)
    }
    val onConfirmAction = { onConfirm(selectedItem) }

    DialogWithRadioButtons(
        title = title,
        options = options,
        confirm = confirm,
        dismiss = dismiss,
        onOptionSelected = onOptionSelected,
        onConfirm = onConfirmAction,
        onDismiss = onDismiss,
    )
}

internal inline fun <T> List<T>.firstSelectedOrFirst(
    crossinline isSelected: (T) -> Boolean,
    errorMessage: String,
): T = firstOrNull(isSelected)
    ?: firstOrNull()
    ?: error(errorMessage)
