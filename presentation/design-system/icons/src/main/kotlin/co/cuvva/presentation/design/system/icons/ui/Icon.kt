package co.cuvva.presentation.design.system.icons.ui

import androidx.compose.animation.Crossfade
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import co.cuvva.presentation.design.system.icons.model.IconState
import androidx.compose.material3.Icon as MaterialIcon

@Composable
public fun Icon(
    state: IconState,
    modifier: Modifier = Modifier,
    crossfade: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    when {
        crossfade -> Crossfade(targetState = state) { targetState ->
            Icon(modifier, targetState, onClick)
        }

        else -> Icon(modifier, state, onClick)
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