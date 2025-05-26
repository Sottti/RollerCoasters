package com.sottti.roller.coasters.presentation.explore.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import com.sotti.roller.coasters.presentation.design.system.icons.model.IconState
import com.sottti.roller.coasters.domain.model.ImageUrl
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction
import kotlinx.coroutines.flow.Flow

@Immutable
internal data class ExploreState(
    val filters: Filters,
    val rollerCoasters: Flow<PagingData<ExploreRollerCoaster>>,
)

@Immutable
internal data class ExploreRollerCoaster(
    val id: Int,
    val imageUrl: ImageUrl?,
    val parkName: String,
    val rollerCoasterName: String,
    val stat: String?,
    val statDetail: String,
)

@Immutable
internal data class Filters(
    val primary: List<PrimaryFilter>,
    val secondary: List<SecondaryFilter>,
)

@Immutable
internal sealed class PrimaryFilter(
    @StringRes open val labelResId: Int,
    open val action: PrimaryFilterAction,
    open val expanded: Boolean,
    open val leadingIcon: IconState?,
    open val selected: Boolean,
) {
    @Immutable
    data class SortByPrimaryFilter(
        @StringRes override val labelResId: Int,
        override val action: PrimaryFilterAction,
        override val expanded: Boolean,
        override val leadingIcon: IconState?,
        override val selected: Boolean,
    ) : PrimaryFilter(labelResId, action, expanded, leadingIcon, selected)

    @Immutable
    data class TypePrimaryFilter(
        @StringRes override val labelResId: Int,
        override val action: PrimaryFilterAction,
        override val expanded: Boolean,
        override val leadingIcon: IconState?,
        override val selected: Boolean,
    ) : PrimaryFilter(labelResId, action, expanded, leadingIcon, selected)
}

@Immutable
internal sealed class SecondaryFilter(
    @StringRes open val labelResId: Int,
    open val action: SecondaryFilterAction,
    open val leadingIcon: IconState?,
    open val selected: Boolean,
    open val visible: Boolean,
) {
    @Immutable
    internal sealed class SortBySecondaryFilter(
        @StringRes override val labelResId: Int,
        override val action: SecondaryFilterAction,
        override val leadingIcon: IconState?,
        override val selected: Boolean,
        override val visible: Boolean,
    ) : SecondaryFilter(labelResId, action, leadingIcon, selected, visible) {

        @Immutable
        data class AlphabeticalFilter(
            @StringRes override val labelResId: Int,
            override val action: SecondaryFilterAction,
            override val leadingIcon: IconState?,
            override val selected: Boolean,
            override val visible: Boolean,
        ) : SortBySecondaryFilter(labelResId, action, leadingIcon, selected, visible)

        @Immutable
        data class DropFilter(
            @StringRes override val labelResId: Int,
            override val action: SecondaryFilterAction,
            override val leadingIcon: IconState?,
            override val selected: Boolean,
            override val visible: Boolean,
        ) : SortBySecondaryFilter(labelResId, action, leadingIcon, selected, visible)

        @Immutable
        data class GForceFilter(
            @StringRes override val labelResId: Int,
            override val action: SecondaryFilterAction,
            override val leadingIcon: IconState?,
            override val selected: Boolean,
            override val visible: Boolean,
        ) : SortBySecondaryFilter(labelResId, action, leadingIcon, selected, visible)

        @Immutable
        data class HeightFilter(
            @StringRes override val labelResId: Int,
            override val action: SecondaryFilterAction,
            override val leadingIcon: IconState?,
            override val selected: Boolean,
            override val visible: Boolean,
        ) : SortBySecondaryFilter(labelResId, action, leadingIcon, selected, visible)

        @Immutable
        data class InversionsFilter(
            @StringRes override val labelResId: Int,
            override val action: SecondaryFilterAction,
            override val leadingIcon: IconState?,
            override val selected: Boolean,
            override val visible: Boolean,
        ) : SortBySecondaryFilter(labelResId, action, leadingIcon, selected, visible)

        @Immutable
        data class LengthFilter(
            @StringRes override val labelResId: Int,
            override val action: SecondaryFilterAction,
            override val leadingIcon: IconState?,
            override val selected: Boolean,
            override val visible: Boolean,
        ) : SortBySecondaryFilter(labelResId, action, leadingIcon, selected, visible)

        @Immutable
        data class MaxVerticalFilter(
            @StringRes override val labelResId: Int,
            override val action: SecondaryFilterAction,
            override val leadingIcon: IconState?,
            override val selected: Boolean,
            override val visible: Boolean,
        ) : SortBySecondaryFilter(labelResId, action, leadingIcon, selected, visible)

        @Immutable
        data class SpeedFilter(
            @StringRes override val labelResId: Int,
            override val action: SecondaryFilterAction,
            override val leadingIcon: IconState?,
            override val selected: Boolean,
            override val visible: Boolean,
        ) : SortBySecondaryFilter(labelResId, action, leadingIcon, selected, visible)
    }

    @Immutable
    internal sealed class TypeSecondaryFilter(
        @StringRes override val labelResId: Int,
        override val action: SecondaryFilterAction,
        override val leadingIcon: IconState?,
        override val selected: Boolean,
        override val visible: Boolean,
    ) : SecondaryFilter(labelResId, action, leadingIcon, selected, visible) {
        @Immutable
        data class AllFilter(
            @StringRes override val labelResId: Int,
            override val action: SecondaryFilterAction,
            override val leadingIcon: IconState?,
            override val selected: Boolean,
            override val visible: Boolean,
        ) : TypeSecondaryFilter(labelResId, action, leadingIcon, selected, visible)

        @Immutable
        data class WoodFilter(
            @StringRes override val labelResId: Int,
            override val action: SecondaryFilterAction,
            override val leadingIcon: IconState?,
            override val selected: Boolean,
            override val visible: Boolean,
        ) : TypeSecondaryFilter(labelResId, action, leadingIcon, selected, visible)

        @Immutable
        data class SteelFilter(
            @StringRes override val labelResId: Int,
            override val action: SecondaryFilterAction,
            override val leadingIcon: IconState?,
            override val selected: Boolean,
            override val visible: Boolean,
        ) : TypeSecondaryFilter(labelResId, action, leadingIcon, selected, visible)
    }
}