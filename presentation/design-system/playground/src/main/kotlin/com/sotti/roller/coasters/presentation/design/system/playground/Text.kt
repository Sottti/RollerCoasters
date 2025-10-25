package com.sotti.roller.coasters.presentation.design.system.playground

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sotti.roller.coasters.presentation.design.system.text.Text
import com.sotti.roller.coasters.presentation.design.system.themes.RollerCoastersTheme
import androidx.compose.material3.Text as MaterialText


@Preview
@Composable
private fun Material3Text() {
    RollerCoastersTheme() {
        MyCard {
            MaterialText(text = "This is a Material 3 Text")
        }
    }
}

@Preview
@Composable
private fun VanillaText() {
    RollerCoastersTheme() {
        MyCard {
            Text.Vanilla(text = "This is a Material 3 Text")
        }
    }
}


@Preview
@Composable
private fun BodyMediumText() {
    RollerCoastersTheme() {
        MyCard {
            Text.Body.Medium(text = "This is a Material 3 Text")
        }
    }
}


@Composable
private fun MyCard(
    content: @Composable () -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.error,
        )
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            content()
        }
    }
}
