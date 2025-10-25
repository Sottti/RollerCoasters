package com.sotti.roller.coasters.presentation.design.system.card.grid.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import com.sotti.roller.coasters.presentation.design.system.icons.model.IconState

@Immutable
internal data class QuadCardGridState(
    val items: CardGridItems,
    val modifier: Modifier,
    val iconState: IconState,
    val onClick: (text: Int) -> Unit,
)
