package com.sottti.roller.coasters.presentation.design.system.playground

import android.graphics.Color.colorToHSV
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp

@Composable
fun RainbowLazyColumn(
    nestedScrollConnection: NestedScrollConnection,
    seedColor: Color,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) {
        items(rainbowColors(seedColor)) { color: Color ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(color = color)

            )
        }
    }
}

private fun rainbowColors(
    seedColor: Color,
): List<Color> {
    val hsv = FloatArray(3)
    colorToHSV(seedColor.toArgb(), hsv)

    val seedHue = hsv[0]
    val seedSaturation = hsv[1]
    val seedValue = hsv[2]

    return List(38) { index ->
        Color.hsv(
            hue = (seedHue + index * 360f / 38) % 360,
            saturation = seedSaturation,
            value = seedValue
        )
    }
}