package com.sottti.roller.coasters.presentation.home.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination.Home
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination.RollerCoasterDetails
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination.Settings
import com.sottti.roller.coasters.presentation.roller.coaster.details.ui.RollerCoasterDetailsUi
import com.sottti.roller.coasters.presentation.settings.ui.SettingsUi

@Composable
internal fun HomeUi() {
    val rootNavController = rememberNavController()

    NavHost(
        navController = rootNavController,
        startDestination = Home,
    ) {
        composable<Home> {
            NavigationBar(
                onNavigateToSettings = { rootNavController.navigate(Settings) },
                onNavigateToRollerCoaster = { rollerCoasterId ->
                    rootNavController.navigate(RollerCoasterDetails(rollerCoasterId))
                }
            )
        }
        composable<Settings> {
            SettingsUi(onBackNavigation = { rootNavController.popBackStack() })
        }

        composable<RollerCoasterDetails> { backStackEntry ->
            val rollerCoasterDetails: RollerCoasterDetails = backStackEntry.toRoute()
            RollerCoasterDetailsUi(
                rollerCoasterId = rollerCoasterDetails.rollerCoasterId,
                onBackNavigation = { rootNavController.popBackStack() }
            )
        }
    }
}