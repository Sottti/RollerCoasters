package com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.sottti.roller.coasters.presentation.design.system.icons.model.IconState

@Immutable
public data class DialogRadioButtonOption(
    @StringRes val text: Int,
    val icon: IconState,
    val selected: Boolean,
)