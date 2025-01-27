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
import com.sottti.roller.coasters.presentation.design.system.loading.LoadingIndicator
import com.sottti.roller.coasters.presentation.design.system.loading.LoadingIndicatorSize
import com.sottti.roller.coasters.presentation.design.system.switchh.Switch
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastState
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorCheckedState
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorState
import com.sottti.roller.coasters.presentation.settings.model.SelectedColorContrastState
import com.sottti.roller.coasters.presentation.settings.model.SelectedThemeState
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DynamicColorCheckedChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LaunchColorContrastPicker
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
        state.dynamicColor?.let {
            item {
                DynamicColorSetting(
                    onDynamicColorCheckedChange = { onAction(DynamicColorCheckedChange(it)) },
                    state = state.dynamicColor,
                )
            }
        }

        item {
            ThemeSetting(
                state = state.theme,
                onLaunchThemePicker = { onAction(LaunchThemePicker) })
        }

        item {
            ColorContrastSetting(
                state = state.colorContrast,
                onLaunchColorContrastPicker = { onAction(LaunchColorContrastPicker) },
            )
        }
    }

    state.themePicker?.let { themePickerState ->
        ThemePickerDialog(
            state = themePickerState,
            onAction = onAction,
        )
    }

    state.colorContrastPicker?.let { colorContrastPickerState ->
        ColorContrastPickerDialog(
            state = colorContrastPickerState,
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
        trailingContent = { DynamicColorTrailingContent(state, onDynamicColorCheckedChange) },
    )
}

@Composable
private fun DynamicColorTrailingContent(
    state: DynamicColorState,
    onDynamicColorCheckedChange: (Boolean) -> Unit
) {
    when (state.checkedState) {
        is DynamicColorCheckedState.Loaded -> Switch(
            checked = state.checkedState.checked,
            onCheckedChange = { onDynamicColorCheckedChange(it) },
        )

        DynamicColorCheckedState.Loading -> SmallLoadingIndicator()
    }
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
        trailingContent = { ThemeTrailingContent(state) },
    )
}

@Composable
private fun ColorContrastSetting(
    state: ColorContrastState,
    onLaunchColorContrastPicker: () -> Unit,
) {
    ListItem(
        modifier = Modifier.clickable { onLaunchColorContrastPicker() },
        headlineContent = { Text(state.headline) },
        leadingContent = { Icon(state.icon) },
        supportingContent = { Text(state.supporting) },
        trailingContent = { ColorContrastTrailingContent(state) },
    )
}

@Composable
private fun ThemeTrailingContent(state: ThemeState) {
    when (state.selectedTheme) {
        is SelectedThemeState.Loaded -> Text(state.selectedTheme.theme.text)
        SelectedThemeState.Loading -> SmallLoadingIndicator()
    }
}

@Composable
private fun ColorContrastTrailingContent(state: ColorContrastState) {
    when (state.selectedColorContrast) {
        is SelectedColorContrastState.Loaded -> Text(state.selectedColorContrast.colorContrast.text)
        SelectedColorContrastState.Loading -> SmallLoadingIndicator()
    }
}

@Composable
private fun SmallLoadingIndicator() {
    LoadingIndicator(LoadingIndicatorSize.Small)
}