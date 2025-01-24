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
import com.sottti.roller.coasters.presentation.settings.model.AppThemeState
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorState
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DynamicColorCheckedChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LaunchAppThemePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsState

@Composable
internal fun SettingsContent(
    state: SettingsState,
    onAction: (SettingsAction) -> Unit,
    paddingValues: PaddingValues,
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues)
    ) {
        state.dynamicColor?.let {
            item {
                DynamicColorSetting(
                    onDynamicColorCheckedChange = { onAction(DynamicColorCheckedChange(it)) },
                    state = state.dynamicColor,
                )
            }
        }

        item {
            AppThemeSetting(
                state = state.appTheme,
                onLaunchAppThemePicker = { onAction(LaunchAppThemePicker) })
        }
    }

    state.appThemePicker?.let { appThemePickerState ->
        AppThemePickerDialog(
            state = appThemePickerState,
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
private fun AppThemeSetting(
    state: AppThemeState,
    onLaunchAppThemePicker: () -> Unit,
) {
    ListItem(
        modifier = Modifier.clickable { onLaunchAppThemePicker() },
        headlineContent = { Text(state.headline) },
        leadingContent = { Icon(state.icon) },
        supportingContent = { Text(state.supporting) },
        trailingContent = { Text(state.trailing) },
    )
}