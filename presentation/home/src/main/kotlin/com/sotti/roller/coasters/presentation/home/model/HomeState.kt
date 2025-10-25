package com.sotti.roller.coasters.presentation.home.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.sotti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast
import com.sotti.roller.coasters.domain.settings.model.dynamicColor.ResolvedDynamicColor
import com.sotti.roller.coasters.domain.settings.model.theme.ResolvedTheme
import com.sotti.roller.coasters.presentation.design.system.icons.model.IconState
import com.sotti.roller.coasters.presentation.navigation.NavigationDestination

@Immutable
internal data class HomeState(
    val colorContrast: ResolvedColorContrast,
    val dynamicColor: ResolvedDynamicColor,
    val theme: ResolvedTheme,
    val navigationBarItems: HomeNavigationBarState,
)

@Immutable
internal data class HomeNavigationBarState(
    val items: List<HomeNavigationBarItemState>,
    val selectedItem: NavigationDestination,
)

@Immutable
internal data class HomeNavigationBarItemState(
    @StringRes val labelResId: Int,
    val destination: NavigationDestination,
    val icon: IconState,
)
