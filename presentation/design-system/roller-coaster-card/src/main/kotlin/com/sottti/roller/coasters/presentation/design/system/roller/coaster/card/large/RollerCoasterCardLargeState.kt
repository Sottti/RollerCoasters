package com.sottti.roller.coasters.presentation.design.system.roller.coaster.card.large

import androidx.compose.runtime.Immutable
import com.sottti.roller.coasters.domain.model.ImageUrl
import com.sottti.roller.coasters.presentation.design.system.roller.coaster.card.RollerCoasterCardStat

@Immutable
internal data class RollerCoasterCardLargeState(
    val foreverLoading: Boolean,
    val imageUrl: ImageUrl?,
    val onClick: () -> Unit,
    val parkName: String,
    val rollerCoasterName: String,
    val stat: RollerCoasterCardStat?,
)
