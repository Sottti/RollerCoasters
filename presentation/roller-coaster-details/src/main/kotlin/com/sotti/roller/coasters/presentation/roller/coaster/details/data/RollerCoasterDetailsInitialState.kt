package com.sotti.roller.coasters.presentation.roller.coaster.details.data

import com.sotti.roller.coasters.presentation.design.system.icons.data.Icons
import com.sotti.roller.coasters.presentation.roller.coaster.details.model.FavouriteIconState
import com.sotti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsContentState
import com.sotti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsState
import com.sotti.roller.coasters.presentation.roller.coaster.details.model.TopBarState

internal val initialState: RollerCoasterDetailsState =
    RollerCoasterDetailsState(
        content = RollerCoasterDetailsContentState.Loading,
        topBar = TopBarState(
            favouriteIcon = FavouriteIconState.Loading,
            navigationIcon = Icons.Arrow.Back.filled,
            title = null,
        ),
    )
