package com.sottti.roller.coasters.presentation.design.system.card.grid

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.presentation.design.system.card.grid.model.MonoCardGridState

internal class MonoCardGridStateProvider : PreviewParameterProvider<MonoCardGridState> {
    override val values = sequenceOf(monoCardGridState)
}

internal val monoCardGridState = MonoCardGridState(
    textResId = R.string.first_item,
    modifier = Modifier.fillMaxWidth(),
    onClick = {},
)
