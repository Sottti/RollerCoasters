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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.PreviewParameter
import co.cuvva.presentation.design.system.icons.model.IconState
import co.cuvva.presentation.design.system.icons.ui.circledIcon.CircledIcon
import co.cuvva.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.LightDarkThemePreview


@Composable
public fun CardGrid(
    @StringRes firstItem: Int,
    @StringRes forthItem: Int,
    @StringRes secondItem: Int,
    @StringRes thirdItem: Int,
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
                CardGridCard(
                    onClick = { onClick(0) },
                    shape = topStartRounded(),
                    textResId = firstItem
                )
                CardGridCard(
                    onClick = { onClick(1) },
                    shape = topEndRounded(),
                    textResId = secondItem
                )
            }
            CardGridRow {
                CardGridCard(
                    onClick = { onClick(2) },
                    shape = bottomStartRounded(),
                    textResId = thirdItem
                )
                CardGridCard(
                    onClick = { onClick(3) },
                    shape = bottomEndRounded(),
                    textResId = forthItem
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
private fun RowScope.CardGridCard(
    onClick: (() -> Unit),
    shape: Shape,
    textResId: Int,
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        modifier = Modifier.weight(1f),
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
@LightDarkThemePreview
internal fun CardGridPreview(
    @PreviewParameter(CardGridStateProvider::class)
    state: CardGridState,
) {
    RollerCoastersPreviewTheme {
        Box(modifier = Modifier.background(colors.surfaceContainerHighest)) {
            CardGrid(
                firstItem = state.firstItem,
                forthItem = state.forthItem,
                secondItem = state.secondItem,
                thirdItem = state.thirdItem,
                iconState = state.iconState,
                modifier = state.modifier,
                onClick = state.onClick,
            )
        }
    }
}