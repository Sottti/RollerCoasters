package com.sottti.roller.coasters.presentation.design.system.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.sottti.roller.coasters.domain.settings.di.colorContrast.provideObserveResolvedColorContrast
import com.sottti.roller.coasters.domain.settings.di.dynamicColor.provideObserveDynamicColor
import com.sottti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.dimensions.DimensionsLocalProvider
import com.sottti.roller.coasters.utils.device.di.provideSdkFeatures

@Composable
public fun RollerCoastersTheme(
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val observeResolvedColorContrast = remember { provideObserveResolvedColorContrast(context) }
    val observeDynamicColor = remember { provideObserveDynamicColor(context) }
    val sdkFeatures = remember { provideSdkFeatures(context) }

    val dynamicColor = when (sdkFeatures.dynamicColorAvailable()) {
        true -> observeDynamicColor()
            .collectAsState(initial = true)
            .value

        else -> false
    }

    val resolvedColorContrast =
        observeResolvedColorContrast
            .invoke()
            .collectAsState(initial = ResolvedColorContrast.StandardContrast).value

    val colors = colors(
        colorContrast = resolvedColorContrast,
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