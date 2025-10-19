package com.sottti.roller.coasters.presentation.design.system.playground

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreviewNoLocale
import kotlin.math.min

@Preview
@Composable
private fun MutatingButton() {
    var counter by remember { mutableStateOf(0) }
    Button(onClick = { counter++ }) {
        Text(text = counter.toString())
    }
}

@Composable
@RollerCoastersPreviewNoLocale
private fun DefaultMinSize0() {
    Box(
        modifier = Modifier
            .size(300.dp)
            .background(Color.DarkGray)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(200.dp)
                .background(Color.LightGray)
                .circledRainbowBorder(2.dp)
        )
    }
}

@Composable
private fun TextPropertyAccess() {
    val text = remember { mutableStateOf("") }
    TextField(value = text.value, onValueChange = { text.value = it })
}

@Composable
private fun TextDestructuringDeclaration() {
    val (text, setText) = remember { mutableStateOf("") }
    TextField(value = text, onValueChange = setText)
}

@Composable
private fun TextPropertyDelegation() {
    var text by remember { mutableStateOf("") }
    TextField(value = text, onValueChange = { text = it })
}

@Composable
private fun TextStateHoisting(text: String, onValueChange: (String) -> Unit) {
    TextField(value = text, onValueChange = onValueChange)
}

private fun Modifier.circledRainbowBorder(
    strokeWidth: Dp,
): Modifier = drawWithContent {
    val strokeWidthInPx = strokeWidth.toPx()
    drawCircle(
        color = Color.Yellow,
        radius = min(size.width / 2, size.height / 2) - strokeWidthInPx
    )
    drawContent()
    drawCircle(
        brush = Brush.linearGradient(listOf(Color.Magenta, Color.Blue)),
        radius = min(size.width / 2, size.height / 2) - strokeWidthInPx * 0.5f,
        style = Stroke(width = strokeWidthInPx),
    )
}
