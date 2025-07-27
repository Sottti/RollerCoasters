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
import com.sottti.roller.coasters.domain.roller.coasters.model.Status
import com.sottti.roller.coasters.domain.roller.coasters.model.Train
import com.sottti.roller.coasters.domain.roller.coasters.model.Type
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem.ImperialUs
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem.Metric
import java.time.LocalDate

public fun parkId(): ParkId = ParkId(PARK_ID)

public fun coordinates(): Coordinates =
    Coordinates(Latitude(LATITUDE), Longitude(LONGITUDE))

public fun park(): AmusementPark =
    AmusementPark(
        id = parkId(),
        name = Name(PARK_NAME),
    )

public fun name(): RollerCoasterName =
    RollerCoasterName(
        current = Name(COASTER_NAME),
        former = null,
    )

public fun mainPicture(): Picture =
    Picture(
        id = PictureId(PICTURE_ID_MAIN),
        name = PictureName(COASTER_NAME),
        url = ImageUrl(PICTURE_URL),
        copyright = PictureCopyright(
            author = Author(PICTURE_AUTHOR),
            date = Date.FullDate(LocalDate.parse(COPYRIGHT_DATE)),
        ),
    )

public fun anotherMainPicture(): Picture =
    Picture(
        id = PictureId(PICTURE_ID_ANOTHER_MAIN),
        name = PictureName(COASTER_NAME),
        url = ImageUrl(PICTURE_URL),
        copyright = PictureCopyright(
            author = Author(PICTURE_AUTHOR),
            date = Date.FullDate(LocalDate.parse(COPYRIGHT_DATE)),
        )
    )

public fun notMainPicture(): Picture =
    Picture(
        id = PictureId(PICTURE_ID_NOT_MAIN),
        name = PictureName(COASTER_NAME),
        url = ImageUrl(PICTURE_URL),
        copyright = PictureCopyright(
            author = Author(PICTURE_AUTHOR),
            date = Date.FullDate(LocalDate.parse(COPYRIGHT_DATE)),
        )
    )

public fun anotherNotMainPicture(): Picture =
    Picture(
        id = PictureId(PICTURE_ID_ANOTHER_NOT_MAIN),
        name = PictureName(COASTER_NAME),
        url = ImageUrl(PICTURE_URL),
        copyright = PictureCopyright(
            author = Author(PICTURE_AUTHOR),
            date = Date.FullDate(LocalDate.parse(COPYRIGHT_DATE)),
        )
    )

public fun design(): Design =
    Design(
        type = Type(COASTER_TYPE),
        train = Train(COASTER_TRAIN),
        elements = Element(COASTER_ELEMENT),
        arrangement = Arrangement(COASTER_ARRANGEMENT),
        restraints = Restraints(RESTRAINTS),
        designer = Designer(COASTER_DESIGNER),
    )

public fun ride(
    measurementSystem: ResolvedMeasurementSystem,
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
    measurementSystem: ResolvedMeasurementSystem,
): Specs = Specs(
    design = design(),
    model = Model(MODEL),
    manufacturer = Manufacturer(MANUFACTURER),
    cost = Cost(Euros(COST)),
    dimensions = null,
    capacity = Capacity(RidersPerHour(RIDERS_PER_HOUR)),
    ride = ride(measurementSystem),
)

public fun status(): Status =
    Status(
        current = OperationalState(OPERATIONAL_STATE_CURRENT),
        former = null,
        openedDate = OpenedDate(Date.FullDate(LocalDate.parse(OPENED_DATE))),
        closedDate = null,
    )

public fun location(): Location =
    Location(
        city = City(CITY),
        country = Country(COUNTRY),
        coordinates = coordinates(),
        relocations = null,
    )

public fun rollerCoasterId(): RollerCoasterId = rollerCoaster().id

public fun rollerCoaster(
    measurementSystem: ResolvedMeasurementSystem = Metric,
): RollerCoaster = RollerCoaster(
    id = RollerCoasterId(COASTER_ID),
    location = location(),
    name = name(),
    park = park(),
    pictures = Pictures(
        main = mainPicture(),
        other = listOf(notMainPicture()),
    ),
    specs = specs(measurementSystem),
    status = status(),
)

public fun rollerCoasterWithoutOtherPictures(
    measurementSystem: ResolvedMeasurementSystem,
): RollerCoaster =
    rollerCoaster(measurementSystem).let { coaster ->
        coaster.copy(pictures = coaster.pictures.copy(other = emptyList()))
    }

public fun anotherRollerCoasterId(): RollerCoasterId = anotherRollerCoaster().id

public fun anotherRollerCoaster(
    measurementSystem: ResolvedMeasurementSystem = Metric,
): RollerCoaster = rollerCoaster(measurementSystem = measurementSystem).copy(
    id = RollerCoasterId(COASTER_ID_ANOTHER),
    pictures = Pictures(
        main = anotherMainPicture(),
        other = listOf(anotherNotMainPicture()),
    )
)

public fun gForce(): GForce = GForce(GFORCE)
public fun imperialDrop(): ImperialDrop = ImperialDrop(Feet.fromMeters(Meters(DROP)))
public fun imperialHeight(): ImperialHeight = ImperialHeight(Feet.fromMeters(Meters(HEIGHT)))
public fun imperialLength(): ImperialLength = ImperialLength(Feet.fromMeters(Meters(LENGTH)))
public fun imperialSpeed(): ImperialSpeed = ImperialSpeed(Mph.fromKph(Kmh(SPEED)))
public fun inversions(): Inversions = Inversions(INVERSIONS)
public fun maxVertical(): MaxVertical = MaxVertical(Degrees(DEGREES))
public fun metricDrop(): MetricDrop = MetricDrop(Meters(DROP))
public fun metricHeight(): MetricHeight = MetricHeight(Meters(HEIGHT))
public fun metricLength(): MetricLength = MetricLength(Meters(LENGTH))
public fun metricSpeed(): MetricSpeed = MetricSpeed(Kmh(SPEED))
