package com.sottti.roller.coasters.presentation.roller.coaster.details.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.roller.coasters.presentation.design.system.progress.indicators.ProgressIndicator
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.error.ErrorButton
import com.sottti.roller.coasters.presentation.error.ErrorUi
import com.sottti.roller.coasters.presentation.previews.LightDarkLongThemePreview
import com.sottti.roller.coasters.presentation.roller.coaster.details.data.RollerCoasterDetailsViewModel
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsAction
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsAction.ToggleFavourite
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsContentState.Error
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsContentState.Loaded
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsContentState.Loading
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
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        topBar = {
            TopBar(
                onBackNavigation = onBackNavigation,
                onToggleFavourite = { onAction(ToggleFavourite) },
                scrollBehavior = scrollBehavior,
                state = state.topBar,
            )
        }) { paddingValues ->
        when (val content = state.content) {
            Error -> ErrorUi(modifier = Modifier.padding(paddingValues), button = ErrorButton {})
            Loading -> ProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            )

            is Loaded -> RollerCoasterDetails(
                nestedScrollConnection = scrollBehavior.nestedScrollConnection,
                paddingValues = paddingValues,
                rollerCoaster = content.rollerCoaster,
            )
        }
    }
}

@Composable
@LightDarkLongThemePreview
internal fun RollerCoasterDetailsUiPreview(
    @PreviewParameter(RollerCoasterDetailsUiPreviewProvider::class)
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