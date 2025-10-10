package com.sottti.roller.coasters.presentation.design.system.playground

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.graphics.shapes.toPath
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreviewNoLocale

@Composable
internal fun HexagonBox(
    color: Color,
) {
    Box(
        modifier = Modifier
            .drawWithCache {
                val roundedPolygonPath =
                    roundedPolygon()
                        .toPath()
                        .asComposePath()

                onDrawBehind {
                    drawPath(roundedPolygonPath, color = color)
                }
            }
            .fillMaxSize()
    )
}

@Composable
@RollerCoastersPreviewNoLocale
private fun HexagonBoxPreview(){
    HexagonBox(Color.Yellow)
}
