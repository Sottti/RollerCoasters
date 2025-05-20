package com.sottti.roller.coasters.presentation.about.me.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
internal sealed class AboutMeAction {
    data class OpenUrl(
        @StringRes val url: Int,
        val primaryColor: Color,
    ) : AboutMeAction()
}