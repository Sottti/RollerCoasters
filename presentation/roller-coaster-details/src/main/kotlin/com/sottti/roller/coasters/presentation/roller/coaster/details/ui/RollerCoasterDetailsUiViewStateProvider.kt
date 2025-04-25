package com.sottti.roller.coasters.presentation.roller.coaster.details.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.presentation.roller.coaster.details.data.topBarInitialState
import com.sottti.roller.coasters.presentation.roller.coaster.details.fixtures.rollerCoasterDetailsAverage
import com.sottti.roller.coasters.presentation.roller.coaster.details.fixtures.rollerCoasterDetailsMaxedOut
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsContentState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsViewState

internal class RollerCoasterDetailsUiViewStateProvider :
    PreviewParameterProvider<RollerCoasterDetailsViewState> {
    override val values: Sequence<RollerCoasterDetailsViewState> =
        sequenceOf(
            //loadingState,
            //loadedStateAverageRollerCoaster,
            loadedStateMaxedOutRollerCoaster,
            //errorState,
        )
}

private fun rollerCoasterDetailsState(
    content: RollerCoasterDetailsContentState,
) =
    RollerCoasterDetailsViewState(
        content = content,
        topBar = topBarInitialState(),
    )


private val loadingState = rollerCoasterDetailsState(
    content = RollerCoasterDetailsContentState.Loading,
)

private val loadedStateAverageRollerCoaster = rollerCoasterDetailsState(
    content = RollerCoasterDetailsContentState.Loaded(rollerCoasterDetailsAverage),
)

private val loadedStateMaxedOutRollerCoaster = rollerCoasterDetailsState(
    content = RollerCoasterDetailsContentState.Loaded(rollerCoasterDetailsMaxedOut),
)

private val errorState = rollerCoasterDetailsState(
    content = RollerCoasterDetailsContentState.Error,
)