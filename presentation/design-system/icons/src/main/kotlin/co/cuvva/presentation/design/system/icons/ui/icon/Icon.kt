package co.cuvva.presentation.design.system.icons.ui.icon

import androidx.compose.animation.Crossfade
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
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
    when {
        crossfade -> Crossfade(targetState = iconState) { targetState ->
            Icon(modifier, targetState, onClick)
        }

        else -> Icon(modifier, iconState, onClick)
    }
}

@Composable
private fun Icon(
    modifier: Modifier,
    state: IconState,
    onClick: (() -> Unit)? = null,
) {
    when {
        onClick != null -> IconWithClick(modifier, state, onClick = onClick)
        else -> StaticIcon(modifier, state)
    }
}

@Composable
private fun IconWithClick(
    modifier: Modifier,
    state: IconState,
    onClick: (() -> Unit),
) {
    IconButton(onClick = onClick) { StaticIcon(modifier, state) }
}

@Composable
private fun StaticIcon(
    modifier: Modifier,
    state: IconState,
) {
    MaterialIcon(
        contentDescription = stringResource(state.descriptionResId),
        modifier = modifier,
        painter = painterResource(state.resId),
    )
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