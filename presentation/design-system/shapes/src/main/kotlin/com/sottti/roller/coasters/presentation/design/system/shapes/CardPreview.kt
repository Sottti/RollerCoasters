package com.sottti.roller.coasters.presentation.design.system.shapes

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.sottti.roller.coasters.presentation.design.system.shapes.data.cornersShape
import com.sottti.roller.coasters.presentation.design.system.shapes.model.CardState
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreviewNoLocale

@Composable
@RollerCoastersPreviewNoLocale
internal fun CardPreview(
    @PreviewParameter(CardStateProvider::class)
    state: CardState,
) {
    RollerCoastersPreviewTheme {
        Card(
            content = state.content,
            modifier = state.modifier.padding(16.dp),
            onClick = state.onClick,
            elevation = CardDefaults.cardElevation(defaultElevation = state.elevation),
            shape = cornersShape(
                bottomEnd = state.corners.bottomEnd,
                bottomStart = state.corners.bottomStart,
                topEnd = state.corners.topEnd,
                topStart = state.corners.topStart,
            ),
        )
    }
}
