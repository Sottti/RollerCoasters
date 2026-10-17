package com.sottti.roller.coasters.presentation.app.shell.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.sottti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.ResolvedDynamicColor
import com.sottti.roller.coasters.domain.settings.model.theme.ResolvedTheme
import com.sottti.roller.coasters.presentation.design.system.icons.model.IconState
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination

@Immutable
internal data class AppShellState(
    val colorContrast: ResolvedColorContrast,
    val dynamicColor: ResolvedDynamicColor,
    val theme: ResolvedTheme,
    val navigationBarItems: AppShellNavigationBarState,
)

@Immutable
internal data class AppShellNavigationBarState(
    val items: List<AppShellNavigationBarItemState>,
    val selectedItem: NavigationDestination,
)

@Immutable
internal data class AppShellNavigationBarItemState(
    @StringRes val labelResId: Int,
    val destination: NavigationDestination,
    val icon: IconState,
)
