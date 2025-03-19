package com.sottti.roller.coasters.presentation.design.system.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.sottti.roller.coasters.di.settings.provideGetSystemColorContrast
import com.sottti.roller.coasters.di.settings.provideObserveColorContrast
import com.sottti.roller.coasters.di.settings.provideObserveDynamicColor
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
    val getSystemColorContrast = remember { provideGetSystemColorContrast(context) }
    val observeColorContrast = remember { provideObserveColorContrast(context) }
    val observeDynamicColor = remember { provideObserveDynamicColor(context) }
    val sdkFeatures = remember { provideSdkFeatures(context) }

    val dynamicColor = when (sdkFeatures.dynamicColorAvailable()) {
        true -> observeDynamicColor()
            .collectAsState(initial = true)
            .value

        else -> false
    }

    val colorContrast = observeColorContrast()
        .map { colorContrast ->
            colorContrast.toAppColorContrast(getSystemColorContrast())
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