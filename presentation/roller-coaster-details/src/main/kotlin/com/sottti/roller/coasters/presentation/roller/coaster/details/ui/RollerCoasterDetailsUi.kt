package com.sottti.roller.coasters.presentation.roller.coaster.details.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.roller.coasters.presentation.design.system.progress.indicators.ProgressIndicator
import com.sottti.roller.coasters.presentation.error.ErrorButton
import com.sottti.roller.coasters.presentation.error.ErrorUi
import com.sottti.roller.coasters.presentation.roller.coaster.details.data.RollerCoasterDetailsViewModel
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsContentState.Error
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsContentState.Loaded
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsContentState.Loading
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
    RollerCoasterDetailsUi(onBackNavigation, state)
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun RollerCoasterDetailsUi(
    onBackNavigation: () -> Unit,
    state: RollerCoasterDetailsState,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        topBar = { TopBar(onBackNavigation, scrollBehavior, state.topBar) }
    ) { paddingValues ->
        when (val content = state.content) {
            Error -> ErrorUi(modifier = Modifier.padding(paddingValues), button = ErrorButton {})
            Loading -> ProgressIndicator(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues))
            is Loaded -> RollerCoasterDetails(content.rollerCoaster, paddingValues)
        }
    }
}