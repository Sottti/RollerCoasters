package com.sottti.roller.coasters.presentation.settings.ui

import androidx.annotation.StringRes
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
import com.sottti.roller.coasters.presentation.design.system.icons.model.IconState
import com.sottti.roller.coasters.presentation.design.system.icons.ui.icon.Icon
import com.sottti.roller.coasters.presentation.design.system.progress.indicators.ProgressIndicator
import com.sottti.roller.coasters.presentation.design.system.progress.indicators.ProgressIndicatorSize
import com.sottti.roller.coasters.presentation.design.system.switchh.Switch
import com.sottti.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.settings.model.AppSelectedLanguageState
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorCheckedState
import com.sottti.roller.coasters.presentation.settings.model.SelectedAppColorContrastState
import com.sottti.roller.coasters.presentation.settings.model.SelectedAppMeasurementSystemState.Loaded
import com.sottti.roller.coasters.presentation.settings.model.SelectedAppMeasurementSystemState.Loading
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
        {
            checked: Boolean ->
                onAction(DynamicColorCheckedChange(checked))
            }
    }
    val launchThemePicker = remember(onAction) {
        {
            onAction(LaunchAppThemePicker)
        }
    }
    val launchColorContrastPicker = remember(onAction) {
        {
            onAction(LaunchAppColorContrastPicker)
        }
    }
    val launchLanguagePicker = remember(onAction) {
        {
            onAction(LaunchAppLanguagePicker)
        }
    }
    val launchMeasurementSystemPicker = remember(onAction) {
        {
            onAction(LaunchAppMeasurementSystemPicker)
        }
    }
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .nestedScroll(nestedScrollConnection),
    ) {
        state.dynamicColor?.let { dynamicState ->
            item(key = "dynamic_color") {
                SettingItem(
                    headline = dynamicState.headline,
                    supporting = dynamicState.supporting,
                    iconState = dynamicState.icon,
                    onClick = {},
                ) {
                    when (dynamicState.checkedState) {
                        is DynamicColorCheckedState.Loaded -> Switch(
                            checked = dynamicState.checkedState.checked,
                            onCheckedChange = onDynamicColorCheckedChange,
                        )

                        DynamicColorCheckedState.Loading -> SmallProgressIndicator()
                    }
                }
            }
        }
        item {
            SettingItem(
                headline = state.appTheme.listItem.headline,
                supporting = state.appTheme.listItem.supporting,
                iconState = state.appTheme.listItem.icon,
                onClick = launchThemePicker,
            ) {
                when (val selection = state.appTheme.listItem.selectedAppTheme) {
                    is SelectedAppThemeState.Loaded -> Text.Vanilla(selection.appTheme.text)
                    SelectedAppThemeState.Loading -> SmallProgressIndicator()
                }
            }
        }
        item {
            SettingItem(
                headline = state.appColorContrast.listItem.headline,
                supporting = state.appColorContrast.listItem.supporting,
                iconState = state.appColorContrast.listItem.icon,
                onClick = launchColorContrastPicker,
            ) {
                when (val selection = state.appColorContrast.listItem.selectedAppColorContrast) {
                    is SelectedAppColorContrastState.Loaded -> Text.Vanilla(selection.appColorContrast.text)
                    SelectedAppColorContrastState.Loading -> SmallProgressIndicator()
                }
            }
        }
        item {
            SettingItem(
                headline = state.appLanguage.listItem.headline,
                supporting = state.appLanguage.listItem.supporting,
                iconState = state.appLanguage.listItem.icon,
                onClick = launchLanguagePicker,
            ) {
                when (val selection = state.appLanguage.listItem.selectedAppLanguage) {
                    is AppSelectedLanguageState.Loaded -> Text.Vanilla(selection.appLanguage.text)
                    AppSelectedLanguageState.Loading -> SmallProgressIndicator()
                }
            }
        }
        item {
            SettingItem(
                headline = state.appMeasurementSystem.listItem.headline,
                supporting = state.appMeasurementSystem.listItem.supporting,
                iconState = state.appMeasurementSystem.listItem.icon,
                onClick = launchMeasurementSystemPicker,
            ) {
                when (
                    val selection =
                    state.appMeasurementSystem.listItem.selectedAppMeasurementSystem
                ) {
                    is Loaded -> Text.Vanilla(selection.appMeasurementSystem.text)
                    Loading -> SmallProgressIndicator()
                }
            }
        }
    }
}

@Composable
private fun SettingItem(
    @StringRes headline: Int,
    @StringRes supporting: Int,
    iconState: IconState,
    onClick: () -> Unit,
    trailingContent: @Composable () -> Unit,
) {
    ListItem(
        modifier = Modifier.clickable(onClick = onClick),
        headlineContent = { Text.Vanilla(headline) },
        leadingContent = { Icon(iconState) },
        supportingContent = { Text.Vanilla(supporting) },
        trailingContent = trailingContent,
    )
}


@Composable
private fun SmallProgressIndicator() {
    ProgressIndicator(size = ProgressIndicatorSize.Small)
}