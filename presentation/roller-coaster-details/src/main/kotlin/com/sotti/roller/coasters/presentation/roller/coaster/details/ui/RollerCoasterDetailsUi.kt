package com.sotti.roller.coasters.presentation.roller.coaster.details.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sotti.roller.coasters.presentation.design.system.themes.RollerCoastersTheme
import com.sotti.roller.coasters.presentation.previews.RollerCoastersTallPreview
import com.sotti.roller.coasters.presentation.roller.coaster.details.data.RollerCoasterDetailsViewModel
import com.sotti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsAction
import com.sotti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsAction.ToggleFavourite
import com.sotti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsPreviewState
import com.sotti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsState

@Composable
public fun RollerCoasterDetailsUi(
    onBackNavigation: () -> Unit,
) {
    RollerCoasterDetailsUi(
        onBackNavigation = onBackNavigation,
        viewModel = hiltViewModel(),
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun RollerCoasterDetailsUi(
    onBackNavigation: () -> Unit,
    viewModel: RollerCoasterDetailsViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    RollerCoasterDetailsUi(
        onAction = viewModel.onAction,
        onBackNavigation = onBackNavigation,
        state = state,
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun RollerCoasterDetailsUi(
    onAction: (RollerCoasterDetailsAction) -> Unit,
    onBackNavigation: () -> Unit,
    state: RollerCoasterDetailsState,
) {
    val content = state.content
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val topBarState = state.topBar
    val onToggleFavourite = { onAction(ToggleFavourite) }

    RollerCoasterDetailsContent(
        content = content,
        onBackNavigation = onBackNavigation,
        onToggleFavourite = onToggleFavourite,
        scrollBehavior = scrollBehavior,
        topBarState = topBarState,
    )
}

@Composable
@RollerCoastersTallPreview
internal fun RollerCoasterDetailsUiPreview(
    @PreviewParameter(RollerCoasterDetailsUiStateProvider::class)
    previewState: RollerCoasterDetailsPreviewState,
) {
    RollerCoastersTheme {
        RollerCoasterDetailsUi(
            onAction = previewState.onAction,
            onBackNavigation = previewState.onBackNavigation,
            state = previewState.state,
        )
    }
}
