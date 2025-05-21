package com.sottti.roller.coasters.presentation.design.system.card.grid

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import co.cuvva.presentation.design.system.icons.data.Icons

internal class CardGridStateProvider : PreviewParameterProvider<CardGridState> {
    override val values = buildList {
        add(
            CardGridState(
                firstItem = R.string.first_item,
                secondItem = R.string.second_item,
                thirdItem = R.string.third_item,
                forthItem = R.string.fourth_item,
                modifier = Modifier.fillMaxWidth(),
                iconState = Icons.Android.filled,
                onClick = {},
            )
        )
    }.asSequence()
}