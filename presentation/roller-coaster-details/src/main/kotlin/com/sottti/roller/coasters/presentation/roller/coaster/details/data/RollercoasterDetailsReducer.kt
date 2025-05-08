package com.sottti.roller.coasters.presentation.roller.coaster.details.data

import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.Specs
import com.sottti.roller.coasters.domain.roller.coasters.model.Status
import com.sottti.roller.coasters.presentation.format.DateFormatter
import com.sottti.roller.coasters.presentation.roller.coaster.details.R
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsContentState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsRollerCoasterViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsRow
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState.RollerCoasterIdentityViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState.RollerCoasterLocationViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState.RollerCoasterSpecsViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState.RollerCoasterStatusViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal fun MutableStateFlow<RollerCoasterDetailsViewState>.updateRollerCoaster(
    dateFormatter: DateFormatter,
    rollerCoaster: RollerCoaster,
) {
    update { currentState ->
        currentState.copy(
            content = RollerCoasterDetailsContentState.Loaded(
                rollerCoaster = rollerCoaster.toRollerCoasterDetails(dateFormatter),
            )
        )
    }
}

private fun RollerCoaster.toRollerCoasterDetails(
    dateFormatter: DateFormatter,
): RollerCoasterDetailsRollerCoasterViewState =
    RollerCoasterDetailsRollerCoasterViewState(
        identity = toIdentityViewState(),
        location = toLocationViewState(),
        specs = specs.toSpecsViewState(),
        status = status.toStatusViewState(dateFormatter),
    )

private fun RollerCoaster.toIdentityViewState() =
    RollerCoasterIdentityViewState(
        header = R.string.identity_header,
        name = RollerCoasterDetailsRow(
            headline = R.string.identity_name,
            trailing = name.current.value,
        ),
        formerNames = name.former?.value?.let { formerNames ->
            RollerCoasterDetailsRow(
                headline = R.string.identity_former_names,
                trailing = formerNames,
            )
        }
    )

private fun Status.toStatusViewState(
    dateFormatter: DateFormatter,
) =
    RollerCoasterStatusViewState(
        header = R.string.status_header,
        closedDate = closedDate?.date?.let {
            RollerCoasterDetailsRow(
                trailing = dateFormatter.format(it),
                headline = R.string.status_closed_date,
            )
        },
        current = RollerCoasterDetailsRow(
            trailing = current.value,
            headline = R.string.status_current,
        ),
        former = former?.value?.let { formerStatus ->
            RollerCoasterDetailsRow(
                trailing = formerStatus,
                headline = R.string.status_former,
            )
        },
        openedDate = openedDate?.date?.let {
            RollerCoasterDetailsRow(
                trailing = dateFormatter.format(it),
                headline = R.string.status_opened_date,
            )
        },
    )

private fun Specs.toSpecsViewState() =
    RollerCoasterSpecsViewState(
        header = R.string.specs_header,
        capacity = capacity?.let { capacity ->
            RollerCoasterDetailsRow(
                trailing = capacity.ridersPerHour.value.toString(),
                headline = R.string.specs_capacity,
            )
        },
        cost = RollerCoasterDetailsRow(
            trailing = cost.toString(),
            headline = R.string.specs_cost,
        ),
        manufacturer = manufacturer?.let { manufacturer ->
            RollerCoasterDetailsRow(
                trailing = manufacturer.value,
                headline = R.string.specs_manufacturer,
            )
        },
        model = RollerCoasterDetailsRow(
            trailing = model.value,
            headline = R.string.specs_model,
        ),
    )

private fun RollerCoaster.toLocationViewState() =
    RollerCoasterLocationViewState(
        city = RollerCoasterDetailsRow(
            trailing = location.city.value,
            headline = R.string.location_city,
        ),
        country = RollerCoasterDetailsRow(
            trailing = location.country.value,
            headline = R.string.location_country,
        ),
        header = R.string.location_header,
        park = RollerCoasterDetailsRow(
            trailing = park.name.value,
            headline = R.string.location_park,
        ),
        region = RollerCoasterDetailsRow(
            trailing = location.region.value,
            headline = R.string.location_region,
        ),
        relocations = location.relocations?.value?.let { relocations ->
            RollerCoasterDetailsRow(
                trailing = relocations,
                headline = R.string.location_relocations,
            )
        },
        state = RollerCoasterDetailsRow(
            trailing = location.state.value,
            headline = R.string.location_state,
        ),
    )