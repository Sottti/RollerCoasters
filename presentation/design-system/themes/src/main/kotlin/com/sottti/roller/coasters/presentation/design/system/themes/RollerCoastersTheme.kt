package com.sottti.roller.coasters.presentation.design.system.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.sottti.roller.coasters.data.settings.di.provideSettingsRepository
import com.sottti.roller.coasters.presentation.design.system.colors.color.AppColorContrast
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.dimensions.DimensionsLocalProvider
import com.sottti.roller.coasters.utils.device.di.provideSdkFeatures
import kotlinx.coroutines.flow.map

@Composable
public fun RollerCoastersTheme(
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val sdkFeatures = remember { provideSdkFeatures(context) }
    val settingsRepository = remember { provideSettingsRepository(context) }

    val dynamicColor = when (sdkFeatures.dynamicColorAvailable()) {
        true -> settingsRepository
            .observeDynamicColor()
            .collectAsState(initial = true)
            .value

        else -> false
    }

    val colorContrast =
        settingsRepository
            .observeColorContrast()
            .map { colorContrast ->
                colorContrast.toAppColorContrast(settingsRepository.getSystemColorContrast())
            }
            .collectAsState(initial = AppColorContrast.StandardContrast).value

    val colors = colors(
        colorContrast = colorContrast,
        darkTheme = isSystemInDarkTheme(),
        dynamicColor = dynamicColor,
        sdkFeatures = sdkFeatures,
    )

    DimensionsLocalProvider {
        BaseTheme(
            colors = colors,
            content = content,
        )
    }
}