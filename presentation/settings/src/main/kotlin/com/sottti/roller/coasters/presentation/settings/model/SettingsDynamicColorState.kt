package com.sottti.roller.coasters.presentation.settings.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.sottti.roller.coasters.presentation.design.system.icons.model.IconState

@Immutable
internal data class DynamicColorState(
    @StringRes val headline: Int,
    @StringRes val supporting: Int,
    val checkedState: DynamicColorCheckedState,
    val icon: IconState,
)

@Immutable
internal sealed class DynamicColorCheckedState {
    @Immutable
    data object Loading : DynamicColorCheckedState()
    @Immutable
    data class Loaded(val checked: Boolean) : DynamicColorCheckedState()
}