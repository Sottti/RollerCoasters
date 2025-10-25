package com.sotti.roller.coasters.data.roller.coasters.datasources.local.mapper

import com.sotti.roller.coasters.data.roller.coasters.datasources.local.model.CoordinatesRoomModel
import com.sotti.roller.coasters.data.roller.coasters.datasources.local.model.DesignRoomModel
import com.sotti.roller.coasters.data.roller.coasters.datasources.local.model.LocationRoomModel
import com.sotti.roller.coasters.data.roller.coasters.datasources.local.model.NameRoomModel
import com.sotti.roller.coasters.data.roller.coasters.datasources.local.model.ParkRoomModel
import com.sotti.roller.coasters.data.roller.coasters.datasources.local.model.PictureRoomModel
import com.sotti.roller.coasters.data.roller.coasters.datasources.local.model.RideRoomModel
import com.sotti.roller.coasters.data.roller.coasters.datasources.local.model.RollerCoasterRoomModel
import com.sotti.roller.coasters.data.roller.coasters.datasources.local.model.SpecsRoomModel
import com.sotti.roller.coasters.data.roller.coasters.datasources.local.model.StatusRoomModel
import com.sotti.roller.coasters.data.roller.coasters.mapper.toDrop
import com.sotti.roller.coasters.data.roller.coasters.mapper.toHeight
import com.sotti.roller.coasters.data.roller.coasters.mapper.toLength
import com.sotti.roller.coasters.data.roller.coasters.mapper.toSpeed
import com.sotti.roller.coasters.domain.model.Author
import com.sotti.roller.coasters.domain.model.City
import com.sotti.roller.coasters.domain.model.Coordinates
import com.sotti.roller.coasters.domain.model.Euros
import com.sotti.roller.coasters.domain.model.ImageUrl
import com.sotti.roller.coasters.domain.model.Latitude
import com.sotti.roller.coasters.domain.model.Longitude
import com.sotti.roller.coasters.domain.model.Meters
import com.sotti.roller.coasters.domain.model.Name
import com.sotti.roller.coasters.domain.model.Picture
import com.sotti.roller.coasters.domain.model.PictureCopyright
import com.sotti.roller.coasters.domain.model.PictureId
import com.sotti.roller.coasters.domain.model.PictureName
import com.sotti.roller.coasters.domain.model.Seconds
import com.sotti.roller.coasters.domain.roller.coasters.model.AmusementPark
import com.sotti.roller.coasters.domain.roller.coasters.model.Arrangement
import com.sotti.roller.coasters.domain.roller.coasters.model.Capacity
import com.sotti.roller.coasters.domain.roller.coasters.model.ClosedDate
import com.sotti.roller.coasters.domain.roller.coasters.model.Cost
import com.sotti.roller.coasters.domain.roller.coasters.model.Country
import com.sotti.roller.coasters.domain.roller.coasters.model.Degrees
import com.sotti.roller.coasters.domain.roller.coasters.model.Design
import com.sotti.roller.coasters.domain.roller.coasters.model.Designer
import com.sotti.roller.coasters.domain.roller.coasters.model.Dimensions
import com.sotti.roller.coasters.domain.roller.coasters.model.Duration
import com.sotti.roller.coasters.domain.roller.coasters.model.Element
import com.sotti.roller.coasters.domain.roller.coasters.model.FormerStatus
import com.sotti.roller.coasters.domain.roller.coasters.model.GForce
import com.sotti.roller.coasters.domain.roller.coasters.model.Inversions
import com.sotti.roller.coasters.domain.roller.coasters.model.Location
import com.sotti.roller.coasters.domain.roller.coasters.model.Manufacturer
import com.sotti.roller.coasters.domain.roller.coasters.model.MaxVertical
import com.sotti.roller.coasters.domain.roller.coasters.model.Model
import com.sotti.roller.coasters.domain.roller.coasters.model.MultiTrackRide
import com.sotti.roller.coasters.domain.roller.coasters.model.OpenedDate
import com.sotti.roller.coasters.domain.roller.coasters.model.OperationalState
import com.sotti.roller.coasters.domain.roller.coasters.model.ParkId
import com.sotti.roller.coasters.domain.roller.coasters.model.Pictures
import com.sotti.roller.coasters.domain.roller.coasters.model.Relocations
import com.sotti.roller.coasters.domain.roller.coasters.model.Restraints
import com.sotti.roller.coasters.domain.roller.coasters.model.Ride
import com.sotti.roller.coasters.domain.roller.coasters.model.RidersPerHour
import com.sotti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sotti.roller.coasters.domain.roller.coasters.model.RollerCoasterId
import com.sotti.roller.coasters.domain.roller.coasters.model.RollerCoasterName
import com.sotti.roller.coasters.domain.roller.coasters.model.SingleTrackRide
import com.sotti.roller.coasters.domain.roller.coasters.model.Specs
import com.sotti.roller.coasters.domain.roller.coasters.model.Status
import com.sotti.roller.coasters.domain.roller.coasters.model.Train
import com.sotti.roller.coasters.domain.roller.coasters.model.Type
import com.sotti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem
import com.sotti.roller.coasters.utils.time.dates.mapper.toDate

internal fun RollerCoasterRoomModel.toDomain(
    measurementSystem: ResolvedMeasurementSystem,
    pictures: List<PictureRoomModel>,
) = RollerCoaster(
    id = RollerCoasterId(id),
    location = location.toDomain(),
    name = name.toDomain(),
    park = park.toDomain(),
    pictures = pictures.toDomain(mainPicture),
    specs = specs.toDomain(measurementSystem),
    status = status.toDomain(),
)

private fun LocationRoomModel.toDomain() =
    Location(
        city = City(city),
        coordinates = coordinates?.toDomain(),
        country = Country(country),
        relocations = relocations?.let(::Relocations),
    )

private fun CoordinatesRoomModel.toDomain() =
    Coordinates(
        latitude = Latitude(latitude),
        longitude = Longitude(longitude),
    )

private fun NameRoomModel.toDomain() =
    RollerCoasterName(
        current = Name(current),
        former = former?.let(::Name),
    )

private fun ParkRoomModel.toDomain() =
    AmusementPark(
        id = ParkId(id),
        name = Name(name),
    )

private fun List<PictureRoomModel>.toDomain(
    mainPicture: PictureRoomModel?,
): Pictures = Pictures(
    main = mainPicture?.toDomain(),
    other = map(PictureRoomModel::toDomain)
)

private fun PictureRoomModel.toDomain() =
    Picture(
        id = PictureId(id),
        name = PictureName(name),
        url = ImageUrl(url),
        copyright = PictureCopyright(
            author = Author(copyrightName),
            date = copyrightDate?.toDate()
        ),
    )

private fun SpecsRoomModel.toDomain(
    measurementSystem: ResolvedMeasurementSystem,
) = Specs(
    capacity = capacity?.let { Capacity(RidersPerHour(it)) },
    cost = cost?.let { Cost(Euros(it)) },
    design = design.toDomain(),
    dimensions = dimensions?.let { Dimensions(Meters(it)) },
    manufacturer = manufacturer?.let(::Manufacturer),
    model = Model(model),
    ride = ride?.toDomain(measurementSystem = measurementSystem),
)

private fun DesignRoomModel.toDomain() =
    Design(
        arrangement = arrangement?.let(::Arrangement),
        designer = designer?.let(::Designer),
        elements = elements?.let(::Element),
        restraints = restraints?.let(::Restraints),
        train = Train(train),
        type = Type(type),
    )

private fun RideRoomModel.toDomain(
    measurementSystem: ResolvedMeasurementSystem,
): Ride =
    when {
        trackNames.isNullOrEmpty() -> toSingleTrackRide(measurementSystem)
        else -> toMultiTrackRide(measurementSystem, trackNames)
    }

private fun RideRoomModel.toSingleTrackRide(
    measurementSystem: ResolvedMeasurementSystem,
) = SingleTrackRide(
    drop = drop?.firstOrNull()?.toDrop(measurementSystem),
    duration = duration?.firstOrNull()?.let { Duration(Seconds(it)) },
    gForce = gForce?.firstOrNull()?.let(::GForce),
    height = height?.firstOrNull()?.toHeight(measurementSystem),
    inversions = inversions?.firstOrNull()?.let(::Inversions),
    length = length?.firstOrNull()?.toLength(measurementSystem),
    maxVertical = maxVertical?.firstOrNull()?.let { MaxVertical(Degrees(it)) },
    speed = speed?.firstOrNull()?.toSpeed(measurementSystem),
)

private fun RideRoomModel.toMultiTrackRide(
    measurementSystem: ResolvedMeasurementSystem,
    trackNames: List<String>,
) = MultiTrackRide(
    drop = drop?.map { it.toDrop(measurementSystem) },
    duration = duration?.map { Duration(Seconds(it)) },
    gForce = gForce?.map(::GForce),
    height = height?.map { it.toHeight(measurementSystem) },
    inversions = inversions?.map(::Inversions),
    length = length?.map { it.toLength(measurementSystem) },
    maxVertical = maxVertical?.map { MaxVertical(Degrees(it)) },
    speed = speed?.map { it.toSpeed(measurementSystem) },
    trackNames = trackNames.map(::Name),
)

private fun StatusRoomModel.toDomain() = Status(
    closedDate = closedDate?.toDate()?.let(::ClosedDate),
    current = current?.let { OperationalState(current) },
    former = former?.let(::FormerStatus),
    openedDate = openedDate?.toDate()?.let(::OpenedDate),
)
