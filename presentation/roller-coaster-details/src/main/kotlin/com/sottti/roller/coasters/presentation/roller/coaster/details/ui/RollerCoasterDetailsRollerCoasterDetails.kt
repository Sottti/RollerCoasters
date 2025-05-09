package com.sottti.roller.coasters.presentation.roller.coaster.details.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import co.cuvva.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.map.Map
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsRollerCoasterViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsRow
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState.RollerCoasterIdentityViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState.RollerCoasterLocationViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState.RollerCoasterSpecsViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState.RollerCoasterStatusViewState
import androidx.compose.material3.ListItem as ListItemMaterial

@Composable
internal fun RollerCoasterDetails(
    rollerCoaster: RollerCoasterDetailsRollerCoasterViewState,
    paddingValues: PaddingValues,
    nestedScrollConnection: NestedScrollConnection,
) {
    val bottomPadding = paddingValues.calculateBottomPadding() + dimensions.padding.medium
    val horizontalPadding = dimensions.padding.medium
    val topPadding = paddingValues.calculateTopPadding() + dimensions.padding.medium

    LazyColumn(
        modifier = Modifier
            .nestedScroll(nestedScrollConnection)
            .fillMaxSize(),
        contentPadding = PaddingValues(
            bottom = bottomPadding,
            end = horizontalPadding,
            start = horizontalPadding,
            top = topPadding,
        ),
        verticalArrangement = Arrangement.spacedBy(dimensions.padding.mediumLarge),
    ) {
        item { RollerCoasterDetailsSection(rollerCoaster.identity) }
        item { RollerCoasterDetailsSection(rollerCoaster.status) }
        item { RollerCoasterDetailsSection(rollerCoaster.location) }
        item { RollerCoasterDetailsSection(rollerCoaster.specs) }
    }
}

@Composable
internal fun RollerCoasterDetailsSection(
    details: RollerCoasterDetailsSectionViewState,
) {
    Header(details.header)
    Spacer(modifier = Modifier.height(dimensions.padding.smallMedium))
    when (details) {
        is RollerCoasterIdentityViewState -> IdentityDetails(details)
        is RollerCoasterLocationViewState -> LocationDetails(details)
        is RollerCoasterSpecsViewState -> SpecsDetails(details)
        is RollerCoasterStatusViewState -> StatusDetails(details)
    }
}

@Composable
internal fun SpecsDetails(
    details: RollerCoasterSpecsViewState,
) {
    DetailsCard {
        ListItem(state = details.manufacturer, showDivider = false)
        ListItem(state = details.model, showDivider = details.manufacturer != null)
        ListItem(state = details.capacity)
        ListItem(state = details.cost)
    }
}


@Composable
internal fun IdentityDetails(
    state: RollerCoasterIdentityViewState,
) {
    DetailsCard {
        ListItem(state = state.name, showDivider = false)
        ListItem(state.formerNames)
    }
}

@Composable
internal fun StatusDetails(
    state: RollerCoasterStatusViewState,
) {
    DetailsCard {
        ListItem(state = state.current, showDivider = false)
        ListItem(state.former)
        ListItem(state.openedDate)
        ListItem(state.closedDate)
    }
}

@Composable
internal fun LocationDetails(
    state: RollerCoasterLocationViewState,
) {
    DetailsCard {
        state.coordinates?.let {
            Map(
                latitude = state.coordinates.latitude,
                longitude = state.coordinates.longitude,
                markerTitle = state.mapMarkerTitle,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1.75f)
            )
        }
        ListItem(state = state.park, showDivider = false)
        ListItem(state.city)
        ListItem(state.country)
        ListItem(state.relocations)
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
private fun ListItem(
    state: RollerCoasterDetailsRow?,
    showDivider: Boolean = true,
) {
    state?.let {
        if (showDivider) HorizontalDivider()
        ListItemMaterial(
            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
            headlineContent = { Headline(state) },
            trailingContent = { Trailing(state) },
        )
    }
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
private fun Headline(state: RollerCoasterDetailsRow) {
    Text.Body.Large(
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