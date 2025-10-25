package com.sotti.roller.coasters.presentation.design.system.playground

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sotti.roller.coasters.presentation.previews.RollerCoastersPreviewNoLocale

@Composable
@RollerCoastersPreviewNoLocale
private fun DropdownMenuColumn() {
    Column(modifier = Modifier.width(IntrinsicSize.Max)) {
        (1..10).forEach { index ->
            var text = "a"
            repeat(index) { text += "a" }
            Text(
                text = "Text $index",
                modifier = Modifier
                    .background(Yellow)
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
}
