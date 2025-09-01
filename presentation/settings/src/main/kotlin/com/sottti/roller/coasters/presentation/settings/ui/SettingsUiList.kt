package com.sottti.roller.coasters.presentation.settings.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Card
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.icons.model.IconState
import com.sottti.roller.coasters.presentation.design.system.icons.ui.circledIcon.CircledIcon
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
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppMeasurementSystemActions
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppThemeActions.LaunchAppThemePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DynamicColorCheckedChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import com.sottti.roller.coasters.presentation.utils.Spacer
import com.sottti.roller.coasters.presentation.utils.plus

@Composable
internal fun SettingsList(
    nestedScrollConnection: NestedScrollConnection,
    padding: PaddingValues,
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
        { onAction(AppMeasurementSystemActions.LaunchAppMeasurementSystemPicker) }
    }
    LazyColumn(
        contentPadding = padding + PaddingValues(vertical = dimensions.padding.medium),
        modifier = Modifier.nestedScroll(nestedScrollConnection),
    ) {
        dynamicColor(state = state, onDynamicColorCheckedChange = onDynamicColorCheckedChange)
        item { Spacer(dimensions.padding.extraSmall) }
        theme(state = state, launchThemePicker = launchThemePicker)
        item { Spacer(dimensions.padding.mediumLarge) }
        colorContrast(state = state, launchColorContrastPicker = launchColorContrastPicker)
        item { Spacer(dimensions.padding.extraSmall) }
        language(state = state, launchLanguagePicker = launchLanguagePicker)
        item { Spacer(dimensions.padding.extraSmall) }
        measurementSystem(
            launchMeasurementSystemPicker = launchMeasurementSystemPicker,
            state = state,
        )
    }
}

private fun LazyListScope.dynamicColor(
    state: SettingsState,
    onDynamicColorCheckedChange: (Boolean) -> Unit,
) {
    state.dynamicColor?.let { dynamicState ->
        val onClick = when (dynamicState.checkedState) {
            is DynamicColorCheckedState.Loaded -> {
                { onDynamicColorCheckedChange(!dynamicState.checkedState.checked) }
            }

            DynamicColorCheckedState.Loading -> null
        }
        settingItem(
            headline = dynamicState.headline,
            supporting = dynamicState.supporting,
            iconState = dynamicState.icon,
            onClick = onClick,
            topRounded = true,
            bottomRounded = false,
            key = "dynamic_color"
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

private fun LazyListScope.theme(
    state: SettingsState,
    launchThemePicker: () -> Unit,
) {
    settingItem(
        headline = state.appTheme.listItem.headline,
        supporting = state.appTheme.listItem.supporting,
        iconState = state.appTheme.listItem.icon,
        onClick = launchThemePicker,
        topRounded = false,
        bottomRounded = true,
        key = "theme",
    ) {
        when (val selection = state.appTheme.listItem.selectedAppTheme) {
            is SelectedAppThemeState.Loaded -> Text.Vanilla(selection.appTheme.text)
            SelectedAppThemeState.Loading -> SmallProgressIndicator()
        }
    }
}

private fun LazyListScope.colorContrast(
    state: SettingsState,
    launchColorContrastPicker: () -> Unit,
) {
    settingItem(
        headline = state.appColorContrast.listItem.headline,
        supporting = state.appColorContrast.listItem.supporting,
        iconState = state.appColorContrast.listItem.icon,
        onClick = launchColorContrastPicker,
        topRounded = true,
        bottomRounded = false,
        key = "color_contrast",
    ) {
        when (val selection = state.appColorContrast.listItem.selectedAppColorContrast) {
            is SelectedAppColorContrastState.Loaded -> Text.Vanilla(selection.appColorContrast.text)
            SelectedAppColorContrastState.Loading -> SmallProgressIndicator()
        }
    }
}

private fun LazyListScope.language(
    state: SettingsState,
    launchLanguagePicker: () -> Unit,
) {
    settingItem(
        headline = state.appLanguage.listItem.headline,
        supporting = state.appLanguage.listItem.supporting,
        iconState = state.appLanguage.listItem.icon,
        onClick = launchLanguagePicker,
        topRounded = false,
        bottomRounded = false,
        key = "language",
    ) {
        when (val selection = state.appLanguage.listItem.selectedAppLanguage) {
            is AppSelectedLanguageState.Loaded -> Text.Vanilla(selection.appLanguage.text)
            AppSelectedLanguageState.Loading -> SmallProgressIndicator()
        }
    }
}

private fun LazyListScope.measurementSystem(
    launchMeasurementSystemPicker: () -> Unit,
    state: SettingsState,
) {
    settingItem(
        headline = state.appMeasurementSystem.listItem.headline,
        supporting = state.appMeasurementSystem.listItem.supporting,
        iconState = state.appMeasurementSystem.listItem.icon,
        onClick = launchMeasurementSystemPicker,
        topRounded = false,
        bottomRounded = true,
        key = "measurement_system",
    ) {
        when (val selection =
            state.appMeasurementSystem.listItem.selectedAppMeasurementSystem) {
            is Loaded -> Text.Vanilla(selection.appMeasurementSystem.text)
            Loading -> SmallProgressIndicator()
        }
    }
}

private fun LazyListScope.settingItem(
    @StringRes headline: Int,
    @StringRes supporting: Int,
    iconState: IconState,
    onClick: (() -> Unit)?,
    topRounded: Boolean,
    bottomRounded: Boolean,
    key: String,
    trailingContent: @Composable () -> Unit,
) {
    item(key = key) {
        SettingsListCard(
            topRounded = topRounded,
            bottomRounded = bottomRounded,
            onClick = onClick,
        ) {
            ListItem(
                modifier = Modifier.fillMaxWidth(),
                colors = ListItemDefaults.colors().copy(containerColor = Color.Transparent),
                headlineContent = { Text.Vanilla(headline) },
                leadingContent = { CircledIcon(iconState) },
                supportingContent = { Text.Vanilla(supporting) },
                trailingContent = trailingContent,
            )
        }
    }
}

@Composable
private fun SettingsListCard(
    topRounded: Boolean,
    bottomRounded: Boolean,
    onClick: (() -> Unit)?,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensions.padding.medium),
        onClick = onClick ?: {},
        shape = when {
            topRounded && bottomRounded -> MaterialTheme.shapes.large
            topRounded -> MaterialTheme.shapes.large.copy(
                bottomStart = MaterialTheme.shapes.extraSmall.bottomStart,
                bottomEnd = MaterialTheme.shapes.extraSmall.bottomEnd,
            )

            bottomRounded -> MaterialTheme.shapes.large.copy(
                topStart = MaterialTheme.shapes.extraSmall.topStart,
                topEnd = MaterialTheme.shapes.extraSmall.topEnd,
            )

            else -> MaterialTheme.shapes.extraSmall
        },
    ) {
        content()
    }
}


@Composable
private fun SmallProgressIndicator() {
    ProgressIndicator(size = ProgressIndicatorSize.Small)
}