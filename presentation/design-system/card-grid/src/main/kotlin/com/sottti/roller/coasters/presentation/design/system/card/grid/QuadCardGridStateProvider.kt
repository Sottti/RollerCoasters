package com.sottti.roller.coasters.presentation.design.system.card.grid

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.presentation.design.system.icons.data.Icons

internal class QuadCardGridStateProvider : PreviewParameterProvider<QuadCardGridState> {
    override val values = buildList {
        add(
            QuadCardGridState(
                firstItem = R.string.first_item,
                secondItem = R.string.second_item,
                thirdItem = R.string.third_item,
                forthItem = R.string.fourth_item,
                modifier = Modifier.fillMaxWidth(),
                iconState = Icons.Android.filled,
                onClick = {},
            ),
        )
    }.asSequence()
}