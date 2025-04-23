package com.sottti.roller.coasters.presentation.roller.coaster.details.ui

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.cuvva.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.roller.coaster.details.data.RollerCoasterDetailsViewModel

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
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
private fun RollerCoasterDetailsUi(
    onBackNavigation: () -> Unit,
    viewModel: RollerCoasterDetailsViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        topBar = { TopBar(onBackNavigation, scrollBehavior, state.topBar) }
    ) { paddingValues ->
        Text.Vanilla(text = "Roller Coaster Details")
    }
}