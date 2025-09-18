package com.sottti.roller.coasters.presentation.design.system.shapes

import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sottti.roller.coasters.presentation.design.system.shapes.data.cornerShapes
import com.sottti.roller.coasters.presentation.design.system.shapes.model.CardState
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreviewNoLocale

@Composable
@RollerCoastersPreviewNoLocale
internal fun CardPreview(
    @PreviewParameter(CardStateProvider::class)
    state: CardState,
) {
    val corners = state.cornerKeyToValues.invoke(state.cornersKey)
    RollerCoastersPreviewTheme {
        Card(
            content = state.content,
            modifier = state.modifier,
            onClick = state.onClick,
            shape = cornerShapes(
                bottomEnd = corners.bottomEnd,
                bottomStart = corners.bottomStart,
                topEnd = corners.topEnd,
                topStart = corners.topStart,
            ),
        )
    }
}
