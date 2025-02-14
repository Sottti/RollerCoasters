package com.sottti.roller.coasters.data.roller.coasters

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
internal val parkRoom = ParkRoomModel(
    id = PARK_ID,
    name = PARK_NAME,
)

@OptIn(InternalSerializationApi::class)
internal val nameRoom = NameRoomModel(
    current = COASTER_NAME,
    former = null,
)

@OptIn(InternalSerializationApi::class)
internal val mainPictureRoom = PictureRoomModel(
    id = MAIN_PICTURE_ID,
    name = COASTER_NAME,
    rollerCoasterId = COASTER_ID,
    url = MAIN_PICTURE_URL,
    copyrightName = PICTURE_AUTHOR,
    copyrightDate = null,
)

@OptIn(InternalSerializationApi::class)
internal val picturesRoom = listOf(
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
internal val statusRoom = StatusRoomModel(
    current = STATUS,
    former = null,
    openedDate = OPENED_DATE,
    closedDate = null,
)

@OptIn(InternalSerializationApi::class)
internal val designRoom = DesignRoomModel(
    type = COASTER_TYPE,
    train = COASTER_TRAIN,
    elements = COASTER_ELEMENT,
    arrangement = COASTER_ARRANGEMENT,
    restraints = null,
    designer = COASTER_DESIGNER,
)

@OptIn(InternalSerializationApi::class)
internal val rideRoom = RideRoomModel(
    height = listOf(HEIGHT),
    length = listOf(LENGTH),
    speed = listOf(SPEED),
    duration = listOf(DURATION),
    inversions = listOf(INVERSIONS),
    gForce = null,
    drop = listOf(DROP),
    maxVertical = null,
    trackNames = null,
)

@OptIn(InternalSerializationApi::class)
internal val specsRoom = SpecsRoomModel(
    model = MODEL,
    manufacturer = MANUFACTURER,
    cost = null,
    dimensions = null,
    capacity = null,
    design = designRoom,
    ride = rideRoom,
)

@OptIn(InternalSerializationApi::class)
internal val locationRoom = LocationRoomModel(
    city = CITY,
    country = COUNTRY,
    region = REGION,
    state = STATE,
    coordinates = coordinatesRoomModel,
    relocations = null,
)

@OptIn(InternalSerializationApi::class)
internal val rollerCoasterRoom = RollerCoasterRoomModel(
    id = COASTER_ID,
    location = locationRoom,
    park = parkRoom,
    name = nameRoom,
    status = statusRoom,
    specs = specsRoom,
    mainPicture = mainPictureRoom,
)

@OptIn(InternalSerializationApi::class)
internal val rollerCoastersRoom = listOf(rollerCoasterRoom)