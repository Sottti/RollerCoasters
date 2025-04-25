package com.sottti.roller.coasters.presentation.roller.coaster.details.data

import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.Status
import com.sottti.roller.coasters.presentation.format.DateFormatter
import com.sottti.roller.coasters.presentation.roller.coaster.details.R
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsContentState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsRow
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsRollerCoasterViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState.RollerCoasterIdentityViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState.RollerCoasterStatusViewState
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
        identity = this.toIdentityViewState(),
        status = status.toStatusViewState(dateFormatter),
    )

private fun RollerCoaster.toIdentityViewState() =
    RollerCoasterIdentityViewState(
        header = R.string.identity_header,
        name = RollerCoasterDetailsRow(
            headline = R.string.identity_overline_name,
            trailing = name.current.value,
        ),
        formerNames = name.former?.value?.let { formerNames ->
            RollerCoasterDetailsRow(
                headline = R.string.identity_overline_former_names,
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
                headline = R.string.status_overline_closed_date,
            )
        },
        current = RollerCoasterDetailsRow(
            trailing = current.value,
            headline = R.string.status_overline_current,
        ),
        former = former?.value?.let { formerStatus ->
            RollerCoasterDetailsRow(
                trailing = formerStatus,
                headline = R.string.status_overline_former,
            )
        },
        openedDate = openedDate?.date?.let {
            RollerCoasterDetailsRow(
                trailing = dateFormatter.format(it),
                headline = R.string.status_overline_opened_date,
            )
        },
    )