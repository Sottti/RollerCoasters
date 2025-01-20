package com.sottti.roller.coasters.ui.favourites.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItem
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import co.cuvva.presentation.design.system.icons.Icon
import co.cuvva.presentation.design.system.icons.Icons

@Composable
internal fun SettingsContent(
    paddingValues: PaddingValues,
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues)
    ) {
        item { SettingsListItem() }
    }
}

@Composable
private fun SettingsListItem() {
    ListItem(
        modifier = Modifier.background(color = Color.Red),
        headlineContent = { Text("Headline") },
        leadingContent = { LeadingIcon() },
        overlineContent = { Text("Overline") },
        supportingContent = { Text("Supporting") },
        trailingContent = {  },
    )
}

@Composable
private fun LeadingIcon() {
    Icon(
        iconResId = Icons.Palette.outlined,
        contentDescriptionResId = Icons.Palette.descriptionResId
    )
}