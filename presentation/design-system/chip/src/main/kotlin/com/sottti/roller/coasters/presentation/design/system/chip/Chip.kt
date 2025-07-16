package com.sottti.roller.coasters.presentation.design.system.chip

import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sottti.roller.coasters.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.presentation.design.system.icons.model.IconState
import com.sottti.roller.coasters.presentation.design.system.icons.ui.icon.Icon
import com.sottti.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreview

@Composable
public fun Chip(
    @StringRes labelResId: Int,
    selected: Boolean,
    onClick: () -> Unit,
    leadingIcon: IconState? = null,
    expanded: Boolean? = null,
) {
    FilterChip(
        label = { Text.Vanilla(stringResource(labelResId)) },
        leadingIcon = leadingIcon?.let { { LeadingIcon(leadingIcon) } },
        trailingIcon = { expanded?.let { TrailingIcon(expanded) } },
        onClick = onClick,
        selected = selected || expanded == true,
        modifier = Modifier.animateContentSize(),
    )
}

@Composable
private fun LeadingIcon(
    state: IconState,
) = Icon(
    iconState = state,
    modifier = Modifier.size(FilterChipDefaults.IconSize),
)

@Composable
private fun TrailingIcon(expanded: Boolean) =
    Icon(
        crossfade = true,
        modifier = Modifier.size(FilterChipDefaults.IconSize),
        iconState = if (expanded) Icons.ArrowDropUp.filled else Icons.ArrowDropDown.filled,
    )


@Composable
@RollerCoastersPreview
internal fun ChipPreview(
    @PreviewParameter(ChipPreviewProvider::class)
    state: ChipState,
) {
    RollerCoastersPreviewTheme {
        Chip(
            expanded = state.expanded,
            labelResId = state.labelResId,
            leadingIcon = state.leadingIcon,
            onClick = state.onClick,
            selected = state.selected,
        )
    }
}