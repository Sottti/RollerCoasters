package com.sottti.roller.coasters.presentation.settings.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme
import com.sottti.roller.coasters.presentation.settings.data.initialState
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateAppColorContrast
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateAppLanguage
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateAppMeasurementSystem
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateAppTheme
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateDynamicColor
import com.sottti.roller.coasters.presentation.settings.model.SettingsPreviewState
import com.sottti.roller.coasters.presentation.settings.model.SettingsState

@OptIn(ExperimentalMaterial3Api::class)
internal class SettingsUiStateProvider : PreviewParameterProvider<SettingsPreviewState> {
    override val values: Sequence<SettingsPreviewState> = sequenceOf(
        loadingStateWithDynamicColorAvailable,
        loadingStateWithDynamicColorNotAvailable,
        loadedStateWithDynamicColorAvailableAndDisabled,
        loadedStateWithDynamicColorAvailableAndEnabled,
    )
}

private fun previewState(
    state: SettingsState,
): SettingsPreviewState =
    SettingsPreviewState(
        onAction = {},
        onBackNavigation = {},
        state = state,
    )

private val loadingStateWithDynamicColorAvailable = previewState(state = initialState(true))
private val loadingStateWithDynamicColorNotAvailable = previewState(state = initialState(false))
private val loadedStateWithDynamicColorAvailableAndDisabled = previewState(
    state = initialState(true)
        .updateDynamicColor(AppDynamicColor.Disabled)
        .updateAppTheme(AppTheme.System)
        .updateAppColorContrast(AppColorContrast.System)
        .updateAppLanguage(AppLanguage.System)
        .updateAppMeasurementSystem(AppMeasurementSystem.System)
)
private val loadedStateWithDynamicColorAvailableAndEnabled = previewState(
    state = initialState(true)
        .updateDynamicColor(AppDynamicColor.Enabled)
        .updateAppTheme(AppTheme.System)
        .updateAppColorContrast(AppColorContrast.System)
        .updateAppLanguage(AppLanguage.System)
        .updateAppMeasurementSystem(AppMeasurementSystem.System)
)