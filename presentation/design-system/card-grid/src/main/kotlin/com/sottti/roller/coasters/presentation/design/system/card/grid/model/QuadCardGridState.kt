package com.sottti.roller.coasters.presentation.design.system.card.grid.model

import androidx.compose.ui.Modifier
import com.sottti.roller.coasters.presentation.design.system.icons.model.IconState

internal data class QuadCardGridState(
    val items: CardGridItems,
    val modifier: Modifier,
    val iconState: IconState,
    val onClick: (text: Int) -> Unit,
)
