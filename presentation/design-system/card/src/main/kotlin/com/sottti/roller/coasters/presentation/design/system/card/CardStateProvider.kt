package com.sottti.roller.coasters.presentation.design.system.card

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.sottti.roller.coasters.presentation.design.system.card.data.CardDefaults
import com.sottti.roller.coasters.presentation.design.system.card.model.CardState

internal class CardStateProvider : PreviewParameterProvider<CardState> {
    override val values: Sequence<CardState> = sequence {
        cornerValues().forEach { corners ->
            yield(
                CardState(
                    content = {},
                    corners = corners,
                    modifier = Modifier
                        .width(200.dp)
                        .height(100.dp),
                    onClick = {},
                ),
            )
        }
    }
}

private fun cornerValues() = sequenceOf(
    CardDefaults.cardConcaveCorners,
    CardDefaults.cardConvexCorners,
    CardDefaults.cardSharpCorners,
)
