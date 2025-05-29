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
    override val values: Sequence<SettingsPreviewState> = sequence {
        val dynamicColors = AppDynamicColor.entries.toList()
        val themes = AppTheme.entries.toList()
        val contrasts = AppColorContrast.entries.toList()
        val languages = AppLanguage.entries.toList()
        val measurements = AppMeasurementSystem.entries.toList()

        for (i in 0..3) {
            yield(
                previewState(
                    state = initialState(true)
                        .updateDynamicColor(dynamicColors.atLeast(i))
                        .updateAppTheme(themes.atLeast(i))
                        .updateAppColorContrast(contrasts.atLeast(i))
                        .updateAppLanguage(languages.atLeast(i))
                        .updateAppMeasurementSystem(measurements.atLeast(i)),
                )
            )
        }
    }
}