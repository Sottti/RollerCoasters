package com.sottti.roller.coasters.data.roller.coasters.stubs

import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.CITY
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.COASTER_ARRANGEMENT
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.COASTER_DESIGNER
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.COASTER_ELEMENT
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.COASTER_ID
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.COASTER_ID_ANOTHER
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.COASTER_NAME
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.COASTER_TRAIN
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.COASTER_TYPE
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.COPYRIGHT_DATE
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.COST
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.COUNTRY
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.DEGREES
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.DROP
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.DURATION_IN_SECONDS
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.GFORCE
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.HEIGHT
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.INVERSIONS
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.LATITUDE
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.LENGTH
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.LONGITUDE
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.MANUFACTURER
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.MODEL
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.OPENED_DATE
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.PARK_ID
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.PARK_NAME
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.PICTURE_AUTHOR
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.PICTURE_ID_ANOTHER_MAIN
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.PICTURE_ID_ANOTHER_NOT_MAIN
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.PICTURE_ID_MAIN
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.PICTURE_ID_NOT_MAIN
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.PICTURE_URL
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.REGION
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.RESTRAINTS
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.RIDERS_PER_HOUR
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.SPEED
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.STATE
import com.sottti.roller.coasters.data.roller.coasters.shared.test.stubs.STATUS
import com.sottti.roller.coasters.domain.model.Author
import com.sottti.roller.coasters.domain.model.City
import com.sottti.roller.coasters.domain.model.Coordinates
import com.sottti.roller.coasters.domain.model.Date
import com.sottti.roller.coasters.domain.model.Euros
import com.sottti.roller.coasters.domain.model.ImageUrl
import com.sottti.roller.coasters.domain.model.Kmh
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
import com.sottti.roller.coasters.domain.roller.coasters.model.Cost
import com.sottti.roller.coasters.domain.roller.coasters.model.Country
import com.sottti.roller.coasters.domain.roller.coasters.model.Degrees
import com.sottti.roller.coasters.domain.roller.coasters.model.Design
import com.sottti.roller.coasters.domain.roller.coasters.model.Designer
import com.sottti.roller.coasters.domain.roller.coasters.model.Drop
import com.sottti.roller.coasters.domain.roller.coasters.model.Duration
import com.sottti.roller.coasters.domain.roller.coasters.model.Element
import com.sottti.roller.coasters.domain.roller.coasters.model.GForce
import com.sottti.roller.coasters.domain.roller.coasters.model.Height
import com.sottti.roller.coasters.domain.roller.coasters.model.Inversions
import com.sottti.roller.coasters.domain.roller.coasters.model.Length
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
import com.sottti.roller.coasters.domain.roller.coasters.model.Speed
import com.sottti.roller.coasters.domain.roller.coasters.model.State
import com.sottti.roller.coasters.domain.roller.coasters.model.Status
import com.sottti.roller.coasters.domain.roller.coasters.model.Train
import com.sottti.roller.coasters.domain.roller.coasters.model.Type
import java.time.LocalDate

internal val rollerCoasterId = RollerCoasterId(COASTER_ID)
internal val anotherRollerCoasterId = RollerCoasterId(COASTER_ID_ANOTHER)
internal val parkId = ParkId(PARK_ID)

internal val coordinates: Coordinates =
    Coordinates(Latitude(LATITUDE), Longitude(LONGITUDE))

internal val park = AmusementPark(
    id = parkId,
    name = Name(PARK_NAME),
)

internal val name = RollerCoasterName(
    current = Name(COASTER_NAME),
    former = null,
)

internal val mainPicture = Picture(
    id = PictureId(PICTURE_ID_MAIN),
    name = PictureName(COASTER_NAME),
    url = ImageUrl(PICTURE_URL),
    copyright = PictureCopyright(
        author = Author(PICTURE_AUTHOR),
        date = Date.FullDate(LocalDate.parse(COPYRIGHT_DATE)),
    )
)

internal val anotherMainPicture = Picture(
    id = PictureId(PICTURE_ID_ANOTHER_MAIN),
    name = PictureName(COASTER_NAME),
    url = ImageUrl(PICTURE_URL),
    copyright = PictureCopyright(
        author = Author(PICTURE_AUTHOR),
        date = Date.FullDate(LocalDate.parse(COPYRIGHT_DATE)),
    )
)

internal val notMainPicture =
    Picture(
        id = PictureId(PICTURE_ID_NOT_MAIN),
        name = PictureName(COASTER_NAME),
        url = ImageUrl(PICTURE_URL),
        copyright = PictureCopyright(
            author = Author(PICTURE_AUTHOR),
            date = Date.FullDate(LocalDate.parse(COPYRIGHT_DATE)),
        )
    )

internal val anotherNotMainPicture =
    Picture(
        id = PictureId(PICTURE_ID_ANOTHER_NOT_MAIN),
        name = PictureName(COASTER_NAME),
        url = ImageUrl(PICTURE_URL),
        copyright = PictureCopyright(
            author = Author(PICTURE_AUTHOR),
            date = Date.FullDate(LocalDate.parse(COPYRIGHT_DATE)),
        )
    )

internal val design = Design(
    type = Type(COASTER_TYPE),
    train = Train(COASTER_TRAIN),
    elements = Element(COASTER_ELEMENT),
    arrangement = Arrangement(COASTER_ARRANGEMENT),
    restraints = Restraints(RESTRAINTS),
    designer = Designer(COASTER_DESIGNER),
)

internal val ride = SingleTrackRide(
    height = Height(Meters(HEIGHT)),
    length = Length(Meters(LENGTH)),
    speed = Speed(Kmh(SPEED)),
    duration = Duration(Seconds(DURATION_IN_SECONDS)),
    inversions = Inversions(INVERSIONS),
    gForce = GForce(GFORCE),
    drop = Drop(Meters(DROP)),
    maxVertical = MaxVertical(Degrees(DEGREES)),
)

internal val specs = Specs(
    design = design,
    model = Model(MODEL),
    manufacturer = Manufacturer(MANUFACTURER),
    cost = Cost(Euros(COST)),
    dimensions = null,
    capacity = Capacity(RidersPerHour(RIDERS_PER_HOUR)),
    ride = ride,
)

internal val status = Status(
    current = OperationalState(STATUS),
    former = null,
    openedDate = OpenedDate(Date.FullDate(LocalDate.parse(OPENED_DATE))),
    closedDate = null,
)

internal val location = Location(
    city = City(CITY),
    country = Country(COUNTRY),
    region = Region(REGION),
    state = State(STATE),
    coordinates = coordinates,
    relocations = null,
)

internal val rollerCoaster = RollerCoaster(
    id = rollerCoasterId,
    location = location,
    name = name,
    park = park,
    pictures = Pictures(
        main = mainPicture,
        other = listOf(notMainPicture),
    ),
    specs = specs,
    status = status,
)

internal val rollerCoasterWithoutOtherPictures =
    rollerCoaster.copy(pictures = rollerCoaster.pictures.copy(other = emptyList()))

internal val anotherRollerCoaster = rollerCoaster.copy(
    id = anotherRollerCoasterId,
    pictures = Pictures(
        main = anotherMainPicture,
        other = listOf(anotherNotMainPicture),
    )
)