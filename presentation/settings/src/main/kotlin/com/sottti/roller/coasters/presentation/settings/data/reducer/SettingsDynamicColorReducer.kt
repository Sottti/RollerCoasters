package com.sottti.roller.coasters.presentation.settings.data.reducer

import com.sottti.roller.coasters.presentation.settings.model.DynamicColorCheckedState
import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal fun MutableStateFlow<SettingsState>.updateDynamicColor(
    dynamicColorChecked: Boolean,
) {
    update { currentState ->
        currentState.copy(
            dynamicColor = currentState.dynamicColor?.copy(
                checkedState = DynamicColorCheckedState.Loaded(dynamicColorChecked)
            )
        )
    }
}