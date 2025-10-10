package com.sottti.roller.coasters.presentation.design.system.playground

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreviewNoLocale

@Composable
private fun Hexagon(
    backgroundColor: Color,
    text: String,
) {
    Box(
        modifier = Modifier
            .size(144.dp)
            .clip(hexagonRoundedPolygonShape())
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text)
    }
}

@Composable
@RollerCoastersPreviewNoLocale
private fun HexagonPreview() {
    Hexagon(
        text = "Hexagon",
        backgroundColor = Color.Yellow,
    )
}
