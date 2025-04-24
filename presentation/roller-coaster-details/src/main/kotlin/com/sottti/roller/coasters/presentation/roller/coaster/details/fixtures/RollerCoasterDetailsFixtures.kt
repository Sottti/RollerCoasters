package com.sottti.roller.coasters.presentation.roller.coaster.details.fixtures

import com.sottti.roller.coasters.domain.fixtures.COASTER_NAME
import com.sottti.roller.coasters.domain.fixtures.OPENED_DATE
import com.sottti.roller.coasters.domain.fixtures.OPERATIONAL_STATE
import com.sottti.roller.coasters.presentation.roller.coaster.details.R
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsRow
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
        name = RollerCoasterDetailsRow(
            headline = COASTER_NAME,
            overline = R.string.identity_overline_name,
            trailing = null,
        ),
        formerNames = null,
    )

private fun statusViewState(): RollerCoasterStatusViewState =
    RollerCoasterStatusViewState(
        header = R.string.status_header,
        closedDate = null,
        current = RollerCoasterDetailsRow(
            headline = OPERATIONAL_STATE,
            overline = R.string.status_overline_current,
            trailing = null,
        ),
        former = null,
        openedDate = RollerCoasterDetailsRow(
            headline = OPENED_DATE,
            overline = R.string.status_overline_opened_date,
            trailing = null,
        ),
    )