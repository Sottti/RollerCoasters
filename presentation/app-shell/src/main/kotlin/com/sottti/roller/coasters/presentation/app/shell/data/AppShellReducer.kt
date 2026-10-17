package com.sottti.roller.coasters.presentation.app.shell.data

import com.sottti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.ResolvedDynamicColor
import com.sottti.roller.coasters.domain.settings.model.theme.ResolvedTheme
import com.sottti.roller.coasters.presentation.app.shell.model.AppShellActions
import com.sottti.roller.coasters.presentation.app.shell.model.AppShellActions.DestinationSelected
import com.sottti.roller.coasters.presentation.app.shell.model.AppShellActions.NoOp
import com.sottti.roller.coasters.presentation.app.shell.model.AppShellState

internal fun AppShellState.reduce(
    resolvedColorContrast: ResolvedColorContrast,
    resolvedDynamicColor: ResolvedDynamicColor,
    resolvedTheme: ResolvedTheme,
    stateMutationAction: AppShellActions,
): AppShellState = copy(
    colorContrast = resolvedColorContrast,
    dynamicColor = resolvedDynamicColor,
    theme = resolvedTheme,
    navigationBarItems = when (stateMutationAction) {
        is DestinationSelected -> navigationBarItems(stateMutationAction.destination)
        NoOp -> navigationBarItems()
    },
)
