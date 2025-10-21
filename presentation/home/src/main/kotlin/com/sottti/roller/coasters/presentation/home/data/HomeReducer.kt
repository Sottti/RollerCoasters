package com.sottti.roller.coasters.presentation.home.data

import com.sottti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.ResolvedDynamicColor
import com.sottti.roller.coasters.domain.settings.model.theme.ResolvedTheme
import com.sottti.roller.coasters.presentation.home.model.HomeActions
import com.sottti.roller.coasters.presentation.home.model.HomeActions.DestinationSelected
import com.sottti.roller.coasters.presentation.home.model.HomeActions.NoOp
import com.sottti.roller.coasters.presentation.home.model.HomeState

internal fun HomeState.reduce(
    resolvedColorContrast: ResolvedColorContrast,
    resolvedDynamicColor: ResolvedDynamicColor,
    resolvedTheme: ResolvedTheme,
    stateMutationAction: HomeActions,
): HomeState = copy(
    colorContrast = resolvedColorContrast,
    dynamicColor = resolvedDynamicColor,
    theme = resolvedTheme,
    navigationBarItems = when (stateMutationAction) {
        is DestinationSelected -> navigationBarItems(stateMutationAction.destination)
        NoOp -> navigationBarItems()
    },
)
