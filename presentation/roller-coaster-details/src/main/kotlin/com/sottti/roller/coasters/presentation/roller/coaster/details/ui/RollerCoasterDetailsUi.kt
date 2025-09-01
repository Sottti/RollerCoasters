package com.sottti.roller.coasters.presentation.roller.coaster.details.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.RollerCoastersTallPreview
import com.sottti.roller.coasters.presentation.roller.coaster.details.data.RollerCoasterDetailsViewModel
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsAction
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsAction.LoadUi
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsAction.ToggleFavourite
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsPreviewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsState

@Composable
public fun RollerCoasterDetailsUi(
    onBackNavigation: () -> Unit,
    rollerCoasterId: Int,
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
    LaunchedEffect(Unit) {
        viewModel.onAction(LoadUi)
    }
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
    RollerCoastersPreviewTheme {
        RollerCoasterDetailsUi(
            onAction = previewState.onAction,
            onBackNavigation = previewState.onBackNavigation,
            state = previewState.state,
        )
    }
}