package com.sottti.roller.coasters.presentation.roller.coaster.details.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.sottti.roller.coasters.presentation.roller.coaster.details.data.RollerCoasterDetailsViewModel

@Composable
public fun RollerCoasterDetailsUi() {
    RollerCoasterDetailsUi(viewModel = hiltViewModel())
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun RollerCoasterDetailsUi(
    viewModel: RollerCoasterDetailsViewModel,
) {

}