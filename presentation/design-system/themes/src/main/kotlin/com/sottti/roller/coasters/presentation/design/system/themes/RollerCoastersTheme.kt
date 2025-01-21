package com.sottti.roller.coasters.presentation.design.system.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.sottti.roller.coasters.data.settings.di.provideSettingsRepository
import com.sottti.roller.coasters.presentation.design.system.dimensions.resolution.DimensionsLocalProvider

@Composable
public fun RollerCoastersTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val repository = provideSettingsRepository(LocalContext.current)
    val dynamicColor by repository.getDynamicColor().collectAsState(initial = true)
    DimensionsLocalProvider {
        BaseTheme(
            dynamicColor = dynamicColor,
            darkTheme = darkTheme,
            content = content,
        )
    }
}