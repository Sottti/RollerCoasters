package com.sottti.roller.coasters.presentation.roller.coaster.details.fixtures

import com.sottti.roller.coasters.domain.fixtures.CLOSED_DATE
import com.sottti.roller.coasters.domain.fixtures.COASTER_FORMER_NAMES
import com.sottti.roller.coasters.domain.fixtures.COASTER_NAME
import com.sottti.roller.coasters.domain.fixtures.COASTER_NAME_THIRD
import com.sottti.roller.coasters.domain.fixtures.OPENED_DATE
import com.sottti.roller.coasters.domain.fixtures.OPERATIONAL_STATE_CURRENT
import com.sottti.roller.coasters.domain.fixtures.OPERATIONAL_STATE_FORMER
import com.sottti.roller.coasters.presentation.roller.coaster.details.R
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsRollerCoasterViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsRow
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState.RollerCoasterIdentityViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState.RollerCoasterStatusViewState

internal val rollerCoasterDetailsAverage =
    RollerCoasterDetailsRollerCoasterViewState(
        identity = identityViewStateAverage(),
        status = statusViewStateAverage(),
    )

internal val rollerCoasterDetailsMaxedOut =
    RollerCoasterDetailsRollerCoasterViewState(
        identity = identityViewStateMaxedOut(),
        status = statusViewStateMaxedOut(),
    )

private fun identityViewStateAverage(): RollerCoasterIdentityViewState =
    RollerCoasterIdentityViewState(
        header = R.string.identity_header,
        name = RollerCoasterDetailsRow(
            trailing = COASTER_NAME,
            headline = R.string.identity_overline_name,
        ),
        formerNames = null,
    )

private fun statusViewStateAverage(): RollerCoasterStatusViewState =
    RollerCoasterStatusViewState(
        header = R.string.status_header,
        closedDate = null,
        current = currentStatus(),
        former = null,
        openedDate = openedDate(),
    )

private fun identityViewStateMaxedOut(): RollerCoasterIdentityViewState =
    RollerCoasterIdentityViewState(
        header = R.string.identity_header,
        name = RollerCoasterDetailsRow(
            trailing = COASTER_NAME_THIRD,
            headline = R.string.identity_overline_name,
        ),
        formerNames = RollerCoasterDetailsRow(
            trailing = COASTER_FORMER_NAMES,
            headline = R.string.identity_overline_former_names,
        ),
    )

private fun statusViewStateMaxedOut(): RollerCoasterStatusViewState =
    RollerCoasterStatusViewState(
        header = R.string.status_header,
        closedDate = closedDate(),
        current = currentStatus(),
        former = RollerCoasterDetailsRow(
            trailing = OPERATIONAL_STATE_FORMER,
            headline = R.string.status_overline_former,
        ),
        openedDate = openedDate(),
    )

private fun closedDate(): RollerCoasterDetailsRow = RollerCoasterDetailsRow(
    trailing = CLOSED_DATE,
    headline = R.string.status_overline_closed_date,
)

private fun currentStatus(): RollerCoasterDetailsRow = RollerCoasterDetailsRow(
    trailing = OPERATIONAL_STATE_CURRENT,
    headline = R.string.status_overline_current,
)

private fun openedDate(): RollerCoasterDetailsRow = RollerCoasterDetailsRow(
    trailing = OPENED_DATE,
    headline = R.string.status_overline_opened_date,
)