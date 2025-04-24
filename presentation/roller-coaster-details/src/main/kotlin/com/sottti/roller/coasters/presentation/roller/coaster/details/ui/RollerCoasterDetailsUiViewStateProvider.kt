package com.sottti.roller.coasters.presentation.roller.coaster.details.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.presentation.roller.coaster.details.data.topBarInitialState
import com.sottti.roller.coasters.presentation.roller.coaster.details.fixtures.rollerCoasterDetails
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsContentState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsState

internal class RollerCoasterDetailsUiViewStateProvider :
    PreviewParameterProvider<RollerCoasterDetailsState> {
    override val values: Sequence<RollerCoasterDetailsState> =
        sequenceOf(
            loadingState,
            loadedState,
            errorState,
        )
}

private fun rollerCoasterDetailsState(
    content: RollerCoasterDetailsContentState,
) =
    RollerCoasterDetailsState(
        content = content,
        topBar = topBarInitialState(),
    )


private val loadingState = rollerCoasterDetailsState(
    content = RollerCoasterDetailsContentState.Loading,
)

private val loadedState = rollerCoasterDetailsState(
    content = RollerCoasterDetailsContentState.Loaded(rollerCoasterDetails),
)

private val errorState = rollerCoasterDetailsState(
    content = RollerCoasterDetailsContentState.Error,
)