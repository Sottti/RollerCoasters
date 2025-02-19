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
import com.sottti.roller.coasters.domain.model.AmusementPark
import com.sottti.roller.coasters.domain.model.Arrangement
import com.sottti.roller.coasters.domain.model.Author
import com.sottti.roller.coasters.domain.model.Capacity
import com.sottti.roller.coasters.domain.model.City
import com.sottti.roller.coasters.domain.model.ClosedDate
import com.sottti.roller.coasters.domain.model.Coordinates
import com.sottti.roller.coasters.domain.model.Cost
import com.sottti.roller.coasters.domain.model.Country
import com.sottti.roller.coasters.domain.model.Degrees
import com.sottti.roller.coasters.domain.model.Design
import com.sottti.roller.coasters.domain.model.Designer
import com.sottti.roller.coasters.domain.model.Dimensions
import com.sottti.roller.coasters.domain.model.Drop
import com.sottti.roller.coasters.domain.model.Duration
import com.sottti.roller.coasters.domain.model.Element
import com.sottti.roller.coasters.domain.model.Euros
import com.sottti.roller.coasters.domain.model.FormerStatus
import com.sottti.roller.coasters.domain.model.GForce
import com.sottti.roller.coasters.domain.model.Height
import com.sottti.roller.coasters.domain.model.ImageUrl
import com.sottti.roller.coasters.domain.model.Inversions
import com.sottti.roller.coasters.domain.model.Kmh
import com.sottti.roller.coasters.domain.model.Latitude
import com.sottti.roller.coasters.domain.model.Length
import com.sottti.roller.coasters.domain.model.Location
import com.sottti.roller.coasters.domain.model.Longitude
import com.sottti.roller.coasters.domain.model.Manufacturer
import com.sottti.roller.coasters.domain.model.MaxVertical
import com.sottti.roller.coasters.domain.model.Meters
import com.sottti.roller.coasters.domain.model.Model
import com.sottti.roller.coasters.domain.model.MultiTrackRide
import com.sottti.roller.coasters.domain.model.Name
import com.sottti.roller.coasters.domain.model.OpenedDate
import com.sottti.roller.coasters.domain.model.OperationalState
import com.sottti.roller.coasters.domain.model.ParkId
import com.sottti.roller.coasters.domain.model.Picture
import com.sottti.roller.coasters.domain.model.PictureCopyright
import com.sottti.roller.coasters.domain.model.PictureId
import com.sottti.roller.coasters.domain.model.PictureName
import com.sottti.roller.coasters.domain.model.Pictures
import com.sottti.roller.coasters.domain.model.Region
import com.sottti.roller.coasters.domain.model.Relocations
import com.sottti.roller.coasters.domain.model.Restraints
import com.sottti.roller.coasters.domain.model.Ride
import com.sottti.roller.coasters.domain.model.RidersPerHour
import com.sottti.roller.coasters.domain.model.RollerCoaster
import com.sottti.roller.coasters.domain.model.RollerCoasterId
import com.sottti.roller.coasters.domain.model.RollerCoasterName
import com.sottti.roller.coasters.domain.model.Seconds
import com.sottti.roller.coasters.domain.model.SingleTrackRide
import com.sottti.roller.coasters.domain.model.Specs
import com.sottti.roller.coasters.domain.model.Speed
import com.sottti.roller.coasters.domain.model.State
import com.sottti.roller.coasters.domain.model.Status
import com.sottti.roller.coasters.domain.model.Train
import com.sottti.roller.coasters.domain.model.Type
import com.sottti.roller.coasters.utils.dates.mappers.toDate
import kotlinx.serialization.InternalSerializationApi

@OptIn(InternalSerializationApi::class)
internal fun RollerCoasterRoomModel.toDomain(
    pictures: List<PictureRoomModel>
) = RollerCoaster(
    id = RollerCoasterId(id),
    location = location.toDomain(),
    name = name.toDomain(),
    park = park.toDomain(),
    pictures = pictures.toDomain(mainPicture),
    specs = specs.toDomain(),
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
): Pictures =
    Pictures(
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
private fun SpecsRoomModel.toDomain() =
    Specs(
        capacity = capacity?.let { Capacity(RidersPerHour(it)) },
        cost = cost?.let { Cost(Euros(it)) },
        design = design.toDomain(),
        dimensions = dimensions?.let { Dimensions(Meters(it)) },
        manufacturer = manufacturer?.let(::Manufacturer),
        model = Model(model),
        ride = ride?.toDomain(),
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
private fun RideRoomModel.toDomain(): Ride =
    if (trackNames.isNullOrEmpty()) toSingleTrackRide() else toMultiTrackRide(trackNames)

@OptIn(InternalSerializationApi::class)
private fun RideRoomModel.toSingleTrackRide() =
    SingleTrackRide(
        drop = drop?.firstOrNull()?.let { Drop(Meters(it)) },
        duration = duration?.firstOrNull()?.let { Duration(Seconds(it)) },
        gForce = gForce?.firstOrNull()?.let(::GForce),
        height = height?.firstOrNull()?.let { Height(Meters(it)) },
        inversions = inversions?.firstOrNull()?.let(::Inversions),
        length = length?.firstOrNull()?.let { Length(Meters(it)) },
        maxVertical = maxVertical?.firstOrNull()?.let { MaxVertical(Degrees(it)) },
        speed = speed?.firstOrNull()?.let { Speed(Kmh(it)) },
    )

@OptIn(InternalSerializationApi::class)
private fun RideRoomModel.toMultiTrackRide(
    trackNames: List<String>,
) = MultiTrackRide(
    drop = drop?.map { Drop(Meters(it)) },
    duration = duration?.map { Duration(Seconds(it)) },
    gForce = gForce?.map(::GForce),
    height = height?.map { Height(Meters(it)) },
    inversions = inversions?.map(::Inversions),
    length = length?.map { Length(Meters(it)) },
    maxVertical = maxVertical?.map { MaxVertical(Degrees(it)) },
    speed = speed?.map { Speed(Kmh(it)) },
    trackNames = trackNames.map(::Name),
)

@OptIn(InternalSerializationApi::class)
private fun StatusRoomModel.toDomain() = Status(
    closedDate = closedDate?.toDate()?.let(::ClosedDate),
    current = OperationalState(current),
    former = former?.let(::FormerStatus),
    openedDate = openedDate?.toDate()?.let(::OpenedDate),
)