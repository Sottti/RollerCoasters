package com.sottti.roller.coasters.presentation.settings.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import co.cuvva.presentation.design.system.icons.ui.Icon
import co.cuvva.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.design.system.switchh.Switch
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorState
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DynamicColorCheckedChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LaunchThemePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import com.sottti.roller.coasters.presentation.settings.model.ThemeState

@Composable
internal fun SettingsContent(
    state: SettingsState,
    onAction: (SettingsAction) -> Unit,
    paddingValues: PaddingValues,
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues)
    ) {
        item {
            DynamicColorSetting(
                onDynamicColorCheckedChange = { onAction(DynamicColorCheckedChange(it)) },
                state = state.dynamicColor,
            )
        }

        item {
            ThemeSetting(
                state = state.theme,
                onLaunchThemePicker = { onAction(LaunchThemePicker) })
        }
    }

    state.themePicker?.let { themePickerState ->
        ThemePickerDialog(
            state = themePickerState,
            onAction = onAction,
        )
    }
}

@Composable
private fun DynamicColorSetting(
    onDynamicColorCheckedChange: (Boolean) -> Unit,
    state: DynamicColorState,
) {
    ListItem(
        headlineContent = { Text(state.headline) },
        leadingContent = { Icon(state.icon) },
        supportingContent = { Text(state.supporting) },
        trailingContent = {
            Switch(
                checked = state.checked,
                onCheckedChange = { onDynamicColorCheckedChange(it) },
            )
        },
    )
}

@Composable
private fun ThemeSetting(
    state: ThemeState,
    onLaunchThemePicker: () -> Unit,
) {
    ListItem(
        modifier = Modifier.clickable { onLaunchThemePicker() },
        headlineContent = { Text(state.headline) },
        leadingContent = { Icon(state.icon) },
        supportingContent = { Text(state.supporting) },
        trailingContent = { Text(state.trailing) },
    )
}