package com.sottti.roller.coasters.presentation.design.system.card.grid

import androidx.compose.ui.Modifier
import com.sotti.roller.coasters.presentation.design.system.icons.model.IconState

internal data class QuadCardGridState(
    val firstItem: Int,
    val secondItem: Int,
    val thirdItem: Int,
    val forthItem: Int,
    val modifier: Modifier,
    val iconState: IconState,
    val onClick: (text: Int) -> Unit,
)