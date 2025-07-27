package com.sottti.roller.coasters.presentation.informative

import androidx.annotation.StringRes

public data class InformativeButton(
    @StringRes val text: Int,
    val onClick: () -> Unit,
)
