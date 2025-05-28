package com.sottti.roller.coasters.presentation.roller.coaster.details.data

import com.sottti.roller.coasters.domain.roller.coasters.model.Drop
import com.sottti.roller.coasters.domain.roller.coasters.model.Duration
import com.sottti.roller.coasters.domain.roller.coasters.model.GForce
import com.sottti.roller.coasters.domain.roller.coasters.model.Height
import com.sottti.roller.coasters.domain.roller.coasters.model.Length
import com.sottti.roller.coasters.domain.roller.coasters.model.MaxVertical
import com.sottti.roller.coasters.domain.roller.coasters.model.MultiTrackRide
import com.sottti.roller.coasters.domain.roller.coasters.model.Pictures
import com.sottti.roller.coasters.domain.roller.coasters.model.Ride
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.SingleTrackRide
import com.sottti.roller.coasters.domain.roller.coasters.model.Speed
import com.sottti.roller.coasters.domain.roller.coasters.model.Status
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sottti.roller.coasters.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.presentation.format.DateFormatter
import com.sottti.roller.coasters.presentation.format.DisplayUnitFormatter
import com.sottti.roller.coasters.presentation.roller.coaster.details.R
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.FavouriteIconState.Loaded
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsContentState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsImageState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsRollerCoasterState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsRow
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterIdentityState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterLocationCoordinatesState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterLocationState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterRideState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterStatusState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import java.util.Locale

internal fun MutableStateFlow<RollerCoasterDetailsState>.updateRollerCoaster(
    appLanguage: AppLanguage,
    dateFormatter: DateFormatter,
    rollerCoaster: RollerCoaster,
    systemLocale: Locale,
    displayUnitFormatter: DisplayUnitFormatter,
) {
    val formatContext = FormatContext(
        appLanguage = appLanguage,
        systemLocale = systemLocale,
        displayUnitFormatter = displayUnitFormatter,
    )
    update { currentState ->
        currentState.copy(
            topBar = currentState.topBar.copy(title = rollerCoaster.name.current.value),
            content = RollerCoasterDetailsContentState.Loaded(
                rollerCoaster = rollerCoaster.toRollerCoasterDetails(
                    dateFormatter = dateFormatter,
                    formatContext = formatContext,
                ),
            )
        )
    }
}

internal fun MutableStateFlow<RollerCoasterDetailsState>.updateIsFavouriteRollerCoaster(
    favourite: Boolean,
) {
    update { currentState ->
        currentState.copy(
            topBar = currentState.topBar.copy(
                favouriteIcon = when (favourite) {
                    true -> Loaded(Icons.Star.filled)
                    false -> Loaded(Icons.Star.outlined)
                }
            ),
        )
    }
}

private fun RollerCoaster.toRollerCoasterDetails(
    dateFormatter: DateFormatter,
    formatContext: FormatContext,
): RollerCoasterDetailsRollerCoasterState =
    RollerCoasterDetailsRollerCoasterState(
        images = pictures.toImagesState(),
        identity = toIdentityState(),
        location = toLocationState(),
        ride = specs.ride?.toRideState(formatContext),
        status = status.toStatusState(dateFormatter),
    )

private fun Pictures.toImagesState() =
    buildList {
        add(main)
        addAll(other)
    }.filterNotNull()
        .map { RollerCoasterDetailsImageState("", it.url) }

private fun RollerCoaster.toIdentityState() =
    RollerCoasterIdentityState(
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

private fun Status.toStatusState(
    dateFormatter: DateFormatter,
) = RollerCoasterStatusState(
    header = R.string.status_header,
    closedDate = closedDate?.date?.let {
        RollerCoasterDetailsRow(
            trailing = dateFormatter.format(it),
            headline = R.string.status_closed_date,
        )
    },
    current = current?.let {
        RollerCoasterDetailsRow(
            trailing = it.value,
            headline = R.string.status_current,
        )
    },
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
).takeIf { containsData() }

private fun RollerCoaster.toLocationState() =
    RollerCoasterLocationState(
        coordinates = location.coordinates?.let { coordinates ->
            RollerCoasterLocationCoordinatesState(
                longitude = coordinates.longitude.value,
                latitude = coordinates.latitude.value,
            )
        },
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
        mapMarkerTitle = name.current.value,
        relocations = location.relocations?.value?.let { relocations ->
            RollerCoasterDetailsRow(
                trailing = relocations,
                headline = R.string.location_relocations,
            )
        },
    )

private fun Ride.toRideState(
    formatContext: FormatContext,
): RollerCoasterRideState = when (this) {
    is SingleTrackRide -> toSingleTrackRideState(formatContext)
    is MultiTrackRide -> TODO()
}

private fun SingleTrackRide.toSingleTrackRideState(
    formatContext: FormatContext,
) =
    RollerCoasterRideState(
        header = R.string.ride_header,
        length = length?.let {
            RollerCoasterDetailsRow(
                trailing = it.let { length -> formatContext.formatLength(length) },
                headline = R.string.ride_length,
            )
        },
        height = height?.let {
            RollerCoasterDetailsRow(
                trailing = it.let { height -> formatContext.formatHeight(height) },
                headline = R.string.ride_height,
            )
        },
        drop = drop?.let {
            RollerCoasterDetailsRow(
                trailing = it.let { drop -> formatContext.formatDrop(drop) },
                headline = R.string.ride_drop,
            )
        },
        inversions = inversions?.let {
            RollerCoasterDetailsRow(
                trailing = it.value.toString(),
                headline = R.string.ride_inversions,
            )
        },
        maxVertical = maxVertical?.let {
            RollerCoasterDetailsRow(
                trailing = it.let { maxVertical -> formatContext.formatMaxVertical(maxVertical) },
                headline = R.string.ride_max_vertical,
            )
        },
        duration = duration?.let {
            RollerCoasterDetailsRow(
                trailing = it.let { duration -> formatContext.formatDuration(duration) },
                headline = R.string.ride_duration,
            )
        },
        gForce = gForce?.let {
            RollerCoasterDetailsRow(
                trailing = it.let { gForce -> formatContext.formatGForce(gForce) },
                headline = R.string.ride_g_force,
            )
        },
        speed = speed?.let {
            RollerCoasterDetailsRow(
                trailing = it.let { speed -> formatContext.formatSpeed(speed) },
                headline = R.string.ride_speed,
            )
        },
    )

private data class FormatContext(
    val appLanguage: AppLanguage,
    val systemLocale: Locale,
    val displayUnitFormatter: DisplayUnitFormatter,
)

private fun FormatContext.formatDrop(drop: Drop) =
    displayUnitFormatter.toDisplayFormat(appLanguage, systemLocale, drop)

private fun FormatContext.formatGForce(gForce: GForce) =
    displayUnitFormatter.toDisplayFormat(appLanguage, systemLocale, gForce)

private fun FormatContext.formatDuration(duration: Duration) =
    displayUnitFormatter.toDisplayFormat(systemLocale, duration)

private fun FormatContext.formatHeight(height: Height) =
    displayUnitFormatter.toDisplayFormat(appLanguage, systemLocale, height)

private fun FormatContext.formatLength(length: Length) =
    displayUnitFormatter.toDisplayFormat(appLanguage, systemLocale, length)

private fun FormatContext.formatSpeed(speed: Speed) =
    displayUnitFormatter.toDisplayFormat(appLanguage, systemLocale, speed)

private fun FormatContext.formatMaxVertical(maxVertical: MaxVertical) =
    displayUnitFormatter.toDisplayFormat(maxVertical)