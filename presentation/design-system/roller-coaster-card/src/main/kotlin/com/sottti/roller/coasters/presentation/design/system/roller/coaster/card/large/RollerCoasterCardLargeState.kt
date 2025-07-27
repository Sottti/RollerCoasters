package com.sottti.roller.coasters.presentation.design.system.roller.coaster.card.large

import com.sottti.roller.coasters.domain.model.ImageUrl

internal data class RollerCoasterCardLargeState(
    val foreverLoading: Boolean,
    val imageUrl: ImageUrl?,
    val onClick: () -> Unit,
    val parkName: String,
    val rollerCoasterName: String,
    val stat: String?,
    val statDetail: String,
)
