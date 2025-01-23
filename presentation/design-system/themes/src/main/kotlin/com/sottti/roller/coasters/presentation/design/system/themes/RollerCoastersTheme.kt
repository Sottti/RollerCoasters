package com.sottti.roller.coasters.presentation.design.system.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.sottti.roller.coasters.data.settings.di.provideSettingsRepository
import com.sottti.roller.coasters.presentation.design.system.dimensions.resolution.DimensionsLocalProvider

@Composable
public fun RollerCoastersTheme(
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val dynamicColorInitialValue = true
    val isSystemInDarkTheme = isSystemInDarkTheme()
    val repository = remember { provideSettingsRepository(context) }

    val dynamicColor by repository
        .observeDynamicColor()
        .collectAsState(initial = dynamicColorInitialValue)

    DimensionsLocalProvider {
        BaseTheme(
            dynamicColor = dynamicColor,
            darkTheme = isSystemInDarkTheme,
            content = content,
        )
    }
}