package com.sottti.roller.coasters.presentation.roller.coaster.details.fixtures

import com.sottti.roller.coasters.domain.fixtures.COASTER_NAME
import com.sottti.roller.coasters.domain.fixtures.OPENED_DATE
import com.sottti.roller.coasters.domain.fixtures.OPERATIONAL_STATE
import com.sottti.roller.coasters.presentation.roller.coaster.details.R
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterIdentityViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterStatusViewState

internal val rollerCoasterDetails =
    RollerCoasterDetailsViewState(
        identity = identityViewState(),
        status = statusViewState(),
    )

private fun identityViewState(): RollerCoasterIdentityViewState =
    RollerCoasterIdentityViewState(
        header = R.string.identity_header,
        name = COASTER_NAME,
        formerNames = null,
    )

private fun statusViewState(): RollerCoasterStatusViewState =
    RollerCoasterStatusViewState(
        header = R.string.status_header,
        closedDate = null,
        current = OPERATIONAL_STATE,
        former = null,
        openedDate = OPENED_DATE,
    )