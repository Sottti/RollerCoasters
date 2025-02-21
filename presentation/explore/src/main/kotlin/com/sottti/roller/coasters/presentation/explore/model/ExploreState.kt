package com.sottti.roller.coasters.presentation.explore.model

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import com.sottti.roller.coasters.domain.model.ImageUrl
import kotlinx.coroutines.flow.Flow

@Immutable
internal data class ExploreState(
    val rollerCoastersFlow: Flow<PagingData<RollerCoasterUiModel>>,
)

@Immutable
internal data class RollerCoasterUiModel(
    val imageUrl: ImageUrl?,
    val parkName: String,
    val rollerCoasterName: String,
    val stat: String?,
    val statDetail: String,
)