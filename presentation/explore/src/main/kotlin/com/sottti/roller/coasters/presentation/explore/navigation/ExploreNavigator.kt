package com.sottti.roller.coasters.presentation.explore.navigation

import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoasterId

public interface ExploreNavigator {
    public fun navigateBack()
    public fun navigateToRollerCoasterDetails(rollerCoasterId: RollerCoasterId)
    public fun navigateToSettings()
}