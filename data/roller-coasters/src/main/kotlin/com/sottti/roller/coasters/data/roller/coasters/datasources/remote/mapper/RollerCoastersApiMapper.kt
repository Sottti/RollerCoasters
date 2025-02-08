package com.sottti.roller.coasters.data.roller.coasters.datasources.remote.mapper

import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.AmusementParkApiModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.CoordinatesApiModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.PictureApiModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.RollerCoasterApiModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.RollerCoasterStatsApiModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.RollerCoasterStatusApiModel
import com.sottti.roller.coasters.data.roller.coasters.model.AmusementPark
import com.sottti.roller.coasters.data.roller.coasters.model.Author
import com.sottti.roller.coasters.data.roller.coasters.model.Capacity
import com.sottti.roller.coasters.data.roller.coasters.model.City
import com.sottti.roller.coasters.data.roller.coasters.model.CloseDate
import com.sottti.roller.coasters.data.roller.coasters.model.Coordinates
import com.sottti.roller.coasters.data.roller.coasters.model.Cost
import com.sottti.roller.coasters.data.roller.coasters.model.Country
import com.sottti.roller.coasters.data.roller.coasters.model.Design
import com.sottti.roller.coasters.data.roller.coasters.model.Designer
import com.sottti.roller.coasters.data.roller.coasters.model.Dimensions
import com.sottti.roller.coasters.data.roller.coasters.model.Drop
import com.sottti.roller.coasters.data.roller.coasters.model.Duration
import com.sottti.roller.coasters.data.roller.coasters.model.Euros
import com.sottti.roller.coasters.data.roller.coasters.model.FormerNames
import com.sottti.roller.coasters.data.roller.coasters.model.FormerStatus
import com.sottti.roller.coasters.data.roller.coasters.model.GForce
import com.sottti.roller.coasters.data.roller.coasters.model.Height
import com.sottti.roller.coasters.data.roller.coasters.model.Id
import com.sottti.roller.coasters.data.roller.coasters.model.Inversions
import com.sottti.roller.coasters.data.roller.coasters.model.Kmh
import com.sottti.roller.coasters.data.roller.coasters.model.Latitude
import com.sottti.roller.coasters.data.roller.coasters.model.Length
import com.sottti.roller.coasters.data.roller.coasters.model.Location
import com.sottti.roller.coasters.data.roller.coasters.model.Longitude
import com.sottti.roller.coasters.data.roller.coasters.model.Manufacturer
import com.sottti.roller.coasters.data.roller.coasters.model.Meters
import com.sottti.roller.coasters.data.roller.coasters.model.Model
import com.sottti.roller.coasters.data.roller.coasters.model.Name
import com.sottti.roller.coasters.data.roller.coasters.model.OpenDate
import com.sottti.roller.coasters.data.roller.coasters.model.OperationalState
import com.sottti.roller.coasters.data.roller.coasters.model.Picture
import com.sottti.roller.coasters.data.roller.coasters.model.PictureCopyright
import com.sottti.roller.coasters.data.roller.coasters.model.PictureId
import com.sottti.roller.coasters.data.roller.coasters.model.PictureName
import com.sottti.roller.coasters.data.roller.coasters.model.Pictures
import com.sottti.roller.coasters.data.roller.coasters.model.Region
import com.sottti.roller.coasters.data.roller.coasters.model.Relocations
import com.sottti.roller.coasters.data.roller.coasters.model.RidersPerHour
import com.sottti.roller.coasters.data.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.data.roller.coasters.model.RollerCoasterArrangement
import com.sottti.roller.coasters.data.roller.coasters.model.RollerCoasterElement
import com.sottti.roller.coasters.data.roller.coasters.model.Seconds
import com.sottti.roller.coasters.data.roller.coasters.model.Speed
import com.sottti.roller.coasters.data.roller.coasters.model.State
import com.sottti.roller.coasters.data.roller.coasters.model.Stats
import com.sottti.roller.coasters.data.roller.coasters.model.Status
import com.sottti.roller.coasters.data.roller.coasters.model.Type
import com.sottti.roller.coasters.data.roller.coasters.model.Url
import com.sottti.roller.coasters.utils.dates.mappers.toDate
import com.sottti.roller.coasters.utils.dates.mappers.toLocalDate

internal fun RollerCoasterApiModel.toDomain(): RollerCoaster =
    RollerCoaster(
        design = Design(design),
        id = Id(id),
        location = toDomainLocation(),
        manufacturer = Manufacturer(make),
        model = Model(model),
        name = Name(name),
        park = park.toDomain(),
        pictures = toDomainPictures(),
        stats = stats?.toDomain(),
        status = status.toDomain(),
        type = Type(type),
    )

private fun RollerCoasterApiModel.toDomainPictures() =
    Pictures(
        main = mainPicture?.toDomain(),
        other = this.pictures.map { it.toDomain() }
    )

private fun PictureApiModel.toDomain(): Picture =
    Picture(
        copyright = toDomainCopyright(),
        id = PictureId(id),
        name = PictureName(name),
        url = Url(url),
    )

private fun PictureApiModel.toDomainCopyright(): PictureCopyright =
    PictureCopyright(
        author = Author(copyName),
        date = copyDate.toLocalDate(),
    )

private fun RollerCoasterStatsApiModel.toDomain(): Stats =
    Stats(
        arrangement = arrangement?.let { RollerCoasterArrangement(it) },
        capacity = capacity?.let { Capacity(RidersPerHour(it)) },
        cost = cost?.let { Cost(Euros(it)) },
        designer = designer?.let { Designer(it) },
        dimensions = dimensions?.let { Dimensions(Meters(it)) },
        drop = drop?.let { Drop(Meters(it)) },
        duration = duration?.let { Duration(Seconds(it)) },
        elements = elements?.map { RollerCoasterElement(it) },
        formerNames = formerNames?.let { FormerNames(it) },
        formerStatus = formerStatus?.let { FormerStatus(it) },
        gForce = gForce?.let { GForce(it) },
        height = height?.let { Height(Meters(it)) },
        inversions = inversions?.let { Inversions(it) },
        length = length?.let { Length(Meters(it)) },
        relocations = relocations?.let { Relocations(it) },
        speed = speed?.let { Speed(Kmh(it)) },
    )

private fun RollerCoasterStatusApiModel.toDomain(): Status =
    Status(
        state = OperationalState(state),
        openedDate = OpenDate(date.opened.toDate()),
        closedDate = date.closed?.let { CloseDate(date.closed.toDate()) },
    )

private fun RollerCoasterApiModel.toDomainLocation(): Location =
    Location(
        city = City(city),
        country = Country(country),
        region = Region(region),
        state = State(state),
        coordinates = coords?.toDomain(),
    )

private fun CoordinatesApiModel.toDomain(): Coordinates? =
    Coordinates(latitude = Latitude(lat), longitude = Longitude(lng))

private fun AmusementParkApiModel.toDomain(): AmusementPark =
    AmusementPark(
        id = Id(id),
        name = Name(name),
    )