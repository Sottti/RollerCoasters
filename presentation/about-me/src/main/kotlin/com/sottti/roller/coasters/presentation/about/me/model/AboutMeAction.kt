package com.sottti.roller.coasters.presentation.about.me.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
internal sealed interface AboutMeAction {
    @Immutable
    data class OpenUrl(
        @StringRes val urlResId: Int,
        val primaryColor: Color,
    ) : AboutMeAction
}