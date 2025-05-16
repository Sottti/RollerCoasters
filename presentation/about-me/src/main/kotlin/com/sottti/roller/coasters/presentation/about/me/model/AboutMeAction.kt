package com.sottti.roller.coasters.presentation.about.me.model

import androidx.compose.runtime.Immutable
import androidx.annotation.StringRes

@Immutable
internal sealed class AboutMeAction {
    data class OpenUrl(@StringRes val url : Int) : AboutMeAction()
}