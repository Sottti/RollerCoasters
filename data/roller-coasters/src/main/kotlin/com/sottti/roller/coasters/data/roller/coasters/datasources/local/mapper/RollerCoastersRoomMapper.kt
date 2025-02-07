package com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper

import com.sottti.roller.coasters.data.roller.coasters.datasources.local.converters.toJson
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
import com.sottti.roller.coasters.data.roller.coasters.model.Picture
import com.sottti.roller.coasters.data.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.data.roller.coasters.model.Stats
import com.sottti.roller.coasters.data.roller.coasters.model.Status
import kotlinx.serialization.InternalSerializationApi

@OptIn(InternalSerializationApi::class)
internal fun RollerCoaster.toPicturesRoomModel(): List<PictureRoomModel> =
    pictures.map { picture -> picture.toRoomModel(rollerCoasterId = id) }

@OptIn(InternalSerializationApi::class)
internal fun RollerCoaster.toRoomModel() =
    RollerCoasterRoomModel(
        design = design.value,
        id = id.value,
        location = location.toLocationRoomModel(),
        mainPicture = mainPicture?.toRoomModel(id),
        manufacturer = manufacturer.value,
        model = model.value,
        name = name.value,
        park = park.toRoomModel(),
        stats = stats?.toRoomModel(),
        status = status.toRoomModel(),
        type = type.value,
    )

@OptIn(InternalSerializationApi::class)
internal fun Stats.toRoomModel(): StatsRoomModel =
    StatsRoomModel(
        arrangement = arrangement?.value,
        capacity = capacity?.ridersPerHour?.value,
        cost = cost?.euros?.value,
        designer = designer?.value,
        dimensions = dimensions?.meters?.value,
        drop = drop?.meters?.value,
        duration = duration?.seconds?.value,
        elements = elements.toJson(),
        formerNames = formerNames?.value,
        formerStatus = formerStatus?.value,
        gForce = gForce?.value,
        height = height?.meters?.value,
        inversions = inversions?.value,
        length = length?.meters?.value,
        relocations = relocations?.value,
        speed = speed?.kmh?.value,
    )

@OptIn(InternalSerializationApi::class)
private fun Status.toRoomModel(): StatusRoomModel =
    StatusRoomModel(
        openedDate = openedDate.toString(),
        state = state.value,
        closedDate = closedDate.toString(),
    )

@OptIn(InternalSerializationApi::class)
private fun AmusementPark.toRoomModel(): AmusementParkRoomModel =
    AmusementParkRoomModel(
        id = id.value,
        name = name.value
    )

@OptIn(InternalSerializationApi::class)
private fun Location.toLocationRoomModel(): LocationRoomModel =
    LocationRoomModel(
        city = city.value,
        country = country.value,
        region = region.value,
        state = state.value,
        coordinates = coordinates?.toRoomModel()
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
    copyrightDate = copyright.date.toString(),
    copyrightName = copyright.author.value,
    id = id.value,
    name = name.value,
    rollerCoasterId = rollerCoasterId.value,
    url = url.value,
)