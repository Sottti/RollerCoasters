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
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateAppColorContrast
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateAppLanguage
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateAppMeasurementSystem
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateAppTheme
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateDynamicColor
import com.sottti.roller.coasters.presentation.settings.model.SettingsPreviewState
import kotlin.math.min

@OptIn(ExperimentalMaterial3Api::class)
internal class SettingsUiStateProvider : PreviewParameterProvider<SettingsPreviewState> {

    override val values: Sequence<SettingsPreviewState> = sequence {
        val dynamicColors = AppDynamicColor.entries.toList()
        val themes = AppTheme.entries.toList()
        val contrasts = AppColorContrast.entries.toList()
        val languages = AppLanguage.entries.toList()
        val measurements = AppMeasurementSystem.entries.toList()
        val themingAvailabilityOptions = listOf(true, false)
        val baseStateWithoutDynamicColor = initialState(dynamicColorAvailable = false)

        repeat(4) { index ->
            yield(
                previewWithAllSettings(
                    index = index,
                    dynamicColors = dynamicColors,
                    themes = themes,
                    contrasts = contrasts,
                    languages = languages,
                    measurements = measurements
                )
            )
        }

        languages.forEach { language ->
            yield(
                previewState(
                    state = baseStateWithoutDynamicColor.showAppLanguagePicker(
                        selectedAppLanguage = language,
                    ),
                ),
            )
        }

        themingAvailabilityOptions.forEach { appThemingAvailable ->
            themes.forEach { theme ->
                yield(
                    previewState(
                        state = baseStateWithoutDynamicColor.showAppThemePicker(
                            lightDarkAppThemingAvailable = appThemingAvailable,
                            selectedAppTheme = theme.toPresentationModel(selected = true),
                        ),
                    ),
                )
            }
        }

        measurements.forEach { measurement ->
            yield(
                previewState(
                    state = baseStateWithoutDynamicColor.showAppMeasurementSystemPicker(
                        selectedAppMeasurementSystem = measurement,
                    ),
                ),
            )
        }

        contrasts.forEach { contrast ->
            themingAvailabilityOptions.forEach { appColorContrastAvailable ->
                yield(
                    previewState(
                        state = baseStateWithoutDynamicColor.showAppColorContrastPicker(
                            selectedAppColorContrast = contrast,
                            appColorContrastAvailable = appColorContrastAvailable,
                        ),
                    ),
                )
            }
        }

        themingAvailabilityOptions.forEach { appColorContrastAvailable ->
            yield(
                previewState(
                    state = initialState(dynamicColorAvailable = true)
                        .updateDynamicColor(AppDynamicColor.Enabled)
                        .showAppColorContrastPicker(
                            selectedAppColorContrast = AppColorContrast.System,
                            appColorContrastAvailable = appColorContrastAvailable,
                        ),
                ),
            )
        }
    }

    private fun <T> List<T>.atLeast(index: Int): T = this[min(index, lastIndex)]

    private fun previewWithAllSettings(
        index: Int,
        dynamicColors: List<AppDynamicColor>,
        themes: List<AppTheme>,
        contrasts: List<AppColorContrast>,
        languages: List<AppLanguage>,
        measurements: List<AppMeasurementSystem>,
    ): SettingsPreviewState = previewState(
        state = initialState(dynamicColorAvailable = true)
            .updateDynamicColor(dynamicColors.atLeast(index))
            .updateAppTheme(themes.atLeast(index))
            .updateAppColorContrast(contrasts.atLeast(index))
            .updateAppLanguage(languages.atLeast(index))
            .updateAppMeasurementSystem(measurements.atLeast(index)),
    )
}
