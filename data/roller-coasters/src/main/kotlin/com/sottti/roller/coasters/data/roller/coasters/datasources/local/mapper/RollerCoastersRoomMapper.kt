package com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper

import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.AmusementParkRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.CoordinatesRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.LocationRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.PictureRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.RollerCoasterRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.StatsRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.StatusRoomModel
import com.sottti.roller.coasters.data.roller.coasters.model.AmusementPark
import com.sottti.roller.coasters.data.roller.coasters.model.Coordinates
import com.sottti.roller.coasters.data.roller.coasters.model.Id
import com.sottti.roller.coasters.data.roller.coasters.model.Location
import com.sottti.roller.coasters.data.roller.coasters.model.MultiTrackRide
import com.sottti.roller.coasters.data.roller.coasters.model.Picture
import com.sottti.roller.coasters.data.roller.coasters.model.Ride
import com.sottti.roller.coasters.data.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.data.roller.coasters.model.SingleTrackRide
import com.sottti.roller.coasters.data.roller.coasters.model.Status
import com.sottti.roller.coasters.utils.dates.mappers.toOriginalString
import kotlinx.serialization.InternalSerializationApi

@OptIn(InternalSerializationApi::class)
internal fun RollerCoaster.toRoomModel() =
    RollerCoasterRoomModel(
        design = specs.design.train.value,
        id = id.value,
        location = location.toLocationRoomModel(),
        mainPicture = pictures.main?.toRoomModel(id),
        manufacturer = specs.manufacturer?.value,
        model = specs.model.value,
        name = name.current.value,
        park = park.toRoomModel(),
        stats = toRoomModelStats(),
        status = status.toRoomModel(),
        type = specs.design.type.value,
    )

@OptIn(InternalSerializationApi::class)
private fun Location.toLocationRoomModel(): LocationRoomModel =
    LocationRoomModel(
        city = city.value,
        coordinates = coordinates?.toRoomModel(),
        country = country.value,
        region = region.value,
        state = state.value,
    )

@OptIn(InternalSerializationApi::class)
private fun Coordinates.toRoomModel(): CoordinatesRoomModel =
    CoordinatesRoomModel(
        latitude = latitude.value,
        longitude = longitude.value,
    )

@OptIn(InternalSerializationApi::class)
private fun Picture.toRoomModel(
    rollerCoasterId: Id,
) = PictureRoomModel(
    copyrightDate = copyright.date?.toOriginalString(),
    copyrightName = copyright.author.value,
    id = id.value,
    name = name.value,
    rollerCoasterId = rollerCoasterId.value,
    url = url.value,
)

@OptIn(InternalSerializationApi::class)
private fun AmusementPark.toRoomModel(): AmusementParkRoomModel =
    AmusementParkRoomModel(
        id = id.value,
        name = name.value
    )


@OptIn(InternalSerializationApi::class)
internal fun RollerCoaster.toRoomModelStats(): StatsRoomModel =
    StatsRoomModel(
        arrangement = specs.design.arrangement?.value,
        capacity = specs.capacity?.ridersPerHour?.value,
        cost = specs.cost?.euros?.value,
        designer = specs.design.designer?.value,
        dimensions = specs.dimensions?.meters?.value,
        drop = specs.ride?.toRoomDrop(),
        duration = specs.ride?.toRoomDuration(),
        elements = specs.design.elements?.value,
        formerNames = name.former?.value,
        formerStatus = status.former?.value,
        gForce = specs.ride?.toRoomGForce(),
        height = specs.ride?.toRoomHeight(),
        inversions = specs.ride?.toRoomInversions(),
        length = specs.ride?.toRoomLength(),
        maxVertical = specs.ride?.toRoomMaxVertical(),
        relocations = location.relocations?.value,
        speed = specs.ride?.toRoomSpeed(),
        trackNames = specs.ride?.toRoomTrackNames(),
    )


private fun Ride.toRoomDrop(): List<Double>? = when (this) {
    is SingleTrackRide -> drop?.let { listOf(it.meters.value) }
    is MultiTrackRide -> drop?.let { it.map { it.meters.value } }
}

private fun Ride.toRoomDuration(): List<Int>? = when (this) {
    is SingleTrackRide -> duration?.let { listOf(it.seconds.value) }
    is MultiTrackRide -> duration?.let { it.map { it.seconds.value } }
}

private fun Ride.toRoomGForce(): List<Double>? = when (this) {
    is SingleTrackRide -> gForce?.let { listOf(it.value) }
    is MultiTrackRide -> gForce?.let { it.map { it.value } }
}

private fun Ride.toRoomHeight(): List<Double>? = when (this) {
    is MultiTrackRide -> height?.let { it.map { it.meters.value } }
    is SingleTrackRide -> height?.let { listOf(it.meters.value) }
}

private fun Ride.toRoomInversions(): List<Int>? = when (this) {
    is SingleTrackRide -> inversions?.let { listOf(it.value) }
    is MultiTrackRide -> inversions?.let { it.map { it.value } }
}

private fun Ride.toRoomLength(): List<Double>? = when (this) {
    is MultiTrackRide -> length?.let { it.map { it.meters.value } }
    is SingleTrackRide -> length?.let { listOf(it.meters.value) }
}

private fun Ride.toRoomMaxVertical(): List<Int>? = when (this) {
    is MultiTrackRide -> maxVertical?.let { maxVerticals -> maxVerticals.map { it.degrees.value } }
    is SingleTrackRide -> maxVertical?.let { listOf(it.degrees.value) }
}

private fun Ride.toRoomSpeed(): List<Double>? = when (this) {
    is MultiTrackRide -> speed?.let { it.map { it.kmh.value } }
    is SingleTrackRide -> speed?.let { listOf(it.kmh.value) }
}

private fun Ride.toRoomTrackNames(): List<String>? = when (this) {
    is MultiTrackRide -> trackNames?.let { it.map { it.value } }
    is SingleTrackRide -> null
}

@OptIn(InternalSerializationApi::class)
private fun Status.toRoomModel(): StatusRoomModel =
    StatusRoomModel(
        openedDate = openedDate.toString(),
        state = current.value,
        closedDate = closedDate.toString(),
    )


@OptIn(InternalSerializationApi::class)
internal fun RollerCoaster.toPicturesRoomModel(): List<PictureRoomModel> =
    pictures.other.map { picture -> picture.toRoomModel(rollerCoasterId = id) }