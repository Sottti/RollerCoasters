package com.sottti.roller.coasters.presentation.design.system.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.sottti.roller.coasters.data.settings.di.provideSettingsRepository
import com.sottti.roller.coasters.domain.settings.model.Theme
import com.sottti.roller.coasters.presentation.design.system.dimensions.resolution.DimensionsLocalProvider
import kotlinx.coroutines.flow.map

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

    val darkTheme by repository
        .observeTheme()
        .map { theme -> theme.toDarkTheme(isSystemInDarkTheme) }
        .collectAsState(initial = isSystemInDarkTheme)

    DimensionsLocalProvider {
        BaseTheme(
            dynamicColor = dynamicColor,
            darkTheme = darkTheme,
            content = content,
        )
    }
}

private fun Theme.toDarkTheme(
    isSystemInDarkTheme: Boolean,
) = when (this) {
    Theme.DarkTheme -> true
    Theme.LightTheme -> false
    Theme.SystemTheme -> isSystemInDarkTheme
}