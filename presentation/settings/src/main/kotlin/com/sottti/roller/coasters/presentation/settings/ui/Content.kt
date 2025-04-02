package com.sottti.roller.coasters.presentation.settings.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import co.cuvva.presentation.design.system.icons.ui.Icon
import co.cuvva.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.design.system.progress.indicators.ProgressIndicator
import com.sottti.roller.coasters.presentation.design.system.progress.indicators.ProgressIndicatorSize
import com.sottti.roller.coasters.presentation.design.system.switchh.Switch
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
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DynamicColorCheckedChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LaunchAppColorContrastPicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LaunchAppLanguagePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LaunchAppMeasurementSystemPicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LaunchAppThemePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsState

@Composable
internal fun SettingsContent(
    state: SettingsState,
    onAction: (SettingsAction) -> Unit,
    paddingValues: PaddingValues,
    nestedScrollConnection: NestedScrollConnection,
) {
    SettingsList(nestedScrollConnection, paddingValues, state, onAction)
    Dialogs(state, onAction)
}

@Composable
private fun SettingsList(
    nestedScrollConnection: NestedScrollConnection,
    paddingValues: PaddingValues,
    state: SettingsState,
    onAction: (SettingsAction) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .nestedScroll(nestedScrollConnection),
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
                state = state.appTheme.listItem,
                onLaunchThemePicker = { onAction(LaunchAppThemePicker) })
        }
        item {
            ColorContrastSetting(
                state = state.appColorContrast.listItem,
                onLaunchColorContrastPicker = { onAction(LaunchAppColorContrastPicker) },
            )
        }
        item {
            LanguageSetting(
                state = state.appLanguage.listItem,
                onLaunchLanguagePicker = { onAction(LaunchAppLanguagePicker) },
            )
        }
        item {
            MeasurementSystemSetting(
                state = state.appMeasurementSystem.listItem,
                onLaunchMeasurementSystemPicker = { onAction(LaunchAppMeasurementSystemPicker) },
            )
        }
    }
}

@Composable
private fun Dialogs(
    state: SettingsState,
    onAction: (SettingsAction) -> Unit,
) {
    state.appTheme.picker?.let { themePickerState ->
        AppThemePickerDialog(
            state = themePickerState,
            onAction = onAction,
        )
    }

    state.appColorContrast.picker?.let { colorContrastPickerState ->
        AppColorContrastPickerDialog(
            state = colorContrastPickerState,
            onAction = onAction,
        )
    }

    state.appColorContrast.notAvailableMessage?.let { colorContrastNotAvailableMessageState ->
        AppColorContrastNotAvailableDialog(
            state = colorContrastNotAvailableMessageState,
            onAction = onAction,
        )
    }

    state.appLanguage.picker?.let { languagePickerState ->
        LanguagePickerDialog(
            state = languagePickerState,
            onAction = onAction,
        )
    }

    state.appMeasurementSystem.picker?.let { measurementSystemPickerState ->
        AppMeasurementSystemPickerDialog(
            state = measurementSystemPickerState,
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