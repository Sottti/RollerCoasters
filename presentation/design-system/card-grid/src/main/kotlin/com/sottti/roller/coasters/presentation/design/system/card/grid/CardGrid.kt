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
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sottti.roller.coasters.presentation.design.system.card.grid.model.CardGridItems
import com.sottti.roller.coasters.presentation.design.system.card.grid.model.MonoCardGridState
import com.sottti.roller.coasters.presentation.design.system.card.grid.model.QuadCardGridState
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.icons.model.IconState
import com.sottti.roller.coasters.presentation.design.system.icons.ui.icon.Icon
import com.sottti.roller.coasters.presentation.design.system.shapes.data.cornerShapes
import com.sottti.roller.coasters.presentation.design.system.shapes.model.Corner.Concave
import com.sottti.roller.coasters.presentation.design.system.shapes.model.Corner.Convex
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
        shape = MaterialTheme.shapes.large,
        textResId = item,
    )
}

@Composable
private fun CardGrid(
    modifier: Modifier,
    onClick: (() -> Unit),
    shape: Shape,
    textResId: Int,
) {
    Card(
        modifier = modifier,
        onClick = onClick,
        colors = MaterialCardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        shape = shape,
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
                    shape = cornerShapes(
                        bottomEnd = Concave(MaterialTheme.shapes.large.bottomEnd),
                        bottomStart = Convex(MaterialTheme.shapes.extraSmall.bottomStart),
                        topEnd = Convex(MaterialTheme.shapes.extraSmall.topEnd),
                    ),
                    textResId = items.firstItem,
                )
                CardGrid(
                    modifier = Modifier.weight(1f),
                    onClick = { onClick(1) },
                    shape = cornerShapes(
                        bottomEnd = Convex(MaterialTheme.shapes.extraSmall.bottomStart),
                        bottomStart = Concave(MaterialTheme.shapes.large.bottomStart),
                        topStart = Convex(MaterialTheme.shapes.extraSmall.bottomStart),
                    ),
                    textResId = items.secondItem,
                )
            }
            CardGridRow {
                CardGrid(
                    modifier = Modifier.weight(1f),
                    onClick = { onClick(2) },
                    shape = cornerShapes(
                        topEnd = Concave(MaterialTheme.shapes.large.topEnd),
                        topStart = Convex(MaterialTheme.shapes.extraSmall.bottomStart),
                        bottomEnd = Convex(MaterialTheme.shapes.extraSmall.bottomStart),
                    ),
                    textResId = items.thirdItem,
                )
                CardGrid(
                    modifier = Modifier.weight(1f),
                    onClick = { onClick(3) },
                    shape = cornerShapes(
                        topStart = Concave(MaterialTheme.shapes.large.topStart),
                        bottomStart = Convex(MaterialTheme.shapes.extraSmall.bottomStart),
                        topEnd = Convex(MaterialTheme.shapes.extraSmall.bottomStart),
                    ),
                    textResId = items.forthItem,
                )
            }
        }
        Icon(
            iconState = iconState,
            modifier = Modifier.align(Alignment.Center),
            tint = colors.onSurface,
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
        CardGrid(
            item = state.item,
            modifier = state.modifier,
            onClick = state.onClick,
        )
    }
}
