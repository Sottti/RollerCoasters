package com.sottti.roller.coasters.presentation.roller.coaster.details.fixtures

import com.sottti.roller.coasters.domain.fixtures.CITY
import com.sottti.roller.coasters.domain.fixtures.CLOSED_DATE
import com.sottti.roller.coasters.domain.fixtures.COASTER_FORMER_NAMES
import com.sottti.roller.coasters.domain.fixtures.COASTER_NAME
import com.sottti.roller.coasters.domain.fixtures.COASTER_NAME_THIRD
import com.sottti.roller.coasters.domain.fixtures.COUNTRY
import com.sottti.roller.coasters.domain.fixtures.DEGREES
import com.sottti.roller.coasters.domain.fixtures.DROP
import com.sottti.roller.coasters.domain.fixtures.DURATION_IN_MMSS
import com.sottti.roller.coasters.domain.fixtures.GFORCE
import com.sottti.roller.coasters.domain.fixtures.HEIGHT
import com.sottti.roller.coasters.domain.fixtures.INVERSIONS
import com.sottti.roller.coasters.domain.fixtures.LENGTH
import com.sottti.roller.coasters.domain.fixtures.OPENED_DATE
import com.sottti.roller.coasters.domain.fixtures.OPERATIONAL_STATE_CURRENT
import com.sottti.roller.coasters.domain.fixtures.OPERATIONAL_STATE_FORMER
import com.sottti.roller.coasters.domain.fixtures.PARK_NAME
import com.sottti.roller.coasters.domain.fixtures.RELOCATIONS
import com.sottti.roller.coasters.domain.fixtures.SPEED
import com.sottti.roller.coasters.domain.fixtures.coordinates
import com.sottti.roller.coasters.presentation.roller.coaster.details.R
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsRollerCoasterState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsRow
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterIdentityState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterLocationCoordinatesState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterLocationState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterRideState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterStatusState

internal val rollerCoasterDetailsMaxedOut =
    RollerCoasterDetailsRollerCoasterState(
        identity = identityMaxedOutState(),
        images = emptyList(),
        location = locationMaxedOutState(),
        ride = specsMaxedOutState(),
        status = statusMaxedOutState(),
    )

private fun locationMaxedOutState(): RollerCoasterLocationState =
    RollerCoasterLocationState(
        city = city(),
        coordinates = rollerCoasterLocationCoordinates(),
        country = country(),
        header = R.string.location_header,
        mapMarkerTitle = COASTER_NAME,
        park = park(),
        relocations = relocations(),
    )

private fun identityMaxedOutState(): RollerCoasterIdentityState =
    RollerCoasterIdentityState(
        header = R.string.identity_header,
        name = RollerCoasterDetailsRow(
            trailing = COASTER_NAME_THIRD,
            headline = R.string.identity_name,
        ),
        formerNames = formerNames(),
    )

private fun statusMaxedOutState(): RollerCoasterStatusState =
    RollerCoasterStatusState(
        header = R.string.status_header,
        closedDate = closedDate(),
        current = currentStatus(),
        former = formerStatus(),
        openedDate = openedDate(),
    )

private fun specsMaxedOutState() =
    RollerCoasterRideState(
        drop = drop(),
        duration = duration(),
        gForce = gForce(),
        header = R.string.ride_header,
        height = height(),
        inversions = inversions(),
        length = length(),
        maxVertical = maxVertical(),
        speed = speed(),
    )

private fun duration(): RollerCoasterDetailsRow = RollerCoasterDetailsRow(
    trailing = DURATION_IN_MMSS,
    headline = R.string.ride_duration,
)

private fun gForce(): RollerCoasterDetailsRow = RollerCoasterDetailsRow(
    trailing = GFORCE.toString(),
    headline = R.string.ride_g_force,
)

private fun height(): RollerCoasterDetailsRow = RollerCoasterDetailsRow(
    trailing = "$HEIGHT meters",
    headline = R.string.ride_height,
)

private fun length(): RollerCoasterDetailsRow = RollerCoasterDetailsRow(
    trailing = "$LENGTH meters",
    headline = R.string.ride_length,
)

private fun inversions(): RollerCoasterDetailsRow = RollerCoasterDetailsRow(
    trailing = INVERSIONS.toString(),
    headline = R.string.ride_inversions,
)

private fun maxVertical(): RollerCoasterDetailsRow = RollerCoasterDetailsRow(
    trailing = "$DEGREES°",
    headline = R.string.ride_max_vertical,
)

private fun speed(): RollerCoasterDetailsRow = RollerCoasterDetailsRow(
    trailing = "$SPEED km/h",
    headline = R.string.ride_speed,
)

private fun drop(): RollerCoasterDetailsRow = RollerCoasterDetailsRow(
    trailing = "$DROP meters",
    headline = R.string.ride_g_force,
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

private fun rollerCoasterLocationCoordinates(): RollerCoasterLocationCoordinatesState =
    RollerCoasterLocationCoordinatesState(
        latitude = coordinates().latitude.value,
        longitude = coordinates().longitude.value,
    )