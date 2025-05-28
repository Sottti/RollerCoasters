package com.sottti.roller.coasters.presentation.explore.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import com.sottti.roller.coasters.domain.model.ImageUrl
import com.sottti.roller.coasters.presentation.design.system.icons.model.IconState
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
internal sealed interface PrimaryFilter {
    @get:StringRes
    val labelResId: Int
    val action: PrimaryFilterAction
    val expanded: Boolean
    val leadingIcon: IconState?
    val selected: Boolean
}

@Immutable
internal data class SortByPrimaryFilter(
    @StringRes override val labelResId: Int,
    override val action: PrimaryFilterAction,
    override val expanded: Boolean,
    override val leadingIcon: IconState?,
    override val selected: Boolean,
) : PrimaryFilter

@Immutable
internal data class TypePrimaryFilter(
    @StringRes override val labelResId: Int,
    override val action: PrimaryFilterAction,
    override val expanded: Boolean,
    override val leadingIcon: IconState?,
    override val selected: Boolean,
) : PrimaryFilter

@Immutable
internal sealed interface SecondaryFilter {
    @get:StringRes
    val labelResId: Int
    val action: SecondaryFilterAction
    val leadingIcon: IconState?
    val selected: Boolean
    val visible: Boolean
}

@Immutable
internal sealed interface SortBySecondaryFilter : SecondaryFilter

@Immutable
internal data class AlphabeticalFilter(
    @StringRes override val labelResId: Int,
    override val action: SecondaryFilterAction,
    override val leadingIcon: IconState?,
    override val selected: Boolean,
    override val visible: Boolean,
) : SortBySecondaryFilter

@Immutable
internal data class DropFilter(
    @StringRes override val labelResId: Int,
    override val action: SecondaryFilterAction,
    override val leadingIcon: IconState?,
    override val selected: Boolean,
    override val visible: Boolean,
) : SortBySecondaryFilter

@Immutable
internal data class GForceFilter(
    @StringRes override val labelResId: Int,
    override val action: SecondaryFilterAction,
    override val leadingIcon: IconState?,
    override val selected: Boolean,
    override val visible: Boolean,
) : SortBySecondaryFilter

@Immutable
internal data class HeightFilter(
    @StringRes override val labelResId: Int,
    override val action: SecondaryFilterAction,
    override val leadingIcon: IconState?,
    override val selected: Boolean,
    override val visible: Boolean,
) : SortBySecondaryFilter

@Immutable
internal data class InversionsFilter(
    @StringRes override val labelResId: Int,
    override val action: SecondaryFilterAction,
    override val leadingIcon: IconState?,
    override val selected: Boolean,
    override val visible: Boolean,
) : SortBySecondaryFilter

@Immutable
internal data class LengthFilter(
    @StringRes override val labelResId: Int,
    override val action: SecondaryFilterAction,
    override val leadingIcon: IconState?,
    override val selected: Boolean,
    override val visible: Boolean,
) : SortBySecondaryFilter

@Immutable
internal data class MaxVerticalFilter(
    @StringRes override val labelResId: Int,
    override val action: SecondaryFilterAction,
    override val leadingIcon: IconState?,
    override val selected: Boolean,
    override val visible: Boolean,
) : SortBySecondaryFilter

@Immutable
internal data class SpeedFilter(
    @StringRes override val labelResId: Int,
    override val action: SecondaryFilterAction,
    override val leadingIcon: IconState?,
    override val selected: Boolean,
    override val visible: Boolean,
) : SortBySecondaryFilter


@Immutable
internal sealed interface TypeSecondaryFilter : SecondaryFilter

@Immutable
internal data class AllFilter(
    @StringRes override val labelResId: Int,
    override val action: SecondaryFilterAction,
    override val leadingIcon: IconState?,
    override val selected: Boolean,
    override val visible: Boolean,
) : TypeSecondaryFilter

@Immutable
internal data class WoodFilter(
    @StringRes override val labelResId: Int,
    override val action: SecondaryFilterAction,
    override val leadingIcon: IconState?,
    override val selected: Boolean,
    override val visible: Boolean,
) : TypeSecondaryFilter

@Immutable
internal data class SteelFilter(
    @StringRes override val labelResId: Int,
    override val action: SecondaryFilterAction,
    override val leadingIcon: IconState?,
    override val selected: Boolean,
    override val visible: Boolean,
) : TypeSecondaryFilter