package com.sottti.roller.coasters.presentation.design.system.chip

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import co.cuvva.presentation.design.system.icons.data.Icons
import co.cuvva.presentation.design.system.icons.ui.Icon
import co.cuvva.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme

@Composable
public fun Chip(
    @StringRes labelResId: Int,
    selected: Boolean,
    onClick: () -> Unit,
    expanded: Boolean? = null,
) {
    FilterChip(
        label = { Text.Vanilla(stringResource(labelResId)) },
        leadingIcon = { LeadingIcon(selected) },
        trailingIcon = { expanded?.let { TrailingIcon(expanded) } },
        onClick = onClick,
        selected = selected,
    )
}

@Composable
private fun LeadingIcon(selected: Boolean) =
    when {
        selected -> Icon(
            state = Icons.CheckSmall.Filled,
            modifier = Modifier.size(FilterChipDefaults.IconSize),
        )

        else -> null
    }

@Composable
private fun TrailingIcon(expanded: Boolean) =
    Icon(
        crossfade = true,
        modifier = Modifier.size(FilterChipDefaults.IconSize),
        state = if (expanded) Icons.ArrowDropUp.Filled else Icons.ArrowDropDown.Filled,
    )


@Composable
@PreviewLightDark
private fun ChipPreview(
    @PreviewParameter(ChipPreviewProvider::class)
    state: ChipState,
) {
    RollerCoastersPreviewTheme {
        Chip(
            expanded = state.expanded,
            labelResId = state.labelResId,
            onClick = state.onClick,
            selected = state.selected,
        )
    }
}