package com.sottti.roller.coasters.presentation.roller.coaster.details.data

import co.cuvva.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsContentState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.TopBarState

internal fun initialState(): RollerCoasterDetailsState =
    RollerCoasterDetailsState(
        content = RollerCoasterDetailsContentState.Loading,
        topBar = topBarInitialState()
    )

private fun topBarInitialState(): TopBarState = TopBarState(
    title = null,
    icon = Icons.ArrowBack.Filled,
)