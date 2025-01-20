package co.cuvva.presentation.design.system.icons

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.material3.Icon as MaterialIcon

@Composable
fun Icon(
    @DrawableRes iconResId: Int,
    @StringRes contentDescriptionResId: Int,
    onClick: (() -> Unit)? = null,
) {
    when {
        onClick != null -> IconWithClick(
            iconResId = iconResId,
            contentDescriptionResId = contentDescriptionResId,
            onClick = onClick,
        )

        else -> StaticIcon(
            iconResId = iconResId,
            contentDescriptionResId = contentDescriptionResId,
        )
    }
}

@Composable
private fun IconWithClick(
    @DrawableRes iconResId: Int,
    @StringRes contentDescriptionResId: Int,
    onClick: (() -> Unit),
) {
    IconButton(onClick = onClick) {
        StaticIcon(
            iconResId = iconResId,
            contentDescriptionResId = contentDescriptionResId,
        )
    }
}

@Composable
private fun StaticIcon(
    @DrawableRes iconResId: Int,
    @StringRes contentDescriptionResId: Int,
) {
    MaterialIcon(
        painter = painterResource(iconResId),
        contentDescription = stringResource(contentDescriptionResId),
    )
}