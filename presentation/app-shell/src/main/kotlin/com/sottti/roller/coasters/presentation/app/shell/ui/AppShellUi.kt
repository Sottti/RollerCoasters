package com.sottti.roller.coasters.presentation.app.shell.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sottti.roller.coasters.presentation.app.shell.model.AppShellActions
import com.sottti.roller.coasters.presentation.app.shell.model.AppShellState
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination.AppShell
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination.RollerCoasterDetails
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination.Settings
import com.sottti.roller.coasters.presentation.roller.coaster.details.ui.RollerCoasterDetailsUi
import com.sottti.roller.coasters.presentation.settings.ui.SettingsUi

@Composable
internal fun AppShellUi(
    state: AppShellState,
    onAction: (AppShellActions) -> Unit,
) {
    val rootNavController = rememberNavController()

    NavHost(
        navController = rootNavController,
        startDestination = AppShell,
    ) {
        composable<AppShell> {
            NavigationBar(
                onAction = onAction,
                onNavigateToRollerCoaster = { rollerCoasterId ->
                    rootNavController.navigate(
                        RollerCoasterDetails(rollerCoasterId)
                    )
                },
                onNavigateToSettings = { rootNavController.navigate(Settings) },
                state = state,
            )
        }
        composable<Settings> {
            SettingsUi(onBackNavigation = { rootNavController.popBackStack() })
        }

        composable<RollerCoasterDetails> {
            RollerCoasterDetailsUi(onBackNavigation = { rootNavController.popBackStack() })
        }
    }
}
