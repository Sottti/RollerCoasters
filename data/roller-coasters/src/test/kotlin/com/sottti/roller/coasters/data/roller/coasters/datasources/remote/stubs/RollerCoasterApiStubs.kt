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
import com.sottti.roller.coasters.data.roller.coasters.stubs.CITY
import com.sottti.roller.coasters.data.roller.coasters.stubs.COASTER_ARRANGEMENT
import com.sottti.roller.coasters.data.roller.coasters.stubs.COASTER_DESIGNER
import com.sottti.roller.coasters.data.roller.coasters.stubs.COASTER_ELEMENT
import com.sottti.roller.coasters.data.roller.coasters.stubs.COASTER_ID
import com.sottti.roller.coasters.data.roller.coasters.stubs.COASTER_NAME
import com.sottti.roller.coasters.data.roller.coasters.stubs.COASTER_TRAIN
import com.sottti.roller.coasters.data.roller.coasters.stubs.COASTER_TYPE
import com.sottti.roller.coasters.data.roller.coasters.stubs.COUNTRY
import com.sottti.roller.coasters.data.roller.coasters.stubs.DROP
import com.sottti.roller.coasters.data.roller.coasters.stubs.DURATION_IN_MMSS
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
import com.sottti.roller.coasters.data.roller.coasters.stubs.SPEED
import com.sottti.roller.coasters.data.roller.coasters.stubs.STATE
import com.sottti.roller.coasters.data.roller.coasters.stubs.STATUS

private val pictures: List<PictureApiModel> =
    listOf(
        PictureApiModel(
            copyDate = "",
            copyName = PICTURE_AUTHOR,
            id = OTHER_PICTURE_ID,
            name = COASTER_NAME,
            url = OTHER_PICTURE_URL,
        )
    )

private val mainPicture: PictureApiModel =
    PictureApiModel(
        copyDate = "",
        copyName = PICTURE_AUTHOR,
        id = MAIN_PICTURE_ID,
        name = COASTER_NAME,
        url = MAIN_PICTURE_URL,
    )

private val coordinatesApiModel: CoordinatesApiModel =
    CoordinatesApiModel(
        lat = LATITUDE,
        lng = LONGITUDE,
    )

private val statsApiModel: RollerCoasterStatsApiModel =
    RollerCoasterStatsApiModel(
        arrangement = COASTER_ARRANGEMENT,
        builtBy = MANUFACTURER,
        capacity = null,
        cost = null,
        designer = COASTER_DESIGNER,
        dimensions = null,
        drop = listOf(DROP),
        duration = listOf(DURATION_IN_MMSS),
        elements = listOf(COASTER_ELEMENT),
        formerNames = null,
        formerStatus = null,
        gForce = null,
        height = listOf(HEIGHT),
        inversions = listOf(INVERSIONS),
        length = listOf(LENGTH),
        name = null,
        relocations = null,
        restraints = null,
        speed = listOf(SPEED),
        verticalAngle = null,
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
    mainPicture = mainPicture,
    make = MANUFACTURER,
    model = MODEL,
    name = COASTER_NAME,
    park = parkApiModel,
    pictures = pictures,
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

private val paginationApiModelPage2: PaginationApiModel =
    PaginationApiModel(
        count = 200,
        limit = 200,
        offset = 200,
        total = 400,
    )

internal val rollerCoastersApiModelPage1 =
    RollerCoastersApiModel(
        rollerCoasters = listOf(rollerCoasterApiModel),
        pagination = paginationApiModelPage1,
    )

internal val rollerCoastersApiModelPage2 =
    RollerCoastersApiModel(
        rollerCoasters = listOf(rollerCoasterApiModel),
        pagination = paginationApiModelPage2,
    )