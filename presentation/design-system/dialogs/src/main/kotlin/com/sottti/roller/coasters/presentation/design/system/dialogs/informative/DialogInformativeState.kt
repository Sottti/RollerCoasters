package com.sottti.roller.coasters.presentation.design.system.dialogs.informative

import androidx.annotation.StringRes

internal data class DialogInformativeState(
    @StringRes val dismiss: Int,
    @StringRes val text: Int,
    @StringRes val title: Int,
)