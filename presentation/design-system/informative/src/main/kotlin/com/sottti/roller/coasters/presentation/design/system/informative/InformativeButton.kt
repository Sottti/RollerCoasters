package com.sottti.roller.coasters.presentation.design.system.informative

import androidx.annotation.StringRes

public data class InformativeButton(
    @StringRes val text: Int,
    val onClick: () -> Unit,
)