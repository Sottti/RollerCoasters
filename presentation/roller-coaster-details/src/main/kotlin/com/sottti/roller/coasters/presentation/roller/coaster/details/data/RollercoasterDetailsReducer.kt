package com.sottti.roller.coasters.presentation.roller.coaster.details.data

import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsContentState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal fun MutableStateFlow<RollerCoasterDetailsState>.updateRollerCoaster(
    rollerCoaster: RollerCoaster,
) {
    update { currentState ->
        currentState.copy(
            content = RollerCoasterDetailsContentState.Loaded(
                rollerCoaster = rollerCoaster.toRollerCoasterDetails(),
            )
        )
    }
}