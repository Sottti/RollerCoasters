package com.sottti.roller.coasters.presentation.design.system.dialogs

import androidx.annotation.StringRes
import co.cuvva.presentation.design.system.icons.model.IconState

public data class RadioButtonOption(
    @StringRes val text: Int,
    val icon: IconState,
    val selected : Boolean,
)