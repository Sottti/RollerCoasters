package co.cuvva.presentation.design.system.icons.ui

import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import co.cuvva.presentation.design.system.icons.model.IconState
import androidx.compose.material3.Icon as MaterialIcon

@Composable
public fun Icon(
    state: IconState,
    onClick: (() -> Unit)? = null,
) {
    when {
        onClick != null -> IconWithClick(state, onClick = onClick)
        else -> StaticIcon(state)
    }
}

@Composable
private fun IconWithClick(
    state: IconState,
    onClick: (() -> Unit),
) {
    IconButton(onClick = onClick) { StaticIcon(state) }
}

@Composable
private fun StaticIcon(
    state: IconState,
) {
    MaterialIcon(
        painter = painterResource(state.resId),
        contentDescription = stringResource(state.descriptionResId),
    )
}