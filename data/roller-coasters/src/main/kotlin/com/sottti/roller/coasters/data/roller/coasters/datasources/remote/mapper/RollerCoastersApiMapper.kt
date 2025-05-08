package com.sottti.roller.coasters.data.roller.coasters.datasources.remote.mapper

import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.AmusementParkApiModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.CoordinatesApiModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.PictureApiModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.RollerCoasterApiModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.RollerCoasterStatsApiModel
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
import com.sottti.roller.coasters.domain.roller.coasters.model.Status
import com.sottti.roller.coasters.domain.roller.coasters.model.Train
import com.sottti.roller.coasters.domain.roller.coasters.model.Type
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem
import com.sottti.roller.coasters.utils.time.dates.mapper.toDate
import com.sottti.roller.coasters.utils.time.dates.mapper.toSeconds

internal fun RollerCoasterApiModel.toDomain(
    measurementSystem: ResolvedMeasurementSystem,
): RollerCoaster =
    RollerCoaster(
        id = RollerCoasterId(id),
        location = toDomainLocation(),
        name = toDomainName(),
        park = park.toDomain(),
        pictures = toDomainPictures(),
        specs = toDomainSpecs(measurementSystem),
        status = toDomainStatus(),
    )

private fun RollerCoasterApiModel.toDomainLocation(): Location =
    Location(
        city = City(city),
        coordinates = coords?.toDomain(),
        country = Country(country),
        relocations = stats?.relocations?.let { Relocations(it) },
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
        id = ParkId(id),
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

private fun RollerCoasterApiModel.toDomainSpecs(
    measurementSystem: ResolvedMeasurementSystem,
): Specs = Specs(
    capacity = stats?.capacity?.let { Capacity(RidersPerHour(it)) },
    cost = stats?.cost?.let { Cost(Euros(it)) },
    design = toDomainDesign(),
    dimensions = stats?.dimensions?.let { Dimensions(Meters(it)) },
    manufacturer = stats?.builtBy?.let { Manufacturer(it) },
    model = Model(model),
    ride = stats?.toDomain(measurementSystem),
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

private fun RollerCoasterStatsApiModel.toDomain(
    measurementSystem: ResolvedMeasurementSystem,
): Ride? =
    when {
        isMultiTrack() -> toDomainMultiTrack(measurementSystem)
        else -> toDomainSingleTrack(measurementSystem)
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

private fun RollerCoasterStatsApiModel.toDomainMultiTrack(
    measurementSystem: ResolvedMeasurementSystem,
): MultiTrackRide =
    MultiTrackRide(
        drop = drop?.map { it.toDrop(measurementSystem) },
        duration = duration?.map { Duration(Seconds(it.toSeconds())) },
        gForce = gForce?.map { GForce(it) },
        height = height?.map { it.toHeight(measurementSystem) },
        inversions = inversions?.map { Inversions(it) },
        length = length?.map { it.toLength(measurementSystem) },
        maxVertical = verticalAngle?.map { MaxVertical(Degrees(it)) },
        trackNames = name?.map { Name(it) },
        speed = speed?.map { it.toSpeed(measurementSystem) },
    )

private fun RollerCoasterStatsApiModel.toDomainSingleTrack(
    measurementSystem: ResolvedMeasurementSystem,
): SingleTrackRide =
    SingleTrackRide(
        drop = drop?.first()?.toDrop(measurementSystem),
        duration = duration?.let { Duration(Seconds(it.first().toSeconds())) },
        gForce = gForce?.let { GForce(it.first()) },
        height = height?.first()?.toHeight(measurementSystem),
        inversions = inversions?.let { Inversions(it.first()) },
        length = length?.first()?.toLength(measurementSystem),
        maxVertical = verticalAngle?.let { MaxVertical(Degrees(it.first())) },
        speed = speed?.first()?.toSpeed(measurementSystem),
    )

private fun RollerCoasterApiModel.toDomainStatus(): Status =
    Status(
        closedDate = status.date.closed?.toDate()?.let { ClosedDate(it) },
        current = OperationalState(status.state),
        former = stats?.formerStatus?.let { FormerStatus(it) },
        openedDate = status.date.opened.toDate()?.let { OpenedDate(it) },
    )