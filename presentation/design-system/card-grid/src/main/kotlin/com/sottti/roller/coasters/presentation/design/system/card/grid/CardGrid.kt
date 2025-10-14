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
import com.sottti.roller.coasters.presentation.design.system.shapes.corner.cornerShape
import com.sottti.roller.coasters.presentation.design.system.shapes.corner.Corner.Concave
import com.sottti.roller.coasters.presentation.design.system.shapes.corner.Corner.Rounded
import com.sottti.roller.coasters.presentation.design.system.shapes.shapes
import com.sottti.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersTheme
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreviewNoLocale
import androidx.compose.material3.CardDefaults as MaterialCardDefaults

@Composable
public fun CardGrid(
    @StringRes textResId: Int,
    modifier: Modifier,
    onClick: (() -> Unit),
) {
    CardGrid(
        modifier = modifier,
        onClick = onClick,
        shape = shapes.roundedCorner.large,
        textResId = textResId,
    )
}

@Composable
private fun CardGrid(
    modifier: Modifier,
    onClick: () -> Unit,
    shape: Shape,
    textResId: Int,
) {
    Card(
        modifier = modifier,
        onClick = onClick,
        colors = MaterialCardDefaults.cardColors(
            containerColor = colors.surfaceContainer,
            contentColor = colors.onSurface,
        ),
        shape = shape,
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Text.Label.Medium(
                modifier = Modifier.padding(dimensions.spacing.medium),
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
            verticalArrangement = Arrangement.spacedBy(dimensions.spacing.small),
        ) {
            CardGridRow {
                CardGrid(
                    modifier = Modifier.weight(1f),
                    onClick = { onClick(0) },
                    shape = cornerShape(
                        bottomEnd = Concave(shapes.roundedCorner.large.bottomEnd),
                        bottomStart = Rounded(shapes.roundedCorner.extraSmall.bottomStart),
                        topEnd = Rounded(shapes.roundedCorner.extraSmall.topEnd),
                    ),
                    textResId = items.firstItemResId,
                )
                CardGrid(
                    modifier = Modifier.weight(1f),
                    onClick = { onClick(1) },
                    shape = cornerShape(
                        bottomEnd = Rounded(shapes.roundedCorner.extraSmall.bottomEnd),
                        bottomStart = Concave(shapes.roundedCorner.large.bottomStart),
                        topStart = Rounded(shapes.roundedCorner.extraSmall.topStart),
                    ),
                    textResId = items.secondItemResId,
                )
            }
            CardGridRow {
                CardGrid(
                    modifier = Modifier.weight(1f),
                    onClick = { onClick(2) },
                    shape = cornerShape(
                        topEnd = Concave(shapes.roundedCorner.large.topEnd),
                        topStart = Rounded(shapes.roundedCorner.extraSmall.topStart),
                        bottomEnd = Rounded(shapes.roundedCorner.extraSmall.bottomEnd),
                    ),
                    textResId = items.thirdItemResId,
                )
                CardGrid(
                    modifier = Modifier.weight(1f),
                    onClick = { onClick(3) },
                    shape = cornerShape(
                        topStart = Concave(shapes.roundedCorner.large.topStart),
                        bottomStart = Rounded(shapes.roundedCorner.extraSmall.bottomStart),
                        topEnd = Rounded(shapes.roundedCorner.extraSmall.topEnd),
                    ),
                    textResId = items.forthItemResId,
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
        horizontalArrangement = Arrangement.spacedBy(dimensions.spacing.small),
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
    RollerCoastersTheme {
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
    RollerCoastersTheme {
        CardGrid(
            textResId = state.textResId,
            modifier = state.modifier,
            onClick = state.onClick,
        )
    }
}
