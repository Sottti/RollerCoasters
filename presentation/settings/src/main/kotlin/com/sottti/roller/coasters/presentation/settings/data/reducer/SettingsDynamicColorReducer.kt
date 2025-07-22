package com.sottti.roller.coasters.presentation.settings.data.reducer

import com.sottti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorCheckedState
import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal fun SettingsState.updateDynamicColor(
    dynamicColorChecked: AppDynamicColor,
): SettingsState = copy(
    dynamicColor = dynamicColor?.copy(
        checkedState = DynamicColorCheckedState.Loaded(
            when (dynamicColorChecked) {
                AppDynamicColor.Disabled -> false
                AppDynamicColor.Enabled -> true
            },
        )
    )
)

internal fun MutableStateFlow<SettingsState>.updateDynamicColor(
    dynamicColorChecked: AppDynamicColor,
) {
    update { currentState ->
        currentState.updateDynamicColor(dynamicColorChecked)
    }
}