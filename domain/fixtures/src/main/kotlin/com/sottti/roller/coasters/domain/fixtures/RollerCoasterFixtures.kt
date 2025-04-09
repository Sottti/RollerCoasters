package com.sottti.roller.coasters.domain.fixtures

import com.sottti.roller.coasters.domain.model.Author
import com.sottti.roller.coasters.domain.model.City
import com.sottti.roller.coasters.domain.model.Coordinates
import com.sottti.roller.coasters.domain.model.Date
import com.sottti.roller.coasters.domain.model.Euros
import com.sottti.roller.coasters.domain.model.Feet
import com.sottti.roller.coasters.domain.model.ImageUrl
import com.sottti.roller.coasters.domain.model.Kmh
import com.sottti.roller.coasters.domain.model.Latitude
import com.sottti.roller.coasters.domain.model.Longitude
import com.sottti.roller.coasters.domain.model.Meters
import com.sottti.roller.coasters.domain.model.Mph
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
import com.sottti.roller.coasters.domain.roller.coasters.model.Cost
import com.sottti.roller.coasters.domain.roller.coasters.model.Country
import com.sottti.roller.coasters.domain.roller.coasters.model.Degrees
import com.sottti.roller.coasters.domain.roller.coasters.model.Design
import com.sottti.roller.coasters.domain.roller.coasters.model.Designer
import com.sottti.roller.coasters.domain.roller.coasters.model.Drop.ImperialDrop
import com.sottti.roller.coasters.domain.roller.coasters.model.Drop.MetricDrop
import com.sottti.roller.coasters.domain.roller.coasters.model.Duration
import com.sottti.roller.coasters.domain.roller.coasters.model.Element
import com.sottti.roller.coasters.domain.roller.coasters.model.GForce
import com.sottti.roller.coasters.domain.roller.coasters.model.Height.ImperialHeight
import com.sottti.roller.coasters.domain.roller.coasters.model.Height.MetricHeight
import com.sottti.roller.coasters.domain.roller.coasters.model.Inversions
import com.sottti.roller.coasters.domain.roller.coasters.model.Length.ImperialLength
import com.sottti.roller.coasters.domain.roller.coasters.model.Length.MetricLength
import com.sottti.roller.coasters.domain.roller.coasters.model.Location
import com.sottti.roller.coasters.domain.roller.coasters.model.Manufacturer
import com.sottti.roller.coasters.domain.roller.coasters.model.MaxVertical
import com.sottti.roller.coasters.domain.roller.coasters.model.Model
import com.sottti.roller.coasters.domain.roller.coasters.model.OpenedDate
import com.sottti.roller.coasters.domain.roller.coasters.model.OperationalState
import com.sottti.roller.coasters.domain.roller.coasters.model.ParkId
import com.sottti.roller.coasters.domain.roller.coasters.model.Pictures
import com.sottti.roller.coasters.domain.roller.coasters.model.Restraints
import com.sottti.roller.coasters.domain.roller.coasters.model.RidersPerHour
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoasterId
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoasterName
import com.sottti.roller.coasters.domain.roller.coasters.model.SingleTrackRide
import com.sottti.roller.coasters.domain.roller.coasters.model.Specs
import com.sottti.roller.coasters.domain.roller.coasters.model.Speed.ImperialSpeed
import com.sottti.roller.coasters.domain.roller.coasters.model.Speed.MetricSpeed
import com.sottti.roller.coasters.domain.roller.coasters.model.State
import com.sottti.roller.coasters.domain.roller.coasters.model.Status
import com.sottti.roller.coasters.domain.roller.coasters.model.Train
import com.sottti.roller.coasters.domain.roller.coasters.model.Type
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem.ImperialUs
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem.Metric
import java.time.LocalDate

public val rollerCoasterId: RollerCoasterId = RollerCoasterId(COASTER_ID)
public val anotherRollerCoasterId: RollerCoasterId = RollerCoasterId(COASTER_ID_ANOTHER)
public val parkId: ParkId = ParkId(PARK_ID)

public val coordinates: Coordinates =
    Coordinates(Latitude(LATITUDE), Longitude(LONGITUDE))

public val park: AmusementPark =
    AmusementPark(
        id = parkId,
        name = Name(PARK_NAME),
    )

public val name: RollerCoasterName =
    RollerCoasterName(
        current = Name(COASTER_NAME),
        former = null,
    )

public val mainPicture: Picture =
    Picture(
        id = PictureId(PICTURE_ID_MAIN),
        name = PictureName(COASTER_NAME),
        url = ImageUrl(PICTURE_URL),
        copyright = PictureCopyright(
            author = Author(PICTURE_AUTHOR),
            date = Date.FullDate(LocalDate.parse(COPYRIGHT_DATE)),
        )
    )

public val anotherMainPicture: Picture =
    Picture(
        id = PictureId(PICTURE_ID_ANOTHER_MAIN),
        name = PictureName(COASTER_NAME),
        url = ImageUrl(PICTURE_URL),
        copyright = PictureCopyright(
            author = Author(PICTURE_AUTHOR),
            date = Date.FullDate(LocalDate.parse(COPYRIGHT_DATE)),
        )
    )

public val notMainPicture: Picture =
    Picture(
        id = PictureId(PICTURE_ID_NOT_MAIN),
        name = PictureName(COASTER_NAME),
        url = ImageUrl(PICTURE_URL),
        copyright = PictureCopyright(
            author = Author(PICTURE_AUTHOR),
            date = Date.FullDate(LocalDate.parse(COPYRIGHT_DATE)),
        )
    )

public val anotherNotMainPicture: Picture =
    Picture(
        id = PictureId(PICTURE_ID_ANOTHER_NOT_MAIN),
        name = PictureName(COASTER_NAME),
        url = ImageUrl(PICTURE_URL),
        copyright = PictureCopyright(
            author = Author(PICTURE_AUTHOR),
            date = Date.FullDate(LocalDate.parse(COPYRIGHT_DATE)),
        )
    )

public val design: Design =
    Design(
        type = Type(COASTER_TYPE),
        train = Train(COASTER_TRAIN),
        elements = Element(COASTER_ELEMENT),
        arrangement = Arrangement(COASTER_ARRANGEMENT),
        restraints = Restraints(RESTRAINTS),
        designer = Designer(COASTER_DESIGNER),
    )

public fun ride(
    measurementSystem: SystemMeasurementSystem,
): SingleTrackRide = when (measurementSystem) {
    ImperialUk, ImperialUs -> singleTrackRideImperial()
    Metric -> singleTrackRideMetric()
}

private fun singleTrackRideMetric(): SingleTrackRide =
    SingleTrackRide(
        height = MetricHeight(Meters(HEIGHT)),
        length = MetricLength(Meters(LENGTH)),
        speed = MetricSpeed(Kmh(SPEED)),
        duration = Duration(Seconds(DURATION_IN_SECONDS)),
        inversions = Inversions(INVERSIONS),
        gForce = GForce(GFORCE),
        drop = MetricDrop(Meters(DROP)),
        maxVertical = MaxVertical(Degrees(DEGREES)),
    )

private fun singleTrackRideImperial(): SingleTrackRide =
    SingleTrackRide(
        height = ImperialHeight(Feet.fromMeters(Meters(HEIGHT))),
        length = ImperialLength(Feet.fromMeters(Meters(LENGTH))),
        speed = ImperialSpeed(Mph.fromKph(Kmh(SPEED))),
        duration = Duration(Seconds(DURATION_IN_SECONDS)),
        inversions = Inversions(INVERSIONS),
        gForce = GForce(GFORCE),
        drop = ImperialDrop(Feet.fromMeters(Meters(DROP))),
        maxVertical = MaxVertical(Degrees(DEGREES)),
    )

public fun specs(
    measurementSystem: SystemMeasurementSystem,
): Specs = Specs(
    design = design,
    model = Model(MODEL),
    manufacturer = Manufacturer(MANUFACTURER),
    cost = Cost(Euros(COST)),
    dimensions = null,
    capacity = Capacity(RidersPerHour(RIDERS_PER_HOUR)),
    ride = ride(measurementSystem),
)

public val status: Status =
    Status(
        current = OperationalState(STATUS),
        former = null,
        openedDate = OpenedDate(Date.FullDate(LocalDate.parse(OPENED_DATE))),
        closedDate = null,
    )

public val location: Location =
    Location(
        city = City(CITY),
        country = Country(COUNTRY),
        region = Region(REGION),
        state = State(STATE),
        coordinates = coordinates,
        relocations = null,
    )

public val rollerCoaster: RollerCoaster = rollerCoaster()

public fun rollerCoaster(
    measurementSystem: SystemMeasurementSystem = Metric,
): RollerCoaster = RollerCoaster(
    id = rollerCoasterId,
    location = location,
    name = name,
    park = park,
    pictures = Pictures(
        main = mainPicture,
        other = listOf(notMainPicture),
    ),
    specs = specs(measurementSystem),
    status = status,
)

public fun rollerCoasterWithoutOtherPictures(
    measurementSystem: SystemMeasurementSystem,
): RollerCoaster =
    rollerCoaster(measurementSystem).let { coaster ->
        coaster.copy(pictures = coaster.pictures.copy(other = emptyList()))
    }

public val anotherRollerCoaster: RollerCoaster = anotherRollerCoaster()

public fun anotherRollerCoaster(
    measurementSystem: SystemMeasurementSystem = Metric,
): RollerCoaster = rollerCoaster(measurementSystem = measurementSystem).copy(
    id = anotherRollerCoasterId,
    pictures = Pictures(
        main = anotherMainPicture,
        other = listOf(anotherNotMainPicture),
    )
)

public val gForce: GForce = GForce(GFORCE)
public val imperialDrop: ImperialDrop = ImperialDrop(Feet.fromMeters(Meters(DROP)))
public val imperialHeight: ImperialHeight = ImperialHeight(Feet.fromMeters(Meters(HEIGHT)))
public val imperialLength: ImperialLength = ImperialLength(Feet.fromMeters(Meters(LENGTH)))
public val imperialSpeed: ImperialSpeed = ImperialSpeed(Mph.fromKph(Kmh(SPEED)))
public val inversions: Inversions = Inversions(INVERSIONS)
public val maxVertical: MaxVertical = MaxVertical(Degrees(DEGREES))
public val metricDrop: MetricDrop = MetricDrop(Meters(SPEED))
public val metricHeight: MetricHeight = MetricHeight(Meters(HEIGHT))
public val metricLength: MetricLength = MetricLength(Meters(LENGTH))
public val metricSpeed: MetricSpeed = MetricSpeed(Kmh(SPEED))