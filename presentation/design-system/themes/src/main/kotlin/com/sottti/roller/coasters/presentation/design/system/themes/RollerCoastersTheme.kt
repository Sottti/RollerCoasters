package com.sottti.roller.coasters.presentation.design.system.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.sottti.roller.coasters.data.settings.di.provideSettingsRepository
import com.sottti.roller.coasters.presentation.design.system.colors.color.AppColorContrast
import com.sottti.roller.coasters.presentation.design.system.colors.color.ColorsLocalProvider
import com.sottti.roller.coasters.presentation.design.system.colors.opacity.OpacityLocalProvider
import com.sottti.roller.coasters.presentation.design.system.dimensions.DimensionsLocalProvider
import com.sottti.roller.coasters.utils.device.sdk.isDynamicColorEnabled
import kotlinx.coroutines.flow.map

@Composable
public fun RollerCoastersTheme(
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val dynamicColorInitialValue = true
    val colorContrastInitialValue = AppColorContrast.StandardContrast
    val isSystemInDarkTheme = isSystemInDarkTheme()
    val settingsRepository = remember { provideSettingsRepository(context) }

    val dynamicColor = when {
        isDynamicColorEnabled() ->
            settingsRepository
                .observeDynamicColor()
                .collectAsState(initial = dynamicColorInitialValue)
                .value

        else -> false
    }

    val colorContrast = settingsRepository
        .observeColorContrast()
        .map { it.toAppColorContrast(settingsRepository.getSystemColorContrast()) }
        .collectAsState(initial = colorContrastInitialValue)
        .value

    DimensionsLocalProvider {
        ColorsLocalProvider(
            colorContrast = colorContrast,
            darkTheme = dynamicColor,
            dynamicColor = isSystemInDarkTheme,
        ) {
            OpacityLocalProvider {
                BaseTheme(
                    colorContrast = colorContrast,
                    dynamicColor = dynamicColor,
                    darkTheme = isSystemInDarkTheme,
                    content = content,
                )
            }
        }
    }
}