package com.sottti.roller.coasters.presentation.design.system.roller.coaster.card.small

import com.sottti.roller.coasters.domain.model.ImageUrl

internal data class RollerCoasterCardSmallState(
    val foreverLoading: Boolean,
    val imageUrl: ImageUrl?,
    val onClick: () -> Unit,
    val parkName: String,
    val rollerCoasterName: String,
)