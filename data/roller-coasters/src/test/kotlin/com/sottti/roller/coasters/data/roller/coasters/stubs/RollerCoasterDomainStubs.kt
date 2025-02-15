package com.sottti.roller.coasters.data.roller.coasters.stubs

import com.sottti.roller.coasters.domain.model.AmusementPark
import com.sottti.roller.coasters.domain.model.Arrangement
import com.sottti.roller.coasters.domain.model.Author
import com.sottti.roller.coasters.domain.model.City
import com.sottti.roller.coasters.domain.model.Coordinates
import com.sottti.roller.coasters.domain.model.Country
import com.sottti.roller.coasters.domain.model.Date
import com.sottti.roller.coasters.domain.model.Design
import com.sottti.roller.coasters.domain.model.Designer
import com.sottti.roller.coasters.domain.model.Drop
import com.sottti.roller.coasters.domain.model.Duration
import com.sottti.roller.coasters.domain.model.Element
import com.sottti.roller.coasters.domain.model.Height
import com.sottti.roller.coasters.domain.model.ImageUrl
import com.sottti.roller.coasters.domain.model.Inversions
import com.sottti.roller.coasters.domain.model.Kmh
import com.sottti.roller.coasters.domain.model.Latitude
import com.sottti.roller.coasters.domain.model.Length
import com.sottti.roller.coasters.domain.model.Location
import com.sottti.roller.coasters.domain.model.Longitude
import com.sottti.roller.coasters.domain.model.Manufacturer
import com.sottti.roller.coasters.domain.model.Meters
import com.sottti.roller.coasters.domain.model.Model
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
import java.time.LocalDate

internal val rollerCoasterId = RollerCoasterId(COASTER_ID)
internal val parkId = ParkId(PARK_ID)

private val coordinates: Coordinates =
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
    id = PictureId(MAIN_PICTURE_ID),
    name = PictureName(COASTER_NAME),
    url = ImageUrl(MAIN_PICTURE_URL),
    copyright = PictureCopyright(
        author = Author(PICTURE_AUTHOR),
        date = null,
    )
)

internal val otherPictures = listOf(
    Picture(
        id = PictureId(OTHER_PICTURE_ID),
        name = PictureName(COASTER_NAME),
        url = ImageUrl(OTHER_PICTURE_URL),
        copyright = PictureCopyright(
            author = Author(PICTURE_AUTHOR),
            date = null,
        )
    )
)

internal val pictures = Pictures(
    main = mainPicture,
    other = otherPictures,
)

internal val design = Design(
    type = Type(COASTER_TYPE),
    train = Train(COASTER_TRAIN),
    elements = Element(COASTER_ELEMENT),
    arrangement = Arrangement(COASTER_ARRANGEMENT),
    restraints = null,
    designer = Designer(COASTER_DESIGNER),
)

internal val ride = SingleTrackRide(
    height = Height(Meters(HEIGHT)),
    length = Length(Meters(LENGTH)),
    speed = Speed(Kmh(SPEED)),
    duration = Duration(Seconds(DURATION_IN_SECONDS)),
    inversions = Inversions(INVERSIONS),
    gForce = null,
    drop = Drop(Meters(DROP)),
    maxVertical = null,
)

internal val specs = Specs(
    design = design,
    model = Model(MODEL),
    manufacturer = Manufacturer(MANUFACTURER),
    cost = null,
    dimensions = null,
    capacity = null,
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
    pictures = pictures,
    specs = specs,
    status = status,
)

internal val rollerCoasters = listOf(rollerCoaster)