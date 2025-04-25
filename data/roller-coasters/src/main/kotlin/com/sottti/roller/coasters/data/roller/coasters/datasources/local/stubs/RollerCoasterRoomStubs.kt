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
import com.sottti.roller.coasters.domain.fixtures.CITY
import com.sottti.roller.coasters.domain.fixtures.COASTER_ARRANGEMENT
import com.sottti.roller.coasters.domain.fixtures.COASTER_DESIGNER
import com.sottti.roller.coasters.domain.fixtures.COASTER_ELEMENT
import com.sottti.roller.coasters.domain.fixtures.COASTER_ID
import com.sottti.roller.coasters.domain.fixtures.COASTER_ID_ANOTHER
import com.sottti.roller.coasters.domain.fixtures.COASTER_NAME
import com.sottti.roller.coasters.domain.fixtures.COASTER_TRAIN
import com.sottti.roller.coasters.domain.fixtures.COASTER_TYPE
import com.sottti.roller.coasters.domain.fixtures.COPYRIGHT_DATE
import com.sottti.roller.coasters.domain.fixtures.COST
import com.sottti.roller.coasters.domain.fixtures.COUNTRY
import com.sottti.roller.coasters.domain.fixtures.DEGREES
import com.sottti.roller.coasters.domain.fixtures.DROP
import com.sottti.roller.coasters.domain.fixtures.DURATION_IN_SECONDS
import com.sottti.roller.coasters.domain.fixtures.GFORCE
import com.sottti.roller.coasters.domain.fixtures.HEIGHT
import com.sottti.roller.coasters.domain.fixtures.INVERSIONS
import com.sottti.roller.coasters.domain.fixtures.LATITUDE
import com.sottti.roller.coasters.domain.fixtures.LENGTH
import com.sottti.roller.coasters.domain.fixtures.LONGITUDE
import com.sottti.roller.coasters.domain.fixtures.MANUFACTURER
import com.sottti.roller.coasters.domain.fixtures.MODEL
import com.sottti.roller.coasters.domain.fixtures.OPENED_DATE
import com.sottti.roller.coasters.domain.fixtures.PARK_ID
import com.sottti.roller.coasters.domain.fixtures.PARK_NAME
import com.sottti.roller.coasters.domain.fixtures.PICTURE_AUTHOR
import com.sottti.roller.coasters.domain.fixtures.PICTURE_ID_ANOTHER_MAIN
import com.sottti.roller.coasters.domain.fixtures.PICTURE_ID_ANOTHER_NOT_MAIN
import com.sottti.roller.coasters.domain.fixtures.PICTURE_ID_MAIN
import com.sottti.roller.coasters.domain.fixtures.PICTURE_ID_NOT_MAIN
import com.sottti.roller.coasters.domain.fixtures.PICTURE_URL
import com.sottti.roller.coasters.domain.fixtures.REGION
import com.sottti.roller.coasters.domain.fixtures.RESTRAINTS
import com.sottti.roller.coasters.domain.fixtures.RIDERS_PER_HOUR
import com.sottti.roller.coasters.domain.fixtures.SPEED
import com.sottti.roller.coasters.domain.fixtures.STATE
import com.sottti.roller.coasters.domain.fixtures.OPERATIONAL_STATE_CURRENT
import kotlinx.serialization.InternalSerializationApi

@OptIn(InternalSerializationApi::class)
private val parkRoomModel = ParkRoomModel(
    id = PARK_ID,
    name = PARK_NAME,
)

@OptIn(InternalSerializationApi::class)
private val nameRoomModel = NameRoomModel(
    current = COASTER_NAME,
    former = null,
)

@OptIn(InternalSerializationApi::class)
internal val mainPictureRoomModel = PictureRoomModel(
    id = PICTURE_ID_MAIN,
    name = COASTER_NAME,
    rollerCoasterId = COASTER_ID,
    url = PICTURE_URL,
    copyrightName = PICTURE_AUTHOR,
    copyrightDate = COPYRIGHT_DATE,
)

@OptIn(InternalSerializationApi::class)
internal val anotherMainPictureRoomModel =
    PictureRoomModel(
        id = PICTURE_ID_ANOTHER_MAIN,
        name = COASTER_NAME,
        rollerCoasterId = COASTER_ID_ANOTHER,
        url = PICTURE_URL,
        copyrightName = PICTURE_AUTHOR,
        copyrightDate = COPYRIGHT_DATE,
    )

@OptIn(InternalSerializationApi::class)
internal val notMainPictureRoomModel =
    PictureRoomModel(
        id = PICTURE_ID_NOT_MAIN,
        name = COASTER_NAME,
        rollerCoasterId = COASTER_ID,
        url = PICTURE_URL,
        copyrightName = PICTURE_AUTHOR,
        copyrightDate = COPYRIGHT_DATE,
    )

@OptIn(InternalSerializationApi::class)
internal val anotherNotMainPictureRoomModel =
    PictureRoomModel(
        id = PICTURE_ID_ANOTHER_NOT_MAIN,
        name = COASTER_NAME,
        rollerCoasterId = COASTER_ID_ANOTHER,
        url = PICTURE_URL,
        copyrightName = PICTURE_AUTHOR,
        copyrightDate = COPYRIGHT_DATE,
    )

@OptIn(InternalSerializationApi::class)
private val statusRoomModel = StatusRoomModel(
    current = OPERATIONAL_STATE_CURRENT,
    former = null,
    openedDate = OPENED_DATE,
    closedDate = null,
)

@OptIn(InternalSerializationApi::class)
private val designRoomModel = DesignRoomModel(
    type = COASTER_TYPE,
    train = COASTER_TRAIN,
    elements = COASTER_ELEMENT,
    arrangement = COASTER_ARRANGEMENT,
    restraints = RESTRAINTS,
    designer = COASTER_DESIGNER,
)

@OptIn(InternalSerializationApi::class)
private val rideRoomModel = RideRoomModel(
    drop = listOf(DROP),
    dropMax = DROP,
    duration = listOf(DURATION_IN_SECONDS),
    gForce = listOf(GFORCE),
    gForceMax = GFORCE,
    height = listOf(HEIGHT),
    heightMax = HEIGHT,
    inversions = listOf(INVERSIONS),
    inversionsMax = INVERSIONS,
    length = listOf(LENGTH),
    lengthMax = LENGTH,
    maxVertical = listOf(DEGREES),
    maxVerticalMax = DEGREES,
    speed = listOf(SPEED),
    speedMax = SPEED,
    trackNames = null,
)

@OptIn(InternalSerializationApi::class)
private val specsRoomModel = SpecsRoomModel(
    model = MODEL,
    manufacturer = MANUFACTURER,
    cost = COST,
    dimensions = null,
    capacity = RIDERS_PER_HOUR,
    design = designRoomModel,
    ride = rideRoomModel,
)

@OptIn(InternalSerializationApi::class)
private val coordinatesRoomModel: CoordinatesRoomModel =
    CoordinatesRoomModel(latitude = LATITUDE, longitude = LONGITUDE)

@OptIn(InternalSerializationApi::class)
private val locationRoomModel = LocationRoomModel(
    city = CITY,
    country = COUNTRY,
    region = REGION,
    state = STATE,
    coordinates = coordinatesRoomModel,
    relocations = null,
)

@OptIn(InternalSerializationApi::class)
internal val rollerCoasterRoomModel =
    RollerCoasterRoomModel(
        id = COASTER_ID,
        location = locationRoomModel,
        park = parkRoomModel,
        name = nameRoomModel,
        status = statusRoomModel,
        specs = specsRoomModel,
        mainPicture = mainPictureRoomModel,
    )

@OptIn(InternalSerializationApi::class)
internal val anotherRollerCoasterRoomModel =
    rollerCoasterRoomModel.copy(
        id = COASTER_ID_ANOTHER,
        mainPicture = anotherMainPictureRoomModel,
    )