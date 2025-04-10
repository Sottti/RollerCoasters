package com.sottti.roller.coasters.presentation.error

import androidx.annotation.StringRes

public data class ErrorButton(
    @StringRes val text: Int,
    val onClick: () -> Unit,
)