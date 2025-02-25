package com.sottti.roller.coasters.presentation.explore.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import com.sottti.roller.coasters.domain.model.ImageUrl
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction
import com.sottti.roller.coasters.presentation.explore.model.Filter.PrimaryFilter
import com.sottti.roller.coasters.presentation.explore.model.Filter.SecondaryFilter
import kotlinx.coroutines.flow.Flow

@Immutable
internal data class ExploreState(
    val rollerCoastersFlow: Flow<PagingData<RollerCoasterUiModel>>,
    val filters: Filters,
)

@Immutable
internal data class Filters(
    val primaryFilters: List<PrimaryFilter>,
    val secondaryFilters: List<SecondaryFilter>?,
)

@Immutable
internal data class RollerCoasterUiModel(
    val imageUrl: ImageUrl?,
    val parkName: String,
    val rollerCoasterName: String,
    val stat: String?,
    val statDetail: String,
)

@Immutable
internal sealed class Filter(
    @StringRes open val labelResId: Int,
    open val action: ExploreAction,
    open val selected: Boolean,
) {
    @Immutable
    internal sealed class PrimaryFilter(
        @StringRes override val labelResId: Int,
        open val expanded: Boolean,
        override val action: PrimaryFilterAction,
        override val selected: Boolean,
    ) : Filter(labelResId, action, selected) {
        data class TypeFilter(
            @StringRes override val labelResId: Int,
            override val action: PrimaryFilterAction,
            override val expanded: Boolean,
            override val selected: Boolean,
        ) : PrimaryFilter(labelResId, expanded, action, selected)
    }

    @Immutable
    internal sealed class SecondaryFilter(
        @StringRes override val labelResId: Int,
        override val action: SecondaryFilterAction,
        override val selected: Boolean,
    ) : Filter(labelResId, action, selected) {
        data class Steel(
            @StringRes override val labelResId: Int,
            override val action: SecondaryFilterAction,
            override val selected: Boolean,
        ) : SecondaryFilter(labelResId, action, selected)

        data class Wood(
            @StringRes override val labelResId: Int,
            override val action: SecondaryFilterAction,
            override val selected: Boolean,
        ) : SecondaryFilter(labelResId, action, selected)
    }
}