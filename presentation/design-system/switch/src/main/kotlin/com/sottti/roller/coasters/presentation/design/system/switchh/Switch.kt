package com.sottti.roller.coasters.presentation.design.system.switchh

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sottti.roller.coasters.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.presentation.design.system.icons.ui.icon.Icon
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreview
import androidx.compose.material3.Switch as MaterialSwitch

@Composable
public fun Switch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit),
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    MaterialSwitch(
        checked = checked,
        enabled = enabled,
        modifier = modifier,
        onCheckedChange = onCheckedChange,
        thumbContent = {
            when {
                checked -> Icon(iconState = Icons.CheckSmall.filled)
                else -> Icon(iconState = Icons.CloseSmall.outlined)
            }
        },
    )
}

@Composable
@RollerCoastersPreview
internal fun SwitchPreview(
    @PreviewParameter(SwitchStateProvider::class)
    state: SwitchState,
) {
    RollerCoastersPreviewTheme {
        Switch(
            checked = state.checked,
            enabled = state.enabled,
            onCheckedChange = {},
        )
    }
}