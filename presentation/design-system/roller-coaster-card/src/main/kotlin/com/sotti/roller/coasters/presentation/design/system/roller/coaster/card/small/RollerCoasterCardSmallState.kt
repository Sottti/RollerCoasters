package com.sotti.roller.coasters.presentation.design.system.roller.coaster.card.small

import androidx.compose.runtime.Immutable
import com.sotti.roller.coasters.domain.model.ImageUrl

@Immutable
internal data class RollerCoasterCardSmallState(
    val foreverLoading: Boolean,
    val imageUrl: ImageUrl?,
    val onClick: () -> Unit,
    val parkName: String,
    val rollerCoasterName: String,
)
