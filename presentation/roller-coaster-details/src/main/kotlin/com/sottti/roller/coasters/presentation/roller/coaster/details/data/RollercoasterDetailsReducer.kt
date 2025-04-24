package com.sottti.roller.coasters.presentation.roller.coaster.details.data

import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.Status
import com.sottti.roller.coasters.presentation.format.DateFormatter
import com.sottti.roller.coasters.presentation.roller.coaster.details.R
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsContentState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsRow
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterIdentityViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterStatusViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal fun MutableStateFlow<RollerCoasterDetailsState>.updateRollerCoaster(
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
): RollerCoasterDetailsViewState =
    RollerCoasterDetailsViewState(
        identity = this.toIdentityViewState(),
        status = status.toStatusViewState(dateFormatter),
    )

private fun RollerCoaster.toIdentityViewState() =
    RollerCoasterIdentityViewState(
        header = R.string.identity_header,
        name = RollerCoasterDetailsRow(
            headline = name.current.value,
            overline = R.string.identity_overline_name,
            trailing = null,
        ),
        formerNames = name.former?.value?.let { formerNames ->
            RollerCoasterDetailsRow(
                headline = formerNames,
                overline = R.string.identity_overline_name,
                trailing = null,
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
                headline = dateFormatter.format(it),
                overline = R.string.status_overline_closed_date,
                trailing = null,
            )
        },
        current = RollerCoasterDetailsRow(
            headline = current.value,
            overline = R.string.status_overline_current,
            trailing = null,
        ),
        former = former?.value?.let { formerStatus ->
            RollerCoasterDetailsRow(
                headline = formerStatus,
                overline = R.string.status_overline_former,
                trailing = null,
            )
        },
        openedDate = openedDate?.date?.let {
            RollerCoasterDetailsRow(
                headline = dateFormatter.format(it),
                overline = R.string.status_overline_opened_date,
                trailing = null,
            )
        },
    )