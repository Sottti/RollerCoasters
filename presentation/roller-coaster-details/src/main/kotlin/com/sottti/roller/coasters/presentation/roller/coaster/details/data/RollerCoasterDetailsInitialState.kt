package com.sottti.roller.coasters.presentation.roller.coaster.details.data

import com.sotti.roller.coasters.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.FavouriteIconState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsContentState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.TopBarState

internal fun initialState(): RollerCoasterDetailsViewState =
    RollerCoasterDetailsViewState(
        content = RollerCoasterDetailsContentState.Loading,
        topBar = topBarInitialState()
    )

internal fun topBarInitialState(): TopBarState = TopBarState(
    favouriteIcon = FavouriteIconState.Loading,
    navigationIcon = Icons.ArrowBack.filled,
    title = null,
)