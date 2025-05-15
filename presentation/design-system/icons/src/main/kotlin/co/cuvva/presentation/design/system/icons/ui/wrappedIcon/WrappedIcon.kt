package co.cuvva.presentation.design.system.icons.ui.wrappedIcon

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import co.cuvva.presentation.design.system.icons.model.IconState
import co.cuvva.presentation.design.system.icons.ui.icon.Icon
import co.cuvva.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.LightDarkThemePreview

@Composable
public fun WrappedIcon(
    @StringRes text: Int,
    iconState: IconState,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Graphic(iconState)
        Spacer(modifier = Modifier.size(dimensions.padding.smallMedium))
        Text(text)

    }
}

@Composable
private fun Graphic(iconState: IconState) {
    Card(shape = MaterialTheme.shapes.extraLarge) {
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
@LightDarkThemePreview
internal fun WrappedIconPreview(
    @PreviewParameter(WrappedIconPreviewProvider::class)
    state: WrappedIconPreviewState,
) {
    RollerCoastersPreviewTheme {
        WrappedIcon(
            text = state.text,
            iconState = state.iconState,
            onClick = state.onClick,
        )
    }
}