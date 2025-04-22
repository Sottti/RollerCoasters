package com.sottti.roller.coasters.presentation.roller.coaster.details.data

import androidx.lifecycle.ViewModel
import com.sottti.roller.coasters.domain.roller.coasters.usecase.ObserveRollerCoaster
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class RollerCoasterDetailsViewModel @Inject constructor(
    private val observeRollerCoaster: ObserveRollerCoaster,
) : ViewModel() {
}