package com.sottti.roller.coasters.presentation.settings.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme
import com.sottti.roller.coasters.presentation.settings.data.initialState
import com.sottti.roller.coasters.presentation.settings.data.mapper.toPresentationModel
import com.sottti.roller.coasters.presentation.settings.data.reducer.showAppColorContrastPicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.showAppLanguagePicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.showAppMeasurementSystemPicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.showAppThemePicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateDynamicColor
import com.sottti.roller.coasters.presentation.settings.model.SettingsPreviewState

@OptIn(ExperimentalMaterial3Api::class)
internal class SettingsUiPickersStateProvider : PreviewParameterProvider<SettingsPreviewState> {
    override val values: Sequence<SettingsPreviewState> = sequence {
        val appColorContrasts = AppColorContrast.entries
        val appLanguages = AppLanguage.entries
        val appMeasurementSystems = AppMeasurementSystem.entries
        val appThemes = AppTheme.entries
        val booleanOptions = listOf(true, false)
        val baseState = initialState(dynamicColorAvailable = false)

        for (language in appLanguages) {
            yield(
                previewState(
                    state = baseState.showAppLanguagePicker(
                        selectedAppLanguage = language
                    )
                )
            )
        }

        for (lightDarkAppThemingAvailable in booleanOptions) {
            for (theme in appThemes) {
                yield(
                    previewState(
                        state = baseState.showAppThemePicker(
                            lightDarkAppThemingAvailable = lightDarkAppThemingAvailable,
                            selectedAppTheme = theme.toPresentationModel(selected = true),
                        )
                    )
                )
            }
        }

        for (measurement in appMeasurementSystems) {
            yield(
                previewState(
                    state = baseState.showAppMeasurementSystemPicker(
                        selectedAppMeasurementSystem = measurement,
                    )
                )
            )
        }

        for (appColorContrastAvailable in booleanOptions) {
            for (contrast in appColorContrasts) {
                yield(
                    previewState(
                        state = baseState
                            .showAppColorContrastPicker(
                                selectedAppColorContrast = contrast,
                                appColorContrastAvailable = appColorContrastAvailable,
                            )
                    )

                )
            }
            yield(
                previewState(
                    state = initialState(dynamicColorAvailable = true)
                        .updateDynamicColor(AppDynamicColor.Enabled)
                        .showAppColorContrastPicker(
                            selectedAppColorContrast = AppColorContrast.System,
                            appColorContrastAvailable = appColorContrastAvailable,
                        )
                )

            )
        }
    }
}