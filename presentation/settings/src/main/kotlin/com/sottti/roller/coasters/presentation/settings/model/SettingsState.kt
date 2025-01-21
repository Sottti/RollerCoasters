package com.sottti.roller.coasters.presentation.settings.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import co.cuvva.presentation.design.system.icons.model.IconState

@Immutable
internal data class SettingsState(
    val topBar: TopBarState,
    val dynamicColor: DynamicColorState,
)

@Immutable
internal data class TopBarState(
    @StringRes val title: Int,
    val icon: IconState,
)

@Immutable
internal data class DynamicColorState(
    @StringRes val headline: Int,
    @StringRes val supporting: Int,
    val checked: Boolean,
    val icon: IconState,
)