package com.sottti.roller.coasters.presentation.design.system.card.grid

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sottti.roller.coasters.presentation.design.system.card.Card
import com.sottti.roller.coasters.presentation.design.system.card.data.CardDefaults
import com.sottti.roller.coasters.presentation.design.system.card.grid.model.CardGridItems
import com.sottti.roller.coasters.presentation.design.system.card.grid.model.MonoCardGridState
import com.sottti.roller.coasters.presentation.design.system.card.grid.model.QuadCardGridState
import com.sottti.roller.coasters.presentation.design.system.card.model.CornerType
import com.sottti.roller.coasters.presentation.design.system.card.model.Corners
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.icons.model.IconState
import com.sottti.roller.coasters.presentation.design.system.icons.ui.circledIcon.CircledIcon
import com.sottti.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreviewNoLocale
import androidx.compose.material3.CardDefaults as MaterialCardDefaults

@Composable
public fun CardGrid(
    @StringRes item: Int,
    modifier: Modifier,
    onClick: (() -> Unit),
) {
    CardGrid(
        modifier = modifier.fillMaxWidth(),
        onClick = { onClick() },
        textResId = item,
    )
}

@Composable
private fun CardGrid(
    modifier: Modifier,
    onClick: (() -> Unit),
    corners: Corners = CardDefaults.cardConvexCorners,
    textResId: Int,
) {
    Card(
        modifier = modifier,
        onClick = onClick,
        colors = MaterialCardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        corners = corners,
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Text.Label.Medium(
                modifier = Modifier.padding(dimensions.padding.medium),
                textResId = textResId,
            )
        }
    }
}

@Composable
public fun CardGrid(
    items: CardGridItems,
    iconState: IconState,
    modifier: Modifier,
    onClick: ((Int) -> Unit),
) {
    Box {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(dimensions.padding.small),
        ) {
            CardGridRow {
                CardGrid(
                    modifier = Modifier.weight(1f),
                    onClick = { onClick(0) },
                    corners = CardDefaults.cardSharpCorners.copy(
                        bottomEnd = CornerType.Concave,
                        topStart = CornerType.Convex,
                    ),
                    textResId = items.firstItem,
                )
                CardGrid(
                    modifier = Modifier.weight(1f),
                    onClick = { onClick(1) },
                    corners = CardDefaults.cardSharpCorners.copy(
                        bottomStart = CornerType.Concave,
                        topEnd = CornerType.Convex,
                    ),
                    textResId = items.secondItem,
                )
            }
            CardGridRow {
                CardGrid(
                    modifier = Modifier.weight(1f),
                    onClick = { onClick(2) },
                    corners = CardDefaults.cardSharpCorners.copy(
                        bottomStart = CornerType.Convex,
                        topEnd = CornerType.Concave,
                    ),
                    textResId = items.thirdItem,
                )
                CardGrid(
                    modifier = Modifier.weight(1f),
                    onClick = { onClick(3) },
                    corners = CardDefaults.cardSharpCorners.copy(
                        bottomEnd = CornerType.Convex,
                        topStart = CornerType.Concave,
                    ),
                    textResId = items.forthItem,
                )
            }
        }
        CircledIcon(
            modifier = Modifier.align(Alignment.Center),
            iconState = iconState,
            backgroundColor = colors.surfaceContainerHighest,
            iconColor = colors.onSurface,
        )
    }
}

@Composable
private fun CardGridRow(
    content: @Composable RowScope.() -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(dimensions.padding.small),
    ) {
        content()
    }
}

@Composable
@RollerCoastersPreviewNoLocale
internal fun QuadCardGridPreview(
    @PreviewParameter(QuadCardGridStateProvider::class)
    state: QuadCardGridState,
) {
    RollerCoastersPreviewTheme {
        Box(modifier = Modifier.background(colors.surfaceContainerHighest)) {
            CardGrid(
                items = state.items,
                iconState = state.iconState,
                modifier = state.modifier,
                onClick = state.onClick,
            )
        }
    }
}

@Composable
@RollerCoastersPreviewNoLocale
internal fun MonoCardGridPreview(
    @PreviewParameter(MonoCardGridStateProvider::class)
    state: MonoCardGridState,
) {
    RollerCoastersPreviewTheme {
        Box(modifier = Modifier.background(colors.surfaceContainerHighest)) {
            CardGrid(
                item = state.item,
                modifier = state.modifier,
                onClick = state.onClick,
            )
        }
    }
}
