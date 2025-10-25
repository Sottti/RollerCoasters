package com.sotti.roller.coasters.presentation.design.system.card.grid

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sotti.roller.coasters.presentation.design.system.card.grid.model.CardGridItems
import com.sotti.roller.coasters.presentation.design.system.card.grid.model.QuadCardGridState
import com.sotti.roller.coasters.presentation.design.system.icons.data.Icons

internal class QuadCardGridStateProvider : PreviewParameterProvider<QuadCardGridState> {
    override val values = sequenceOf(quadCardGridState)
}

private val quadGridItems: CardGridItems = CardGridItems(
    firstItemResId = R.string.first_item,
    secondItemResId = R.string.second_item,
    thirdItemResId = R.string.third_item,
    forthItemResId = R.string.fourth_item,
)

internal val quadCardGridState: QuadCardGridState =
    QuadCardGridState(
        items = quadGridItems,
        modifier = Modifier.fillMaxWidth(),
        iconState = Icons.Android.filled,
        onClick = {},
    )
