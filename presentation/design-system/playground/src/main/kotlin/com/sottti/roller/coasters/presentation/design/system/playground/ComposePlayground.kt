package com.sottti.roller.coasters.presentation.design.system.playground

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreviewNoLocale

@Composable
private fun Playground1() {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Button(onClick = { expanded = !expanded }) {
            Text("Expand / Collapse")
        }

        Box(
            modifier = Modifier
                .background(Color.Red)
                .animateContentSize()
                .padding(16.dp)
        ) {
            Text(
                text = if (expanded) "Expanded text with more content" else "Short text",
                fontSize = 18.sp
            )
        }
    }
}


@Composable
@RollerCoastersPreviewNoLocale
private fun MyPreview() {
    RollerCoastersPreviewTheme {
        Playground1()
    }
}
