package com.sottti.roller.coasters.presentation.roller.coaster.details.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.sottti.roller.coasters.domain.model.ImageUrl
import com.sottti.roller.coasters.presentation.design.system.icons.model.IconState

@Immutable
internal data class RollerCoasterDetailsState(
    val content: RollerCoasterDetailsContentState,
    val topBar: TopBarState,
)

@Immutable
internal data class TopBarState(
    val favouriteIcon: FavouriteIconState,
    val navigationIcon: IconState,
    val title: String?,
)

@Immutable
internal sealed interface FavouriteIconState {
    @Immutable
    data class Loaded(val iconState: IconState) : FavouriteIconState

    @Immutable
    data object Loading : FavouriteIconState
}

@Immutable
internal sealed interface RollerCoasterDetailsContentState {
    @Immutable
    data class Loaded(
        val rollerCoaster: RollerCoasterDetailsRollerCoasterState,
    ) : RollerCoasterDetailsContentState

    @Immutable
    data object Error : RollerCoasterDetailsContentState

    @Immutable
    data object Loading : RollerCoasterDetailsContentState
}

@Immutable
internal data class RollerCoasterDetailsRollerCoasterState(
    val images: List<RollerCoasterDetailsImageState>?,
    val identity: RollerCoasterIdentityState,
    val location: RollerCoasterLocationState,
    val ride: RollerCoasterRideState?,
    val status: RollerCoasterStatusState?,
)

@Immutable
internal data class RollerCoasterDetailsImageState(
    val contentDescription: String,
    val imageUrl: ImageUrl,
)

@Immutable
internal sealed interface RollerCoasterDetailsSectionState {
    @get:StringRes
    val header: Int
}

@Immutable
internal data class RollerCoasterIdentityState(
    override val header: Int,
    val formerNames: RollerCoasterDetailsRow?,
    val name: RollerCoasterDetailsRow,
) : RollerCoasterDetailsSectionState

@Immutable
internal data class RollerCoasterStatusState(
    override val header: Int,
    val closedDate: RollerCoasterDetailsRow?,
    val current: RollerCoasterDetailsRow?,
    val former: RollerCoasterDetailsRow?,
    val openedDate: RollerCoasterDetailsRow?,
) : RollerCoasterDetailsSectionState

@Immutable
internal data class RollerCoasterLocationState(
    override val header: Int,
    val city: RollerCoasterDetailsRow,
    val coordinates: RollerCoasterLocationCoordinatesState?,
    val country: RollerCoasterDetailsRow,
    val mapMarkerTitle: String,
    val park: RollerCoasterDetailsRow,
    val relocations: RollerCoasterDetailsRow?,
) : RollerCoasterDetailsSectionState

@Immutable
internal data class RollerCoasterLocationCoordinatesState(
    val latitude: Double,
    val longitude: Double,
)

@Immutable
internal data class RollerCoasterRideState(
    override val header: Int,
    val drop: RollerCoasterDetailsRow?,
    val duration: RollerCoasterDetailsRow?,
    val gForce: RollerCoasterDetailsRow?,
    val height: RollerCoasterDetailsRow?,
    val inversions: RollerCoasterDetailsRow?,
    val length: RollerCoasterDetailsRow?,
    val maxVertical: RollerCoasterDetailsRow?,
    val speed: RollerCoasterDetailsRow?,
) : RollerCoasterDetailsSectionState

@Immutable
internal data class RollerCoasterDetailsRow(
    @StringRes val headline: Int,
    val trailing: String?,
)