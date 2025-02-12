package com.sottti.roller.coasters.data.roller.coasters.datasources.remote.mapper

import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.AmusementParkApiModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.CoordinatesApiModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.PictureApiModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.RollerCoasterApiModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.RollerCoasterStatsApiModel
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
import com.sottti.roller.coasters.domain.model.Id
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
import com.sottti.roller.coasters.utils.dates.mappers.toSeconds

internal fun RollerCoasterApiModel.toDomain(): RollerCoaster =
    RollerCoaster(
        id = RollerCoasterId(id),
        location = toDomainLocation(),
        name = toDomainName(),
        park = park.toDomain(),
        pictures = toDomainPictures(),
        specs = toDomainSpecs(),
        status = toDomainStatus(),
    )

private fun RollerCoasterApiModel.toDomainLocation(): Location =
    Location(
        city = City(city),
        coordinates = coords?.toDomain(),
        country = Country(country),
        region = Region(region),
        relocations = stats?.relocations?.let { Relocations(it) },
        state = State(state),
    )

private fun CoordinatesApiModel.toDomain(): Coordinates? =
    Coordinates(latitude = Latitude(lat), longitude = Longitude(lng))

private fun RollerCoasterApiModel.toDomainName(): RollerCoasterName =
    RollerCoasterName(
        current = Name(name),
        former = stats?.formerNames?.let { Name(it) },
    )

private fun AmusementParkApiModel.toDomain(): AmusementPark =
    AmusementPark(
        id = Id(id),
        name = Name(name),
    )

private fun RollerCoasterApiModel.toDomainPictures() =
    Pictures(
        main = mainPicture?.toDomain(),
        other = this.pictures.map { it.toDomain() },
    )

private fun PictureApiModel.toDomain(): Picture =
    Picture(
        copyright = toDomainCopyright(),
        id = PictureId(id),
        name = PictureName(name),
        url = ImageUrl(url),
    )

private fun PictureApiModel.toDomainCopyright(): PictureCopyright =
    PictureCopyright(
        author = Author(copyName),
        date = copyDate.toDate(),
    )

private fun RollerCoasterApiModel.toDomainSpecs(): Specs = Specs(
    capacity = stats?.capacity?.let { Capacity(RidersPerHour(it)) },
    cost = stats?.cost?.let { Cost(Euros(it)) },
    design = toDomainDesign(),
    dimensions = stats?.dimensions?.let { Dimensions(Meters(it)) },
    manufacturer = stats?.builtBy?.let { Manufacturer(it) },
    model = Model(model),
    ride = stats?.toDomain(),
)

private fun RollerCoasterApiModel.toDomainDesign(): Design =
    Design(
        arrangement = stats?.arrangement?.let { Arrangement(it) },
        designer = stats?.designer?.let { Designer(it) },
        elements = stats?.elements?.let { Element(it.first()) },
        restraints = stats?.restraints?.let { Restraints(it) },
        train = Train(design),
        type = Type(type),
    )

private fun RollerCoasterStatsApiModel.toDomain(): Ride? =
    when {
        isMultiTrack() -> toDomainMultiTrack()
        else -> toDomainSingleTrack()
    }

private fun RollerCoasterStatsApiModel.isMultiTrack(): Boolean =
    (duration?.size ?: 0) > 1 ||
            (gForce?.size ?: 0) > 1 ||
            (height?.size ?: 0) > 1 ||
            (inversions?.size ?: 0) > 1 ||
            (length?.size ?: 0) > 1 ||
            (speed?.size ?: 0) > 1 ||
            (verticalAngle?.size ?: 0) > 1 ||
            (drop?.size ?: 0) > 1

private fun RollerCoasterStatsApiModel.toDomainMultiTrack(): MultiTrackRide =
    MultiTrackRide(
        drop = drop?.map { Drop(Meters(it)) },
        duration = duration?.map { Duration(Seconds(it.toSeconds())) },
        gForce = gForce?.map { GForce(it) },
        height = height?.map { Height(Meters(it)) },
        inversions = inversions?.map { Inversions(it) },
        length = length?.map { Length(Meters(it)) },
        maxVertical = verticalAngle?.map { MaxVertical(Degrees(it)) },
        trackNames = name?.map { Name(it) },
        speed = speed?.map { Speed(Kmh(it)) },
    )

private fun RollerCoasterStatsApiModel.toDomainSingleTrack(): SingleTrackRide =
    SingleTrackRide(
        drop = drop?.let { Drop(Meters(it.first())) },
        duration = duration?.let { Duration(Seconds(it.first().toSeconds())) },
        gForce = gForce?.let { GForce(it.first()) },
        height = height?.let { Height(Meters(it.first())) },
        inversions = inversions?.let { Inversions(it.first()) },
        length = length?.let { Length(Meters(it.first())) },
        maxVertical = verticalAngle?.let { MaxVertical(Degrees(it.first())) },
        speed = speed?.let { Speed(Kmh(it.first())) },
    )

private fun RollerCoasterApiModel.toDomainStatus(): Status =
    Status(
        closedDate = status.date.closed?.toDate()?.let { ClosedDate(it) },
        current = OperationalState(state),
        former = stats?.formerStatus?.let { FormerStatus(it) },
        openedDate = status.date.opened.toDate()?.let { OpenedDate(it) },
    )