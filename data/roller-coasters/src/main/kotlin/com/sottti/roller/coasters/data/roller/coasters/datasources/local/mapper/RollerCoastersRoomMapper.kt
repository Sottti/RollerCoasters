package com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper

import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.CoordinatesRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.DesignRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.LocationRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.NameRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.ParkRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.PictureRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.RideRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.RollerCoasterRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.SpecsRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.StatusRoomModel
import com.sottti.roller.coasters.domain.model.AmusementPark
import com.sottti.roller.coasters.domain.model.Coordinates
import com.sottti.roller.coasters.domain.model.Design
import com.sottti.roller.coasters.domain.model.Location
import com.sottti.roller.coasters.domain.model.MultiTrackRide
import com.sottti.roller.coasters.domain.model.Picture
import com.sottti.roller.coasters.domain.model.Ride
import com.sottti.roller.coasters.domain.model.RollerCoaster
import com.sottti.roller.coasters.domain.model.RollerCoasterId
import com.sottti.roller.coasters.domain.model.RollerCoasterName
import com.sottti.roller.coasters.domain.model.SingleTrackRide
import com.sottti.roller.coasters.domain.model.Specs
import com.sottti.roller.coasters.domain.model.Status
import com.sottti.roller.coasters.utils.dates.mappers.toOriginalString
import kotlinx.serialization.InternalSerializationApi

@OptIn(InternalSerializationApi::class)
internal fun RollerCoaster.toRoom() =
    RollerCoasterRoomModel(
        id = id.value,
        location = location.toRoom(),
        mainPicture = pictures.main?.toRoom(id),
        status = status.toRoom(),
        park = park.toRoom(),
        name = name.toRoom(),
        specs = specs.toRoom(),
    )

@OptIn(InternalSerializationApi::class)
private fun Location.toRoom(): LocationRoomModel =
    LocationRoomModel(
        city = city.value,
        coordinates = coordinates?.toRoom(),
        country = country.value,
        region = region.value,
        relocations = relocations?.value,
        state = state.value,
    )

@OptIn(InternalSerializationApi::class)
private fun Coordinates.toRoom(): CoordinatesRoomModel =
    CoordinatesRoomModel(
        latitude = latitude.value,
        longitude = longitude.value,
    )

@OptIn(InternalSerializationApi::class)
private fun Picture.toRoom(id: RollerCoasterId) =
    PictureRoomModel(
        copyrightDate = copyright.date?.toOriginalString(),
        copyrightName = copyright.author.value,
        id = id.value,
        name = name.value,
        rollerCoasterId = id.value,
        url = url.value,
    )

@OptIn(InternalSerializationApi::class)
private fun AmusementPark.toRoom(): ParkRoomModel =
    ParkRoomModel(
        id = id.value,
        name = name.value
    )

@OptIn(InternalSerializationApi::class)
private fun RollerCoasterName.toRoom(): NameRoomModel =
    NameRoomModel(
        current = current.value,
        former = former?.value,
    )

@OptIn(InternalSerializationApi::class)
internal fun RollerCoaster.toPicturesRoomModel(): List<PictureRoomModel> =
    pictures.other.map { picture -> picture.toRoom(id = id) }

@OptIn(InternalSerializationApi::class)
private fun Status.toRoom(): StatusRoomModel =
    StatusRoomModel(
        closedDate = closedDate?.date?.toOriginalString(),
        current = current.value,
        former = former?.value,
        openedDate = openedDate?.date?.toOriginalString(),
    )

@OptIn(InternalSerializationApi::class)
private fun Specs.toRoom(): SpecsRoomModel =
    SpecsRoomModel(
        capacity = capacity?.ridersPerHour?.value,
        cost = cost?.euros?.value,
        design = design.toRoom(),
        dimensions = dimensions?.meters?.value,
        manufacturer = manufacturer?.value,
        model = model.value,
        ride = ride?.toRoom(),
    )

@OptIn(InternalSerializationApi::class)
internal fun Design.toRoom(): DesignRoomModel =
    DesignRoomModel(
        arrangement = arrangement?.value,
        designer = designer?.value,
        elements = elements?.value,
        restraints = restraints?.value,
        train = train.value,
        type = type.value,
    )

@OptIn(InternalSerializationApi::class)
internal fun Ride.toRoom(): RideRoomModel =
    RideRoomModel(
        drop = toRoomDrop(),
        duration = toRoomDuration(),
        gForce = toRoomGForce(),
        height = toRoomHeight(),
        inversions = toRoomInversions(),
        length = toRoomLength(),
        maxVertical = toRoomMaxVertical(),
        speed = toRoomSpeed(),
        trackNames = toRoomTrackNames(),
    )

private fun Ride.toRoomDrop(): List<Double>? = when (this) {
    is SingleTrackRide -> drop?.let { listOf(it.meters.value) }
    is MultiTrackRide -> drop?.map { it.meters.value }
}

private fun Ride.toRoomDuration(): List<Int>? = when (this) {
    is SingleTrackRide -> duration?.let { listOf(it.seconds.value) }
    is MultiTrackRide -> duration?.map { it.seconds.value }
}

private fun Ride.toRoomGForce(): List<Double>? = when (this) {
    is SingleTrackRide -> gForce?.let { listOf(it.value) }
    is MultiTrackRide -> gForce?.map { it.value }
}

private fun Ride.toRoomHeight(): List<Double>? = when (this) {
    is SingleTrackRide -> height?.let { listOf(it.meters.value) }
    is MultiTrackRide -> height?.map { it.meters.value }
}

private fun Ride.toRoomInversions(): List<Int>? = when (this) {
    is SingleTrackRide -> inversions?.let { listOf(it.value) }
    is MultiTrackRide -> inversions?.map { it.value }
}

private fun Ride.toRoomLength(): List<Double>? = when (this) {
    is SingleTrackRide -> length?.let { listOf(it.meters.value) }
    is MultiTrackRide -> length?.map { it.meters.value }
}

private fun Ride.toRoomMaxVertical(): List<Int>? = when (this) {
    is SingleTrackRide -> maxVertical?.let { listOf(it.degrees.value) }
    is MultiTrackRide -> maxVertical?.map { it.degrees.value }
}

private fun Ride.toRoomSpeed(): List<Double>? = when (this) {
    is SingleTrackRide -> speed?.let { listOf(it.kmh.value) }
    is MultiTrackRide -> speed?.map { it.kmh.value }
}

private fun Ride.toRoomTrackNames(): List<String>? = when (this) {
    is MultiTrackRide -> trackNames?.map { it.value }
    is SingleTrackRide -> null
}