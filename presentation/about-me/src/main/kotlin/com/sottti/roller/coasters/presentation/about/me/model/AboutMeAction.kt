package com.sottti.roller.coasters.presentation.about.me.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

@Immutable
internal sealed class AboutMeAction {
    data class OpenUrl(@StringRes val url: Int) : AboutMeAction()
}