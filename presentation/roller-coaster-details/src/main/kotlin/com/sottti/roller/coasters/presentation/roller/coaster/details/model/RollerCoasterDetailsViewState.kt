package com.sottti.roller.coasters.presentation.roller.coaster.details.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import co.sotti.roller.coasters.presentation.design.system.icons.model.IconState
import com.sottti.roller.coasters.domain.model.ImageUrl
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState.RollerCoasterIdentityViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState.RollerCoasterLocationViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState.RollerCoasterRideViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState.RollerCoasterStatusViewState

@Immutable
internal data class RollerCoasterDetailsViewState(
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
internal sealed class FavouriteIconState {
    data class Loaded(val iconState: IconState) : FavouriteIconState()
    data object Loading : FavouriteIconState()
}

@Immutable
internal sealed class RollerCoasterDetailsContentState {
    @Immutable
    data class Loaded(
        val rollerCoaster: RollerCoasterDetailsRollerCoasterViewState,
    ) : RollerCoasterDetailsContentState()

    @Immutable
    data object Error : RollerCoasterDetailsContentState()

    @Immutable
    object Loading : RollerCoasterDetailsContentState()
}

@Immutable
internal data class RollerCoasterDetailsRollerCoasterViewState(
    val images: List<RollerCoasterDetailsImageViewState>?,
    val identity: RollerCoasterIdentityViewState,
    val location: RollerCoasterLocationViewState,
    val ride: RollerCoasterRideViewState?,
    val status: RollerCoasterStatusViewState?,
)

@Immutable
internal data class RollerCoasterDetailsImageViewState(
    val contentDescription: String,
    val imageUrl: ImageUrl,
)

@Immutable
internal sealed class RollerCoasterDetailsSectionViewState(
    @StringRes open val header: Int,
) {
    @Immutable
    internal data class RollerCoasterIdentityViewState(
        override val header: Int,
        val formerNames: RollerCoasterDetailsRow?,
        val name: RollerCoasterDetailsRow,
    ) : RollerCoasterDetailsSectionViewState(header)

    @Immutable
    internal data class RollerCoasterStatusViewState(
        override val header: Int,
        val closedDate: RollerCoasterDetailsRow?,
        val current: RollerCoasterDetailsRow?,
        val former: RollerCoasterDetailsRow?,
        val openedDate: RollerCoasterDetailsRow?,
    ) : RollerCoasterDetailsSectionViewState(header)

    @Immutable
    internal data class RollerCoasterLocationViewState(
        override val header: Int,
        val city: RollerCoasterDetailsRow,
        val coordinates: RollerCoasterLocationCoordinatesViewState?,
        val country: RollerCoasterDetailsRow,
        val mapMarkerTitle: String,
        val park: RollerCoasterDetailsRow,
        val relocations: RollerCoasterDetailsRow?,
    ) : RollerCoasterDetailsSectionViewState(header)

    @Immutable
    internal data class RollerCoasterLocationCoordinatesViewState(
        val latitude: Double,
        val longitude: Double,
    )

    @Immutable
    internal data class RollerCoasterRideViewState(
        override val header: Int,
        val drop: RollerCoasterDetailsRow?,
        val duration: RollerCoasterDetailsRow?,
        val gForce: RollerCoasterDetailsRow?,
        val height: RollerCoasterDetailsRow?,
        val inversions: RollerCoasterDetailsRow?,
        val length: RollerCoasterDetailsRow?,
        val maxVertical: RollerCoasterDetailsRow?,
        val speed: RollerCoasterDetailsRow?,
    ) : RollerCoasterDetailsSectionViewState(header)
}

@Immutable
internal data class RollerCoasterDetailsRow(
    @StringRes val headline: Int,
    val trailing: String?,
)