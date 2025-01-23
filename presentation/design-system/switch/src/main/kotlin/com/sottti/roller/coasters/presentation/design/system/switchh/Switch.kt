package com.sottti.roller.coasters.presentation.design.system.switchh

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
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
    )
}

@Composable
@PreviewLightDark
internal fun SwitchWithDynamicColorPreview(
    @PreviewParameter(SwitchPreviewProvider::class)
    state: SwitchState,
) {
    RollerCoastersPreviewTheme(
        dynamicColor = true,
        windowWidthSizeClass = WindowWidthSizeClass.Compact,
    ) {
        SwitchPreview(state)
    }
}

@Composable
@PreviewLightDark
internal fun SwitchWithoutDynamicColorPreview(
    @PreviewParameter(SwitchPreviewProvider::class)
    state: SwitchState,
) {
    RollerCoastersPreviewTheme(
        dynamicColor = false,
        windowWidthSizeClass = WindowWidthSizeClass.Compact,
    ) {
        SwitchPreview(state)
    }
}

@Composable
private fun SwitchPreview(state: SwitchState) {
    Switch(
        checked = state.checked,
        enabled = state.enabled,
        onCheckedChange = {},
    )
}