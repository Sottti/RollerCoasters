package com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs

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
import com.sottti.roller.coasters.data.roller.coasters.stubs.CITY
import com.sottti.roller.coasters.data.roller.coasters.stubs.COASTER_ARRANGEMENT
import com.sottti.roller.coasters.data.roller.coasters.stubs.COASTER_DESIGNER
import com.sottti.roller.coasters.data.roller.coasters.stubs.COASTER_ELEMENT
import com.sottti.roller.coasters.data.roller.coasters.stubs.COASTER_ID
import com.sottti.roller.coasters.data.roller.coasters.stubs.COASTER_NAME
import com.sottti.roller.coasters.data.roller.coasters.stubs.COASTER_TRAIN
import com.sottti.roller.coasters.data.roller.coasters.stubs.COASTER_TYPE
import com.sottti.roller.coasters.data.roller.coasters.stubs.COPYRIGHT_DATE
import com.sottti.roller.coasters.data.roller.coasters.stubs.COST
import com.sottti.roller.coasters.data.roller.coasters.stubs.COUNTRY
import com.sottti.roller.coasters.data.roller.coasters.stubs.DEGREES
import com.sottti.roller.coasters.data.roller.coasters.stubs.DROP
import com.sottti.roller.coasters.data.roller.coasters.stubs.DURATION_IN_SECONDS
import com.sottti.roller.coasters.data.roller.coasters.stubs.GFORCE
import com.sottti.roller.coasters.data.roller.coasters.stubs.HEIGHT
import com.sottti.roller.coasters.data.roller.coasters.stubs.INVERSIONS
import com.sottti.roller.coasters.data.roller.coasters.stubs.LATITUDE
import com.sottti.roller.coasters.data.roller.coasters.stubs.LENGTH
import com.sottti.roller.coasters.data.roller.coasters.stubs.LONGITUDE
import com.sottti.roller.coasters.data.roller.coasters.stubs.MAIN_PICTURE_ID
import com.sottti.roller.coasters.data.roller.coasters.stubs.MAIN_PICTURE_URL
import com.sottti.roller.coasters.data.roller.coasters.stubs.MANUFACTURER
import com.sottti.roller.coasters.data.roller.coasters.stubs.MODEL
import com.sottti.roller.coasters.data.roller.coasters.stubs.OPENED_DATE
import com.sottti.roller.coasters.data.roller.coasters.stubs.OTHER_PICTURE_ID
import com.sottti.roller.coasters.data.roller.coasters.stubs.OTHER_PICTURE_URL
import com.sottti.roller.coasters.data.roller.coasters.stubs.PARK_ID
import com.sottti.roller.coasters.data.roller.coasters.stubs.PARK_NAME
import com.sottti.roller.coasters.data.roller.coasters.stubs.PICTURE_AUTHOR
import com.sottti.roller.coasters.data.roller.coasters.stubs.REGION
import com.sottti.roller.coasters.data.roller.coasters.stubs.RESTRAINTS
import com.sottti.roller.coasters.data.roller.coasters.stubs.RIDERS_PER_HOUR
import com.sottti.roller.coasters.data.roller.coasters.stubs.SPEED
import com.sottti.roller.coasters.data.roller.coasters.stubs.STATE
import com.sottti.roller.coasters.data.roller.coasters.stubs.STATUS
import kotlinx.serialization.InternalSerializationApi

@OptIn(InternalSerializationApi::class)
private val coordinatesRoomModel: CoordinatesRoomModel =
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
    copyrightDate = COPYRIGHT_DATE,
)

@OptIn(InternalSerializationApi::class)
internal val picturesRoomModel = listOf(
    PictureRoomModel(
        id = OTHER_PICTURE_ID,
        name = COASTER_NAME,
        rollerCoasterId = COASTER_ID,
        url = OTHER_PICTURE_URL,
        copyrightName = PICTURE_AUTHOR,
        copyrightDate = COPYRIGHT_DATE,
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
    restraints = RESTRAINTS,
    designer = COASTER_DESIGNER,
)

@OptIn(InternalSerializationApi::class)
internal val rideRoomModel = RideRoomModel(
    height = listOf(HEIGHT),
    length = listOf(LENGTH),
    speed = listOf(SPEED),
    duration = listOf(DURATION_IN_SECONDS),
    inversions = listOf(INVERSIONS),
    gForce = listOf(GFORCE),
    drop = listOf(DROP),
    maxVertical = listOf(DEGREES),
    trackNames = null,
)

@OptIn(InternalSerializationApi::class)
internal val specsRoomModel = SpecsRoomModel(
    model = MODEL,
    manufacturer = MANUFACTURER,
    cost = COST,
    dimensions = null,
    capacity = RIDERS_PER_HOUR,
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