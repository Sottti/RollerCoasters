package co.cuvva.presentation.design.system.icons.ui.icon

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import co.cuvva.presentation.design.system.icons.model.IconState
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.LightDarkThemePreview
import androidx.compose.material3.Icon as MaterialIcon

@Composable
public fun Icon(
    iconState: IconState,
    modifier: Modifier = Modifier,
    crossfade: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    val iconModifier = modifier.size(24.dp)

    @Composable
    fun RenderIcon(state: IconState) {
        when {
            onClick != null -> IconButton(onClick = onClick) {
                MaterialIcon(
                    painter = painterResource(state.resId),
                    contentDescription = stringResource(state.descriptionResId),
                    modifier = iconModifier,
                )
            }

            else -> MaterialIcon(
                painter = painterResource(state.resId),
                contentDescription = stringResource(state.descriptionResId),
                modifier = iconModifier,
            )
        }
    }

    when {
        crossfade -> Crossfade(targetState = iconState) { targetState -> RenderIcon(targetState) }
        else -> RenderIcon(iconState)
    }
}

@Composable
@LightDarkThemePreview
internal fun IconPreview(
    @PreviewParameter(IconPreviewProvider::class)
    state: IconPreviewState,
) {
    RollerCoastersPreviewTheme {
        Icon(
            crossfade = state.crossfade,
            onClick = state.onClick,
            iconState = state.iconState,
        )
    }
}