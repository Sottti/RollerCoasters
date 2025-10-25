package com.sotti.roller.coasters.presentation.home.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sotti.roller.coasters.presentation.home.model.HomeActions
import com.sotti.roller.coasters.presentation.home.model.HomeState
import com.sotti.roller.coasters.presentation.navigation.NavigationDestination.Home
import com.sotti.roller.coasters.presentation.navigation.NavigationDestination.RollerCoasterDetails
import com.sotti.roller.coasters.presentation.navigation.NavigationDestination.Settings
import com.sotti.roller.coasters.presentation.roller.coaster.details.ui.RollerCoasterDetailsUi
import com.sotti.roller.coasters.presentation.settings.ui.SettingsUi

@Composable
internal fun HomeUi(
    state: HomeState,
    onAction: (HomeActions) -> Unit,

    ) {
    val rootNavController = rememberNavController()

    NavHost(
        navController = rootNavController,
        startDestination = Home,
    ) {
        composable<Home> {
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
