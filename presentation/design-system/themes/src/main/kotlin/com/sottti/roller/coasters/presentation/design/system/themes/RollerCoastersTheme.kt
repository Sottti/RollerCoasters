package com.sottti.roller.coasters.presentation.design.system.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.sottti.roller.coasters.data.settings.di.provideSettingsRepository
import com.sottti.roller.coasters.presentation.design.system.colors.color.ColorsLocalProvider
import com.sottti.roller.coasters.presentation.design.system.colors.opacity.OpacityLocalProvider
import com.sottti.roller.coasters.presentation.design.system.dimensions.DimensionsLocalProvider
import com.sottti.roller.coasters.utils.device.accesibility.DeviceColorContrast
import com.sottti.roller.coasters.utils.device.di.provideDeviceAccessibility
import com.sottti.roller.coasters.utils.device.sdk.isDynamicColorEnabled
import kotlinx.coroutines.flow.map

@Composable
public fun RollerCoastersTheme(
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val dynamicColorInitialValue = true
    val isSystemInDarkTheme = isSystemInDarkTheme()
    val repository = remember { provideSettingsRepository(context) }
    val deviceColorContrast: DeviceColorContrast = remember {
        provideDeviceAccessibility(context).colorContrast
    }

    val dynamicColor = when {
        isDynamicColorEnabled() ->
            repository
                .observeDynamicColor()
                .collectAsState(initial = dynamicColorInitialValue)
                .value

        else -> false
    }

    val colorContrast = repository
        .observeColorContrast()
        .map { colorContrast -> colorContrast.toColorContrastTheme(deviceColorContrast) }
        .collectAsState(initial = deviceColorContrast.toColorContrastTheme())
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