package com.sottti.roller.coasters.presentation.roller.coaster.details.fixtures

import com.sottti.roller.coasters.domain.fixtures.CITY
import com.sottti.roller.coasters.domain.fixtures.CLOSED_DATE
import com.sottti.roller.coasters.domain.fixtures.COASTER_FORMER_NAMES
import com.sottti.roller.coasters.domain.fixtures.COASTER_NAME
import com.sottti.roller.coasters.domain.fixtures.COASTER_NAME_THIRD
import com.sottti.roller.coasters.domain.fixtures.COST
import com.sottti.roller.coasters.domain.fixtures.COUNTRY
import com.sottti.roller.coasters.domain.fixtures.MANUFACTURER
import com.sottti.roller.coasters.domain.fixtures.MODEL
import com.sottti.roller.coasters.domain.fixtures.OPENED_DATE
import com.sottti.roller.coasters.domain.fixtures.OPERATIONAL_STATE_CURRENT
import com.sottti.roller.coasters.domain.fixtures.OPERATIONAL_STATE_FORMER
import com.sottti.roller.coasters.domain.fixtures.PARK_NAME
import com.sottti.roller.coasters.domain.fixtures.REGION
import com.sottti.roller.coasters.domain.fixtures.RELOCATIONS
import com.sottti.roller.coasters.domain.fixtures.RIDERS_PER_HOUR
import com.sottti.roller.coasters.domain.fixtures.STATE
import com.sottti.roller.coasters.presentation.roller.coaster.details.R
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsRollerCoasterViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsRow
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState.RollerCoasterIdentityViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState.RollerCoasterLocationViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState.RollerCoasterSpecsViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState.RollerCoasterStatusViewState

internal val rollerCoasterDetailsAverage =
    RollerCoasterDetailsRollerCoasterViewState(
        identity = identityAverageViewState(),
        location = locationAverageViewState(),
        specs = specsAverageViewState(),
        status = statusAverageViewState(),
    )

internal val rollerCoasterDetailsMaxedOut =
    RollerCoasterDetailsRollerCoasterViewState(
        identity = identityMaxedOutViewState(),
        location = locationMaxedOutViewState(),
        specs = specsMaxedOutViewState(),
        status = statusMaxedOutViewState(),
    )

private fun identityAverageViewState(): RollerCoasterIdentityViewState =
    RollerCoasterIdentityViewState(
        header = R.string.identity_header,
        name = RollerCoasterDetailsRow(
            trailing = COASTER_NAME,
            headline = R.string.identity_name,
        ),
        formerNames = null,
    )

private fun statusAverageViewState(): RollerCoasterStatusViewState =
    RollerCoasterStatusViewState(
        header = R.string.status_header,
        closedDate = null,
        current = currentStatus(),
        former = null,
        openedDate = openedDate(),
    )

private fun locationAverageViewState(): RollerCoasterLocationViewState =
    RollerCoasterLocationViewState(
        city = city(),
        country = country(),
        header = R.string.location_header,
        park = park(),
        region = region(),
        relocations = null,
        state = state(),
    )

private fun locationMaxedOutViewState(): RollerCoasterLocationViewState =
    RollerCoasterLocationViewState(
        city = city(),
        country = country(),
        header = R.string.location_header,
        park = park(),
        region = region(),
        relocations = relocations(),
        state = state(),
    )

private fun identityMaxedOutViewState(): RollerCoasterIdentityViewState =
    RollerCoasterIdentityViewState(
        header = R.string.identity_header,
        name = RollerCoasterDetailsRow(
            trailing = COASTER_NAME_THIRD,
            headline = R.string.identity_name,
        ),
        formerNames = formerNames(),
    )

private fun statusMaxedOutViewState(): RollerCoasterStatusViewState =
    RollerCoasterStatusViewState(
        header = R.string.status_header,
        closedDate = closedDate(),
        current = currentStatus(),
        former = formerStatus(),
        openedDate = openedDate(),
    )

private fun specsAverageViewState() =
    RollerCoasterSpecsViewState(
        capacity = capacity(),
        cost = cost(),
        header = R.string.specs_header,
        manufacturer = manufacturer(),
        model = model(),
    )

private fun specsMaxedOutViewState() =
    RollerCoasterSpecsViewState(
        capacity = capacity(),
        cost = cost(),
        header = R.string.specs_header,
        manufacturer = manufacturer(),
        model = model(),
    )

private fun cost(): RollerCoasterDetailsRow = RollerCoasterDetailsRow(
    trailing = "€$COST",
    headline = R.string.specs_cost,
)

private fun model(): RollerCoasterDetailsRow = RollerCoasterDetailsRow(
    trailing = MODEL,
    headline = R.string.specs_model,
)

private fun manufacturer(): RollerCoasterDetailsRow = RollerCoasterDetailsRow(
    trailing = MANUFACTURER,
    headline = R.string.specs_manufacturer,
)

private fun capacity(): RollerCoasterDetailsRow = RollerCoasterDetailsRow(
    trailing = "$RIDERS_PER_HOUR riders/hour",
    headline = R.string.specs_capacity,
)

private fun formerNames(): RollerCoasterDetailsRow = RollerCoasterDetailsRow(
    trailing = COASTER_FORMER_NAMES,
    headline = R.string.identity_former_names,
)

private fun relocations(): RollerCoasterDetailsRow = RollerCoasterDetailsRow(
    trailing = RELOCATIONS,
    headline = R.string.location_relocations,
)

private fun formerStatus(): RollerCoasterDetailsRow = RollerCoasterDetailsRow(
    trailing = OPERATIONAL_STATE_FORMER,
    headline = R.string.status_former,
)

private fun closedDate(): RollerCoasterDetailsRow = RollerCoasterDetailsRow(
    trailing = CLOSED_DATE,
    headline = R.string.status_closed_date,
)

private fun currentStatus(): RollerCoasterDetailsRow = RollerCoasterDetailsRow(
    trailing = OPERATIONAL_STATE_CURRENT,
    headline = R.string.status_current,
)

private fun openedDate(): RollerCoasterDetailsRow = RollerCoasterDetailsRow(
    trailing = OPENED_DATE,
    headline = R.string.status_opened_date,
)

private fun state(): RollerCoasterDetailsRow = RollerCoasterDetailsRow(
    trailing = STATE,
    headline = R.string.location_state,
)

private fun region(): RollerCoasterDetailsRow = RollerCoasterDetailsRow(
    trailing = REGION,
    headline = R.string.location_region,
)

private fun park(): RollerCoasterDetailsRow = RollerCoasterDetailsRow(
    trailing = PARK_NAME,
    headline = R.string.location_park,
)

private fun country(): RollerCoasterDetailsRow = RollerCoasterDetailsRow(
    trailing = COUNTRY,
    headline = R.string.location_country,
)

private fun city(): RollerCoasterDetailsRow = RollerCoasterDetailsRow(
    trailing = CITY,
    headline = R.string.location_city,
)