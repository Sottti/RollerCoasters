package com.sottti.roller.coasters.presentation.roller.coaster.details.model

import androidx.compose.runtime.Immutable
import com.sottti.roller.coasters.domain.model.ImageUrl

@Immutable
internal data class RollerCoasterDetails(
    val imageUrl: ImageUrl?,
    val parkName: String,
    val rollerCoasterName: String,
)