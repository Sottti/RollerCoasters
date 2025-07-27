package com.sottti.roller.coasters.presentation.design.system.icons.ui.pilledIcon

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.icons.model.IconState
import com.sottti.roller.coasters.presentation.design.system.icons.ui.icon.Icon
import com.sottti.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreview
import com.sottti.roller.coasters.presentation.utils.Spacer

@Composable
public fun PilledIcon(
    @StringRes text: Int,
    iconState: IconState,
    onClick: () -> Unit,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Graphic(iconState = iconState, onClick = onClick)
        Spacer(dimensions.padding.smallMedium)
        Text(text)

    }
}

@Composable
private fun Graphic(
    iconState: IconState,
    onClick: () -> Unit,
) {
    Card(
        onClick = onClick,
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Icon(
            modifier = Modifier
                .padding(
                    vertical = dimensions.padding.medium,
                    horizontal = dimensions.padding.mediumLarge,
                ),
            iconState = iconState,
        )
    }
}

@Composable
private fun Text(text: Int) {
    Text.Label.Medium(
        textResId = text,
        textColor = colors.onBackground,
    )
}

@Composable
@RollerCoastersPreview
internal fun PilledIconPreview(
    @PreviewParameter(PilledIconPreviewProvider::class)
    state: PilledIconPreviewState,
) {
    RollerCoastersPreviewTheme {
        PilledIcon(
            text = state.text,
            iconState = state.iconState,
            onClick = state.onClick,
        )
    }
}
