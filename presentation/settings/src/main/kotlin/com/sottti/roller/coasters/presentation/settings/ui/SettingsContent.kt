package com.sottti.roller.coasters.presentation.settings.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import co.cuvva.presentation.design.system.icons.ui.Icon
import com.sottti.roller.coasters.presentation.design.system.switchh.Switch
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorState
import com.sottti.roller.coasters.presentation.settings.model.SettingsActions
import com.sottti.roller.coasters.presentation.settings.model.SettingsState

@Composable
internal fun SettingsContent(
    actions: SettingsActions,
    paddingValues: PaddingValues,
    state: SettingsState,
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues)
    ) {
        item {
            DynamicColorSetting(
                onDynamicColorCheckedChange = actions.onDynamicColorCheckedChange,
                state = state.dynamicColor,
            )
        }
    }
}

@Composable
private fun DynamicColorSetting(
    onDynamicColorCheckedChange: (Boolean) -> Unit,
    state: DynamicColorState,
) {
    ListItem(
        headlineContent = { Text(stringResource(id = state.headline)) },
        leadingContent = { Icon(state.icon) },
        supportingContent = { Text(stringResource(id = state.supporting)) },
        trailingContent = {
            Switch(
                checked = state.checked,
                onCheckedChange = { onDynamicColorCheckedChange(it) },
            )
        },
    )
}