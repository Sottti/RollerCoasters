package com.sottti.roller.coasters.presentation.settings.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.sottti.roller.coasters.presentation.design.system.icons.ui.icon.Icon
import com.sottti.roller.coasters.presentation.design.system.progress.indicators.ProgressIndicator
import com.sottti.roller.coasters.presentation.design.system.progress.indicators.ProgressIndicatorSize
import com.sottti.roller.coasters.presentation.design.system.switchh.Switch
import com.sottti.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.settings.model.AppColorContrastListItemState
import com.sottti.roller.coasters.presentation.settings.model.AppLanguageListItemState
import com.sottti.roller.coasters.presentation.settings.model.AppMeasurementSystemListItemState
import com.sottti.roller.coasters.presentation.settings.model.AppSelectedLanguageState
import com.sottti.roller.coasters.presentation.settings.model.AppThemeListItemState
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorCheckedState
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorState
import com.sottti.roller.coasters.presentation.settings.model.SelectedAppColorContrastState
import com.sottti.roller.coasters.presentation.settings.model.SelectedAppMeasurementSystemState
import com.sottti.roller.coasters.presentation.settings.model.SelectedAppThemeState
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppColorContrastActions.LaunchAppColorContrastPicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppLanguageActions.LaunchAppLanguagePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppMeasurementSystemActions.LaunchAppMeasurementSystemPicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppThemeActions.LaunchAppThemePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DynamicColorCheckedChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsState

@Composable
internal fun SettingsList(
    nestedScrollConnection: NestedScrollConnection,
    paddingValues: PaddingValues,
    state: SettingsState,
    onAction: (SettingsAction) -> Unit,
) {
    val onDynamicColorCheckedChange = remember(onAction) {
        { checked: Boolean -> onAction(DynamicColorCheckedChange(checked)) }
    }
    val launchThemePicker = remember(onAction) {
        { onAction(LaunchAppThemePicker) }
    }
    val launchColorContrastPicker = remember(onAction) {
        { onAction(LaunchAppColorContrastPicker) }
    }
    val launchLanguagePicker = remember(onAction) {
        { onAction(LaunchAppLanguagePicker) }
    }
    val launchMeasurementSystemPicker = remember(onAction) {
        { onAction(LaunchAppMeasurementSystemPicker) }
    }
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .nestedScroll(nestedScrollConnection),
    ) {
        state.dynamicColor?.let {
            item {
                DynamicColorSetting(
                    state = state.dynamicColor,
                    onDynamicColorCheckedChange = onDynamicColorCheckedChange,
                )
            }
        }
        item {
            ThemeSetting(
                state = state.appTheme.listItem,
                onLaunchThemePicker = launchThemePicker,
            )
        }
        item {
            ColorContrastSetting(
                state = state.appColorContrast.listItem,
                onLaunchColorContrastPicker = launchColorContrastPicker,
            )
        }
        item {
            LanguageSetting(
                state = state.appLanguage.listItem,
                onLaunchLanguagePicker = launchLanguagePicker,
            )
        }
        item {
            MeasurementSystemSetting(
                state = state.appMeasurementSystem.listItem,
                onLaunchMeasurementSystemPicker = launchMeasurementSystemPicker,
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
        headlineContent = { Text.Vanilla(state.headline) },
        leadingContent = { Icon(state.icon) },
        supportingContent = { Text.Vanilla(state.supporting) },
        trailingContent = { DynamicColorTrailingContent(state, onDynamicColorCheckedChange) },
    )
}

@Composable
private fun DynamicColorTrailingContent(
    state: DynamicColorState,
    onDynamicColorCheckedChange: (Boolean) -> Unit,
) {
    when (state.checkedState) {
        is DynamicColorCheckedState.Loaded -> Switch(
            checked = state.checkedState.checked,
            onCheckedChange = { onDynamicColorCheckedChange(it) },
        )

        DynamicColorCheckedState.Loading -> SmallProgressIndicator()
    }
}

@Composable
private fun ThemeSetting(
    state: AppThemeListItemState,
    onLaunchThemePicker: () -> Unit,
) {
    ListItem(
        modifier = Modifier.clickable { onLaunchThemePicker() },
        headlineContent = { Text.Vanilla(state.headline) },
        leadingContent = { Icon(state.icon) },
        supportingContent = { Text.Vanilla(state.supporting) },
        trailingContent = { ThemeTrailingContent(state.selectedAppTheme) },
    )
}

@Composable
private fun ThemeTrailingContent(state: SelectedAppThemeState) {
    when (state) {
        is SelectedAppThemeState.Loaded -> Text.Vanilla(state.appTheme.text)
        SelectedAppThemeState.Loading -> SmallProgressIndicator()
    }
}

@Composable
private fun ColorContrastSetting(
    state: AppColorContrastListItemState,
    onLaunchColorContrastPicker: () -> Unit,
) {
    ListItem(
        modifier = Modifier.clickable { onLaunchColorContrastPicker() },
        headlineContent = { Text.Vanilla(state.headline) },
        leadingContent = { Icon(state.icon) },
        supportingContent = { Text.Vanilla(state.supporting) },
        trailingContent = { ColorContrastTrailingContent(state.selectedAppColorContrast) },
    )
}

@Composable
private fun ColorContrastTrailingContent(state: SelectedAppColorContrastState) {
    when (state) {
        is SelectedAppColorContrastState.Loaded -> Text.Vanilla(state.appColorContrast.text)
        SelectedAppColorContrastState.Loading -> SmallProgressIndicator()
    }
}

@Composable
private fun LanguageSetting(
    state: AppLanguageListItemState,
    onLaunchLanguagePicker: () -> Unit,
) {
    ListItem(
        modifier = Modifier.clickable { onLaunchLanguagePicker() },
        headlineContent = { Text.Vanilla(state.headline) },
        leadingContent = { Icon(state.icon) },
        supportingContent = { Text.Vanilla(state.supporting) },
        trailingContent = { LanguageTrailingContent(state.selectedAppLanguage) },
    )
}

@Composable
private fun LanguageTrailingContent(state: AppSelectedLanguageState) {
    when (state) {
        is AppSelectedLanguageState.Loaded -> Text.Vanilla(state.appLanguage.text)
        AppSelectedLanguageState.Loading -> SmallProgressIndicator()
    }
}

@Composable
private fun SmallProgressIndicator() {
    ProgressIndicator(size = ProgressIndicatorSize.Small)
}

@Composable
private fun MeasurementSystemSetting(
    state: AppMeasurementSystemListItemState,
    onLaunchMeasurementSystemPicker: () -> Unit,
) {
    ListItem(
        modifier = Modifier.clickable { onLaunchMeasurementSystemPicker() },
        headlineContent = { Text.Vanilla(state.headline) },
        leadingContent = { Icon(state.icon) },
        supportingContent = { Text.Vanilla(state.supporting) },
        trailingContent = { MeasurementSystemTrailingContent(state.selectedAppMeasurementSystem) },
    )
}

@Composable
private fun MeasurementSystemTrailingContent(state: SelectedAppMeasurementSystemState) {
    when (state) {
        is SelectedAppMeasurementSystemState.Loaded -> Text.Vanilla(state.appMeasurementSystem.text)
        SelectedAppMeasurementSystemState.Loading -> SmallProgressIndicator()
    }
}