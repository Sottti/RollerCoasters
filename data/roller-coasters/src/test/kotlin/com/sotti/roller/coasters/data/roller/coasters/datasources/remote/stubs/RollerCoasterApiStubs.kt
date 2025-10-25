package com.sotti.roller.coasters.data.roller.coasters.datasources.remote.stubs

import com.sotti.roller.coasters.data.roller.coasters.datasources.remote.mapper.toApiModel
import com.sotti.roller.coasters.data.roller.coasters.datasources.remote.model.AmusementParkApiModel
import com.sotti.roller.coasters.data.roller.coasters.datasources.remote.model.CoordinatesApiModel
import com.sotti.roller.coasters.data.roller.coasters.datasources.remote.model.PaginationApiModel
import com.sotti.roller.coasters.data.roller.coasters.datasources.remote.model.PictureApiModel
import com.sotti.roller.coasters.data.roller.coasters.datasources.remote.model.RollerCoasterApiModel
import com.sotti.roller.coasters.data.roller.coasters.datasources.remote.model.RollerCoasterStatsApiModel
import com.sotti.roller.coasters.data.roller.coasters.datasources.remote.model.RollerCoasterStatusApiModel
import com.sotti.roller.coasters.data.roller.coasters.datasources.remote.model.RollerCoasterStatusDateApiModel
import com.sotti.roller.coasters.data.roller.coasters.datasources.remote.model.RollerCoastersApiModel
import com.sotti.roller.coasters.data.roller.coasters.datasources.remote.model.SearchCoastersApiModel
import com.sotti.roller.coasters.domain.fixtures.CITY
import com.sotti.roller.coasters.domain.fixtures.COASTER_ARRANGEMENT
import com.sotti.roller.coasters.domain.fixtures.COASTER_DESIGNER
import com.sotti.roller.coasters.domain.fixtures.COASTER_ELEMENT
import com.sotti.roller.coasters.domain.fixtures.COASTER_ID
import com.sotti.roller.coasters.domain.fixtures.COASTER_NAME
import com.sotti.roller.coasters.domain.fixtures.COASTER_TRAIN
import com.sotti.roller.coasters.domain.fixtures.COASTER_TYPE
import com.sotti.roller.coasters.domain.fixtures.COPYRIGHT_DATE
import com.sotti.roller.coasters.domain.fixtures.COST
import com.sotti.roller.coasters.domain.fixtures.COUNTRY
import com.sotti.roller.coasters.domain.fixtures.DEGREES
import com.sotti.roller.coasters.domain.fixtures.DROP
import com.sotti.roller.coasters.domain.fixtures.DURATION_IN_MMSS
import com.sotti.roller.coasters.domain.fixtures.GFORCE
import com.sotti.roller.coasters.domain.fixtures.HEIGHT
import com.sotti.roller.coasters.domain.fixtures.INVERSIONS
import com.sotti.roller.coasters.domain.fixtures.LATITUDE
import com.sotti.roller.coasters.domain.fixtures.LENGTH
import com.sotti.roller.coasters.domain.fixtures.LONGITUDE
import com.sotti.roller.coasters.domain.fixtures.MANUFACTURER
import com.sotti.roller.coasters.domain.fixtures.MODEL
import com.sotti.roller.coasters.domain.fixtures.OPENED_DATE
import com.sotti.roller.coasters.domain.fixtures.OPERATIONAL_STATE_CURRENT
import com.sotti.roller.coasters.domain.fixtures.PARK_ID
import com.sotti.roller.coasters.domain.fixtures.PARK_NAME
import com.sotti.roller.coasters.domain.fixtures.PICTURE_AUTHOR
import com.sotti.roller.coasters.domain.fixtures.PICTURE_ID_MAIN
import com.sotti.roller.coasters.domain.fixtures.PICTURE_ID_NOT_MAIN
import com.sotti.roller.coasters.domain.fixtures.PICTURE_URL
import com.sotti.roller.coasters.domain.fixtures.RESTRAINTS
import com.sotti.roller.coasters.domain.fixtures.RIDERS_PER_HOUR
import com.sotti.roller.coasters.domain.fixtures.SPEED
import com.sotti.roller.coasters.domain.roller.coasters.model.SearchQuery

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
        state = OPERATIONAL_STATE_CURRENT,
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

internal val searchRollercoastersResponseApiModel =
    SearchCoastersApiModel(
        rollerCoasters = listOf(rollerCoasterApiModel),
        totalMatch = 1,
    )

internal val searchQuery =
    SearchQuery(searchRollercoastersResponseApiModel.rollerCoasters.first().name)

internal val searchQueryApiModel = searchQuery.toApiModel()
