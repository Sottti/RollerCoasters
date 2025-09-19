package com.sottti.roller.coasters.presentation.design.system.shapes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
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
        Box(modifier = Modifier.background(colors.background)) {
            Card(
                content = state.content,
                modifier = state.modifier,
                colors = CardDefaults.cardColors(containerColor = colors.surfaceVariant),
                elevation = CardDefaults.cardElevation(defaultElevation = state.elevation),
                shape = cornerShape(
                    bottomEnd = state.corners.bottomEnd,
                    bottomStart = state.corners.bottomStart,
                    topEnd = state.corners.topEnd,
                    topStart = state.corners.topStart,
                ),
            )
        }
    }
}
