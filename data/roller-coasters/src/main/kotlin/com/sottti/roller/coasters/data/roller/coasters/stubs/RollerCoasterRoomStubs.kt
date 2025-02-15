package com.sottti.roller.coasters.data.roller.coasters.stubs

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
import kotlinx.serialization.InternalSerializationApi

@OptIn(InternalSerializationApi::class)
internal val coordinatesRoomModel: CoordinatesRoomModel =
    CoordinatesRoomModel(latitude = LATITUDE, longitude = LONGITUDE)

@OptIn(InternalSerializationApi::class)
internal val parkRoomModel = ParkRoomModel(
    id = PARK_ID,
    name = PARK_NAME,
)

@OptIn(InternalSerializationApi::class)
internal val nameRoomModel = NameRoomModel(
    current = COASTER_NAME,
    former = null,
)

@OptIn(InternalSerializationApi::class)
internal val mainPictureRoomModel = PictureRoomModel(
    id = MAIN_PICTURE_ID,
    name = COASTER_NAME,
    rollerCoasterId = COASTER_ID,
    url = MAIN_PICTURE_URL,
    copyrightName = PICTURE_AUTHOR,
    copyrightDate = null,
)

@OptIn(InternalSerializationApi::class)
internal val picturesRoomModel = listOf(
    PictureRoomModel(
        id = OTHER_PICTURE_ID,
        name = COASTER_NAME,
        rollerCoasterId = COASTER_ID,
        url = OTHER_PICTURE_URL,
        copyrightName = PICTURE_AUTHOR,
        copyrightDate = null,
    )
)

@OptIn(InternalSerializationApi::class)
internal val statusRoomModel = StatusRoomModel(
    current = STATUS,
    former = null,
    openedDate = OPENED_DATE,
    closedDate = null,
)

@OptIn(InternalSerializationApi::class)
internal val designRoomModel = DesignRoomModel(
    type = COASTER_TYPE,
    train = COASTER_TRAIN,
    elements = COASTER_ELEMENT,
    arrangement = COASTER_ARRANGEMENT,
    restraints = null,
    designer = COASTER_DESIGNER,
)

@OptIn(InternalSerializationApi::class)
internal val rideRoomModel = RideRoomModel(
    height = listOf(HEIGHT),
    length = listOf(LENGTH),
    speed = listOf(SPEED),
    duration = listOf(DURATION_IN_SECONDS),
    inversions = listOf(INVERSIONS),
    gForce = null,
    drop = listOf(DROP),
    maxVertical = null,
    trackNames = null,
)

@OptIn(InternalSerializationApi::class)
internal val specsRoomModel = SpecsRoomModel(
    model = MODEL,
    manufacturer = MANUFACTURER,
    cost = null,
    dimensions = null,
    capacity = null,
    design = designRoomModel,
    ride = rideRoomModel,
)

@OptIn(InternalSerializationApi::class)
internal val locationRoomModel = LocationRoomModel(
    city = CITY,
    country = COUNTRY,
    region = REGION,
    state = STATE,
    coordinates = coordinatesRoomModel,
    relocations = null,
)

@OptIn(InternalSerializationApi::class)
internal val rollerCoasterRoomModel = RollerCoasterRoomModel(
    id = COASTER_ID,
    location = locationRoomModel,
    park = parkRoomModel,
    name = nameRoomModel,
    status = statusRoomModel,
    specs = specsRoomModel,
    mainPicture = mainPictureRoomModel,
)

@OptIn(InternalSerializationApi::class)
internal val rollerCoastersRoom = listOf(rollerCoasterRoomModel)