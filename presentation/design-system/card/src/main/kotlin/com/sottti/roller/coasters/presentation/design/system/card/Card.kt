package com.sottti.roller.coasters.presentation.design.system.card

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.CardColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sottti.roller.coasters.presentation.design.system.card.data.CardDefaults
import com.sottti.roller.coasters.presentation.design.system.card.data.toShape
import com.sottti.roller.coasters.presentation.design.system.card.model.CardState
import com.sottti.roller.coasters.presentation.design.system.card.model.Corners
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreviewNoLocale
import androidx.compose.material3.Card as MaterialCard
import androidx.compose.material3.CardDefaults as MaterialCardDefaults

@Composable
public fun Card(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    corners: Corners = CardDefaults.cardConvexCorners,
    colors: CardColors = MaterialCardDefaults.cardColors(),
    content: @Composable ColumnScope.() -> Unit,
) {
    MaterialCard(
        colors = colors,
        content = content,
        modifier = modifier,
        onClick = onClick,
        shape = corners.toShape(),
    )
}

@Composable
@RollerCoastersPreviewNoLocale
internal fun CardPreview(
    @PreviewParameter(CardStateProvider::class)
    state: CardState,
) {
    RollerCoastersPreviewTheme {
        Card(
            content = state.content,
            corners = state.corners,
            modifier = state.modifier,
            onClick = state.onClick,
        )
    }
}
