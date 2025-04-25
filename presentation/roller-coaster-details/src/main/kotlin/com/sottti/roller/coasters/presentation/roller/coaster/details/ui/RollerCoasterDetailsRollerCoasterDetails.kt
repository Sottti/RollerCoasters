package com.sottti.roller.coasters.presentation.roller.coaster.details.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import co.cuvva.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsRollerCoasterViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsRow
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState.RollerCoasterIdentityViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState.RollerCoasterLocationViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState.RollerCoasterStatusViewState
import androidx.compose.material3.ListItem as ListItemMaterial

@Composable
internal fun RollerCoasterDetails(
    rollerCoaster: RollerCoasterDetailsRollerCoasterViewState,
    paddingValues: PaddingValues,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = dimensions.padding.medium),
        verticalArrangement = Arrangement.spacedBy(dimensions.padding.medium)
    ) {
        item { RollerCoasterDetailsSection(rollerCoaster.identity) }
        item { RollerCoasterDetailsSection(rollerCoaster.status) }
        item { RollerCoasterDetailsSection(rollerCoaster.location) }
    }
}

@Composable
internal fun RollerCoasterDetailsSection(
    details: RollerCoasterDetailsSectionViewState,
) {
    Header(details.header)
    Spacer(modifier = Modifier.height(dimensions.padding.small))
    Details(details)
}

@Composable
internal fun Header(
    @StringRes text: Int,
) {
    Text.Label.Large(
        text = stringResource(text),
        textColor = colors.onBackground
    )
}

@Composable
private fun Details(details: RollerCoasterDetailsSectionViewState) {
    when (details) {
        is RollerCoasterIdentityViewState -> IdentityDetails(details)
        is RollerCoasterLocationViewState -> LocationDetails(details)
        is RollerCoasterStatusViewState -> StatusDetails(details)
    }
}

@Composable
internal fun DetailsCard(
    content: @Composable () -> Unit,
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        content()
    }
}

@Composable
internal fun IdentityDetails(
    state: RollerCoasterIdentityViewState,
) {
    DetailsCard {
        ListItem(state = state.name, isFirstItem = true)
        ConditionalListItem(state.formerNames)
    }
}

@Composable
internal fun StatusDetails(
    state: RollerCoasterStatusViewState,
) {
    DetailsCard {
        ListItem(state = state.current, isFirstItem = true)
        ConditionalListItem(state.former)
        ConditionalListItem(state.openedDate)
        ConditionalListItem(state.closedDate)
    }
}

@Composable
internal fun LocationDetails(
    state: RollerCoasterLocationViewState,
) {
    DetailsCard {
        ListItem(state = state.park, isFirstItem = true)
        ConditionalListItem(state.relocations)
        ListItem(state.city)
        ListItem(state.region)
        ListItem(state.state)
        ListItem(state.country)
    }
}

@Composable
private fun ListItem(
    state: RollerCoasterDetailsRow,
    isFirstItem: Boolean = false,
) {
    if (!isFirstItem) HorizontalDivider()
    ListItemMaterial(
        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
        headlineContent = { Headline(state) },
        trailingContent = { Trailing(state) },
    )
}

@Composable
private fun ConditionalListItem(
    state: RollerCoasterDetailsRow?,
    isFirstItem: Boolean = false,
) {
    state?.let {
        if (!isFirstItem) HorizontalDivider()
        ListItem(state = state, isFirstItem = isFirstItem)
    }
}

@Composable
private fun Headline(state: RollerCoasterDetailsRow) {
    Text.Body.Medium(
        text = stringResource(state.headline),
        textColor = colors.onSurfaceVariant,
    )
}

@Composable
private fun Trailing(state: RollerCoasterDetailsRow) {
    state.trailing?.let { trailing ->
        Text.Body.Large(
            text = trailing,
            textColor = colors.onSurface,
        )
    }
}