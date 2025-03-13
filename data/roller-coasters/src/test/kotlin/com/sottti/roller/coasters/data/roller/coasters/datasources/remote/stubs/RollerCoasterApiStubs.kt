package com.sottti.roller.coasters.data.roller.coasters.datasources.remote.stubs

import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.AmusementParkApiModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.CoordinatesApiModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.PaginationApiModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.PictureApiModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.RollerCoasterApiModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.RollerCoasterStatsApiModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.RollerCoasterStatusApiModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.RollerCoasterStatusDateApiModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.RollerCoastersApiModel
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.CITY
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.COASTER_ARRANGEMENT
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.COASTER_DESIGNER
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.COASTER_ELEMENT
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.COASTER_ID
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.COASTER_NAME
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.COASTER_TRAIN
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.COASTER_TYPE
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.COPYRIGHT_DATE
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.COST
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.COUNTRY
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.DEGREES
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.DROP
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.DURATION_IN_MMSS
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.GFORCE
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.HEIGHT
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.INVERSIONS
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.LATITUDE
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.LENGTH
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.LONGITUDE
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.MANUFACTURER
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.MODEL
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.OPENED_DATE
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.PARK_ID
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.PARK_NAME
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.PICTURE_AUTHOR
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.PICTURE_ID_MAIN
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.PICTURE_ID_NOT_MAIN
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.PICTURE_URL
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.REGION
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.RESTRAINTS
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.RIDERS_PER_HOUR
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.SPEED
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.STATE
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.STATUS

internal val mainPictureApiModel: PictureApiModel =
    PictureApiModel(
        copyDate = COPYRIGHT_DATE,
        copyName = PICTURE_AUTHOR,
        id = PICTURE_ID_MAIN,
        name = COASTER_NAME,
        url = PICTURE_URL,
    )

internal val notMainPictureApiModel: PictureApiModel =
    PictureApiModel(
        copyDate = COPYRIGHT_DATE,
        copyName = PICTURE_AUTHOR,
        id = PICTURE_ID_NOT_MAIN,
        name = COASTER_NAME,
        url = PICTURE_URL,
    )

internal val coordinatesApiModel: CoordinatesApiModel =
    CoordinatesApiModel(
        lat = LATITUDE,
        lng = LONGITUDE,
    )

private val statsApiModel: RollerCoasterStatsApiModel =
    RollerCoasterStatsApiModel(
        arrangement = COASTER_ARRANGEMENT,
        builtBy = MANUFACTURER,
        capacity = RIDERS_PER_HOUR,
        cost = COST,
        designer = COASTER_DESIGNER,
        dimensions = null,
        drop = listOf(DROP),
        duration = listOf(DURATION_IN_MMSS),
        elements = listOf(COASTER_ELEMENT),
        formerNames = null,
        formerStatus = null,
        gForce = listOf(GFORCE),
        height = listOf(HEIGHT),
        inversions = listOf(INVERSIONS),
        length = listOf(LENGTH),
        name = null,
        relocations = null,
        restraints = RESTRAINTS,
        speed = listOf(SPEED),
        verticalAngle = listOf(DEGREES),
    )

private val parkApiModel: AmusementParkApiModel =
    AmusementParkApiModel(
        id = PARK_ID,
        name = PARK_NAME,
    )

private val statusApiModel: RollerCoasterStatusApiModel =
    RollerCoasterStatusApiModel(
        date = RollerCoasterStatusDateApiModel(closed = null, opened = OPENED_DATE),
        state = STATUS,
    )

internal val rollerCoasterApiModel = RollerCoasterApiModel(
    city = CITY,
    coords = coordinatesApiModel,
    country = COUNTRY,
    design = COASTER_TRAIN,
    id = COASTER_ID,
    link = "/$COASTER_ID.htm",
    mainPicture = mainPictureApiModel,
    make = MANUFACTURER,
    model = MODEL,
    name = COASTER_NAME,
    park = parkApiModel,
    pictures = listOf(notMainPictureApiModel),
    region = REGION,
    state = STATE,
    stats = statsApiModel,
    status = statusApiModel,
    type = COASTER_TYPE,
)

private val paginationApiModelPage1: PaginationApiModel =
    PaginationApiModel(
        count = 200,
        limit = 200,
        offset = 0,
        total = 400,
    )

internal val rollerCoastersApiModelPage1 =
    RollerCoastersApiModel(
        rollerCoasters = listOf(rollerCoasterApiModel),
        pagination = paginationApiModelPage1,
    )