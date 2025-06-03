package com.sottti.roller.coasters.presentation.design.system.card.grid

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

internal class MonoCardGridStateProvider : PreviewParameterProvider<MonoCardGridState> {
    override val values = buildList {
        add(
            MonoCardGridState(
                item = R.string.first_item,
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
            ),
        )
    }.asSequence()
}