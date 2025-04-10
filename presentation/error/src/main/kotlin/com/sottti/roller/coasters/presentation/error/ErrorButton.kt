package com.sottti.roller.coasters.presentation.error

import androidx.annotation.StringRes

public data class ErrorButton(
    @StringRes val text: Int = R.string.button_text_default,
    val onClick: () -> Unit,
)