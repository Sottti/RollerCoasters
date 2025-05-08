package com.sottti.roller.coasters.presentation.roller.coaster.details.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import co.cuvva.presentation.design.system.icons.model.IconState
import com.sottti.roller.coasters.domain.roller.coasters.model.Capacity
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState.RollerCoasterIdentityViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState.RollerCoasterLocationViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState.RollerCoasterSpecsViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState.RollerCoasterStatusViewState

@Immutable
internal data class RollerCoasterDetailsViewState(
    val content: RollerCoasterDetailsContentState,
    val topBar: TopBarState,
)

@Immutable
internal data class TopBarState(
    @StringRes val title: Int?,
    val icon: IconState,
)

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
    val identity: RollerCoasterIdentityViewState,
    val location: RollerCoasterLocationViewState,
    val specs : RollerCoasterSpecsViewState,
    val status: RollerCoasterStatusViewState,
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
        val current: RollerCoasterDetailsRow,
        val former: RollerCoasterDetailsRow?,
        val openedDate: RollerCoasterDetailsRow?,
    ) : RollerCoasterDetailsSectionViewState(header)

    @Immutable
    internal data class RollerCoasterLocationViewState(
        override val header: Int,
        val city: RollerCoasterDetailsRow,
        val country: RollerCoasterDetailsRow,
        val park: RollerCoasterDetailsRow,
        val region: RollerCoasterDetailsRow,
        val relocations: RollerCoasterDetailsRow?,
        val state: RollerCoasterDetailsRow,
    ) : RollerCoasterDetailsSectionViewState(header)

    @Immutable
    internal data class RollerCoasterSpecsViewState(
        override val header: Int,
        val capacity: RollerCoasterDetailsRow?,
        val cost: RollerCoasterDetailsRow?,
        val manufacturer: RollerCoasterDetailsRow?,
        val model: RollerCoasterDetailsRow,
    ) : RollerCoasterDetailsSectionViewState(header)
}

@Immutable
internal data class RollerCoasterDetailsRow(
    @StringRes val headline: Int,
    val trailing: String?,
)