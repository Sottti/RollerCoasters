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
import co.cuvva.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.design.system.progress.indicators.ProgressIndicator
import com.sottti.roller.coasters.presentation.design.system.progress.indicators.ProgressIndicatorSize
import com.sottti.roller.coasters.presentation.design.system.switchh.Switch
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastListItemState
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorCheckedState
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorState
import com.sottti.roller.coasters.presentation.settings.model.LanguageListItemState
import com.sottti.roller.coasters.presentation.settings.model.MeasurementSystemListItemState
import com.sottti.roller.coasters.presentation.settings.model.SelectedColorContrastState
import com.sottti.roller.coasters.presentation.settings.model.SelectedLanguageState
import com.sottti.roller.coasters.presentation.settings.model.SelectedMeasurementSystemState
import com.sottti.roller.coasters.presentation.settings.model.SelectedThemeState
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DynamicColorCheckedChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LaunchColorContrastPicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LaunchLanguagePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LaunchMeasurementSystemPicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LaunchThemePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import com.sottti.roller.coasters.presentation.settings.model.ThemeListItemState

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
    onAction: (SettingsAction) -> Unit
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
                state = state.theme.listItem,
                onLaunchThemePicker = { onAction(LaunchThemePicker) })
        }
        item {
            ColorContrastSetting(
                state = state.colorContrast.listItem,
                onLaunchColorContrastPicker = { onAction(LaunchColorContrastPicker) },
            )
        }
        item {
            LanguageSetting(
                state = state.language.listItem,
                onLaunchLanguagePicker = { onAction(LaunchLanguagePicker) },
            )
        }
        item {
            MeasurementSystemSetting(
                state = state.measurementSystem.listItem,
                onLaunchMeasurementSystemPicker = { onAction(LaunchMeasurementSystemPicker) },
            )
        }
    }
}

@Composable
private fun Dialogs(
    state: SettingsState,
    onAction: (SettingsAction) -> Unit
) {
    state.theme.picker?.let { themePickerState ->
        ThemePickerDialog(
            state = themePickerState,
            onAction = onAction,
        )
    }

    state.colorContrast.picker?.let { colorContrastPickerState ->
        ColorContrastPickerDialog(
            state = colorContrastPickerState,
            onAction = onAction,
        )
    }

    state.colorContrast.notAvailableMessage?.let { colorContrastNotAvailableMessageState ->
        ColorContrastNotAvailableDialog(
            state = colorContrastNotAvailableMessageState,
            onAction = onAction,
        )
    }

    state.language.picker?.let { languagePickerState ->
        LanguagePickerDialog(
            state = languagePickerState,
            onAction = onAction,
        )
    }

    state.measurementSystem.picker?.let { measurementSystemPickerState ->
        MeasurementSystemPickerDialog(
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
    onDynamicColorCheckedChange: (Boolean) -> Unit
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
    state: ThemeListItemState,
    onLaunchThemePicker: () -> Unit,
) {
    ListItem(
        modifier = Modifier.clickable { onLaunchThemePicker() },
        headlineContent = { Text.Vanilla(state.headline) },
        leadingContent = { Icon(state.icon) },
        supportingContent = { Text.Vanilla(state.supporting) },
        trailingContent = { ThemeTrailingContent(state.selectedTheme) },
    )
}

@Composable
private fun ThemeTrailingContent(state: SelectedThemeState) {
    when (state) {
        is SelectedThemeState.Loaded -> Text.Vanilla(state.theme.text)
        SelectedThemeState.Loading -> SmallProgressIndicator()
    }
}

@Composable
private fun ColorContrastSetting(
    state: ColorContrastListItemState,
    onLaunchColorContrastPicker: () -> Unit,
) {
    ListItem(
        modifier = Modifier.clickable { onLaunchColorContrastPicker() },
        headlineContent = { Text.Vanilla(state.headline) },
        leadingContent = { Icon(state.icon) },
        supportingContent = { Text.Vanilla(state.supporting) },
        trailingContent = { ColorContrastTrailingContent(state.selectedColorContrast) },
    )
}

@Composable
private fun ColorContrastTrailingContent(state: SelectedColorContrastState) {
    when (state) {
        is SelectedColorContrastState.Loaded -> Text.Vanilla(state.colorContrast.text)
        SelectedColorContrastState.Loading -> SmallProgressIndicator()
    }
}

@Composable
private fun LanguageSetting(
    state: LanguageListItemState,
    onLaunchLanguagePicker: () -> Unit,
) {
    ListItem(
        modifier = Modifier.clickable { onLaunchLanguagePicker() },
        headlineContent = { Text.Vanilla(state.headline) },
        leadingContent = { Icon(state.icon) },
        supportingContent = { Text.Vanilla(state.supporting) },
        trailingContent = { LanguageTrailingContent(state.selectedLanguage) },
    )
}

@Composable
private fun LanguageTrailingContent(state: SelectedLanguageState) {
    when (state) {
        is SelectedLanguageState.Loaded -> Text.Vanilla(state.language.text)
        SelectedLanguageState.Loading -> SmallProgressIndicator()
    }
}

@Composable
private fun SmallProgressIndicator() {
    ProgressIndicator(size = ProgressIndicatorSize.Small)
}

@Composable
private fun MeasurementSystemSetting(
    state: MeasurementSystemListItemState,
    onLaunchMeasurementSystemPicker: () -> Unit,
) {
    ListItem(
        modifier = Modifier.clickable { onLaunchMeasurementSystemPicker() },
        headlineContent = { Text.Vanilla(state.headline) },
        leadingContent = { Icon(state.icon) },
        supportingContent = { Text.Vanilla(state.supporting) },
        trailingContent = { MeasurementSystemTrailingContent(state.selectedMeasurementSystem) },
    )
}

@Composable
private fun MeasurementSystemTrailingContent(state: SelectedMeasurementSystemState) {
    when (state) {
        is SelectedMeasurementSystemState.Loaded -> Text.Vanilla(state.measurementSystem.text)
        SelectedMeasurementSystemState.Loading -> SmallProgressIndicator()
    }
}