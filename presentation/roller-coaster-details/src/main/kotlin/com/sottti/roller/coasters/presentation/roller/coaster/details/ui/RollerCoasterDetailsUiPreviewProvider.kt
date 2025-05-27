package com.sottti.roller.coasters.presentation.roller.coaster.details.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.presentation.roller.coaster.details.data.topBarInitialState
import com.sottti.roller.coasters.presentation.roller.coaster.details.fixtures.rollerCoasterDetailsMaxedOut
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsContentState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsContentState.Error
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsContentState.Loaded
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsContentState.Loading
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsPreviewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsState

internal class RollerCoasterDetailsUiPreviewProvider :
    PreviewParameterProvider<RollerCoasterDetailsPreviewState> {
    override val values: Sequence<RollerCoasterDetailsPreviewState> = sequenceOf(
        previewState(Loading),
        previewState(Loaded(rollerCoasterDetailsMaxedOut)),
        previewState(Error),
    )
}

internal fun previewState(
    content: RollerCoasterDetailsContentState,
) = RollerCoasterDetailsPreviewState(
    onAction = {},
    onBackNavigation = {},
    state = RollerCoasterDetailsState(
        content = content,
        topBar = topBarInitialState(),
    ),
)