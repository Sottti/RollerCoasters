package com.sottti.roller.coasters.presentation.design.system.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.sottti.roller.coasters.di.settings.colorContrast.provideObserveResolvedColorContrast
import com.sottti.roller.coasters.di.settings.dynamicColor.provideObserveResolvedDynamicColor
import com.sottti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.ResolvedDynamicColor
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.dimensions.DimensionsLocalProvider

@Composable
public fun RollerCoastersTheme(
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val resolvedDynamicColor =
        provideObserveResolvedDynamicColor(context).invoke()
            .collectAsState(initial = ResolvedDynamicColor(false))
            .value

    val resolvedColorContrast =
        provideObserveResolvedColorContrast(context).invoke()
            .collectAsState(initial = ResolvedColorContrast.StandardContrast).value

    val colors = colors(
        colorContrast = resolvedColorContrast,
        darkTheme = isSystemInDarkTheme(),
        dynamicColor = resolvedDynamicColor,
    )

    DimensionsLocalProvider {
        BaseTheme(
            colors = colors,
            content = content,
        )
    }
}