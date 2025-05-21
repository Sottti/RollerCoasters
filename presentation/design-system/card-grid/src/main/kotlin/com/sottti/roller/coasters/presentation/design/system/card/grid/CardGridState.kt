package com.sottti.roller.coasters.presentation.design.system.card.grid

import androidx.compose.ui.Modifier
import co.cuvva.presentation.design.system.icons.model.IconState

internal data class CardGridState(
    val firstItem: Int,
    val forthItem: Int,
    val secondItem: Int,
    val thirdItem: Int,
    val modifier: Modifier,
    val iconState: IconState,
    val onClick: (text: Int) -> Unit,
)