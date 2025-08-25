package com.sottti.roller.coasters.presentation.roller.coaster.details.data

import com.sottti.roller.coasters.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.FavouriteIconState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsContentState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.TopBarState

internal fun initialState(): RollerCoasterDetailsState =
    RollerCoasterDetailsState(
        content = RollerCoasterDetailsContentState.Loading,
        topBar = TopBarState(
            favouriteIcon = FavouriteIconState.Loading,
            navigationIcon = Icons.Arrow.Back.filled,
            title = null,
        ),
    )