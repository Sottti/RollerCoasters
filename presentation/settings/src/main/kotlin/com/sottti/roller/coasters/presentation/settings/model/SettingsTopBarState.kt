package com.sottti.roller.coasters.presentation.settings.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.sotti.roller.coasters.presentation.design.system.icons.model.IconState

@Immutable
internal data class SettingsTopBarState(
    @StringRes val title: Int,
    val icon: IconState,
)