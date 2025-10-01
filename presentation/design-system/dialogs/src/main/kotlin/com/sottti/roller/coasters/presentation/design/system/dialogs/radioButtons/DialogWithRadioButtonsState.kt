package com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

@Immutable
internal data class DialogWithRadioButtonsState(
    @StringRes val confirm: Int,
    @StringRes val dismiss: Int,
    @StringRes val title: Int,
    val options: List<DialogRadioButtonOption>,
)
