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
import com.sottti.roller.coasters.data.roller.coasters.mapper.toDrop
import com.sottti.roller.coasters.data.roller.coasters.mapper.toHeight
import com.sottti.roller.coasters.data.roller.coasters.mapper.toLength
import com.sottti.roller.coasters.data.roller.coasters.mapper.toSpeed
import com.sottti.roller.coasters.domain.model.Author
import com.sottti.roller.coasters.domain.model.City
import com.sottti.roller.coasters.domain.model.Coordinates
import com.sottti.roller.coasters.domain.model.Euros
import com.sottti.roller.coasters.domain.model.ImageUrl
import com.sottti.roller.coasters.domain.model.Latitude
import com.sottti.roller.coasters.domain.model.Longitude
import com.sottti.roller.coasters.domain.model.Meters
import com.sottti.roller.coasters.domain.model.Name
import com.sottti.roller.coasters.domain.model.Picture
import com.sottti.roller.coasters.domain.model.PictureCopyright
import com.sottti.roller.coasters.domain.model.PictureId
import com.sottti.roller.coasters.domain.model.PictureName
import com.sottti.roller.coasters.domain.model.Region
import com.sottti.roller.coasters.domain.model.Seconds
import com.sottti.roller.coasters.domain.roller.coasters.model.AmusementPark
import com.sottti.roller.coasters.domain.roller.coasters.model.Arrangement
import com.sottti.roller.coasters.domain.roller.coasters.model.Capacity
import com.sottti.roller.coasters.domain.roller.coasters.model.ClosedDate
import com.sottti.roller.coasters.domain.roller.coasters.model.Cost
import com.sottti.roller.coasters.domain.roller.coasters.model.Country
import com.sottti.roller.coasters.domain.roller.coasters.model.Degrees
import com.sottti.roller.coasters.domain.roller.coasters.model.Design
import com.sottti.roller.coasters.domain.roller.coasters.model.Designer
import com.sottti.roller.coasters.domain.roller.coasters.model.Dimensions
import com.sottti.roller.coasters.domain.roller.coasters.model.Duration
import com.sottti.roller.coasters.domain.roller.coasters.model.Element
import com.sottti.roller.coasters.domain.roller.coasters.model.FormerStatus
import com.sottti.roller.coasters.domain.roller.coasters.model.GForce
import com.sottti.roller.coasters.domain.roller.coasters.model.Inversions
import com.sottti.roller.coasters.domain.roller.coasters.model.Location
import com.sottti.roller.coasters.domain.roller.coasters.model.Manufacturer
import com.sottti.roller.coasters.domain.roller.coasters.model.MaxVertical
import com.sottti.roller.coasters.domain.roller.coasters.model.Model
import com.sottti.roller.coasters.domain.roller.coasters.model.MultiTrackRide
import com.sottti.roller.coasters.domain.roller.coasters.model.OpenedDate
import com.sottti.roller.coasters.domain.roller.coasters.model.OperationalState
import com.sottti.roller.coasters.domain.roller.coasters.model.ParkId
import com.sottti.roller.coasters.domain.roller.coasters.model.Pictures
import com.sottti.roller.coasters.domain.roller.coasters.model.Relocations
import com.sottti.roller.coasters.domain.roller.coasters.model.Restraints
import com.sottti.roller.coasters.domain.roller.coasters.model.Ride
import com.sottti.roller.coasters.domain.roller.coasters.model.RidersPerHour
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoasterId
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoasterName
import com.sottti.roller.coasters.domain.roller.coasters.model.SingleTrackRide
import com.sottti.roller.coasters.domain.roller.coasters.model.Specs
import com.sottti.roller.coasters.domain.roller.coasters.model.State
import com.sottti.roller.coasters.domain.roller.coasters.model.Status
import com.sottti.roller.coasters.domain.roller.coasters.model.Train
import com.sottti.roller.coasters.domain.roller.coasters.model.Type
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem
import com.sottti.roller.coasters.utils.time.dates.mapper.toDate
import kotlinx.serialization.InternalSerializationApi

@OptIn(InternalSerializationApi::class)
internal fun RollerCoasterRoomModel.toDomain(
    measurementSystem: SystemMeasurementSystem,
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

@OptIn(InternalSerializationApi::class)
private fun LocationRoomModel.toDomain() =
    Location(
        city = City(city),
        coordinates = coordinates?.toDomain(),
        country = Country(country),
        region = Region(region),
        relocations = relocations?.let(::Relocations),
        state = State(state),
    )

@OptIn(InternalSerializationApi::class)
private fun CoordinatesRoomModel.toDomain() =
    Coordinates(
        latitude = Latitude(latitude),
        longitude = Longitude(longitude)
    )

@OptIn(InternalSerializationApi::class)
private fun NameRoomModel.toDomain() =
    RollerCoasterName(
        current = Name(current),
        former = former?.let(::Name),
    )

@OptIn(InternalSerializationApi::class)
private fun ParkRoomModel.toDomain() =
    AmusementPark(
        id = ParkId(id),
        name = Name(name),
    )

@OptIn(InternalSerializationApi::class)
private fun List<PictureRoomModel>.toDomain(
    mainPicture: PictureRoomModel?,
): Pictures = Pictures(
    main = mainPicture?.toDomain(),
    other = map(PictureRoomModel::toDomain)
)

@OptIn(InternalSerializationApi::class)
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

@OptIn(InternalSerializationApi::class)
private fun SpecsRoomModel.toDomain(
    measurementSystem: SystemMeasurementSystem,
) = Specs(
    capacity = capacity?.let { Capacity(RidersPerHour(it)) },
    cost = cost?.let { Cost(Euros(it)) },
    design = design.toDomain(),
    dimensions = dimensions?.let { Dimensions(Meters(it)) },
    manufacturer = manufacturer?.let(::Manufacturer),
    model = Model(model),
    ride = ride?.toDomain(measurementSystem = measurementSystem),
)

@OptIn(InternalSerializationApi::class)
private fun DesignRoomModel.toDomain() =
    Design(
        arrangement = arrangement?.let(::Arrangement),
        designer = designer?.let(::Designer),
        elements = elements?.let(::Element),
        restraints = restraints?.let(::Restraints),
        train = Train(train),
        type = Type(type),
    )

@OptIn(InternalSerializationApi::class)
private fun RideRoomModel.toDomain(
    measurementSystem: SystemMeasurementSystem,
): Ride =
    when {
        trackNames.isNullOrEmpty() -> toSingleTrackRide(measurementSystem)
        else -> toMultiTrackRide(measurementSystem, trackNames)
    }

@OptIn(InternalSerializationApi::class)
private fun RideRoomModel.toSingleTrackRide(
    measurementSystem: SystemMeasurementSystem,
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

@OptIn(InternalSerializationApi::class)
private fun RideRoomModel.toMultiTrackRide(
    measurementSystem: SystemMeasurementSystem,
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

@OptIn(InternalSerializationApi::class)
private fun StatusRoomModel.toDomain() = Status(
    closedDate = closedDate?.toDate()?.let(::ClosedDate),
    current = OperationalState(current),
    former = former?.let(::FormerStatus),
    openedDate = openedDate?.toDate()?.let(::OpenedDate),
)