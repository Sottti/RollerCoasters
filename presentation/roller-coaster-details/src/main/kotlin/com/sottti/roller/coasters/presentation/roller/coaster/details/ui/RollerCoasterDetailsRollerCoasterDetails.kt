package com.sottti.roller.coasters.presentation.roller.coaster.details.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sotti.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.map.Map
import com.sottti.roller.coasters.presentation.image.loading.Image
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsImageViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsRollerCoasterViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsRow
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState.RollerCoasterIdentityViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState.RollerCoasterLocationViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState.RollerCoasterRideViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionViewState.RollerCoasterStatusViewState
import androidx.compose.material3.ListItem as ListItemMaterial

@Composable
internal fun RollerCoasterDetails(
    rollerCoaster: RollerCoasterDetailsRollerCoasterViewState,
    paddingValues: PaddingValues,
    nestedScrollConnection: NestedScrollConnection,
) {
    val topPadding = paddingValues.calculateTopPadding() + dimensions.padding.medium
    val bottomPadding = paddingValues.calculateBottomPadding() + dimensions.padding.medium

    LazyColumn(
        modifier = Modifier
            .nestedScroll(nestedScrollConnection)
            .fillMaxSize(),
        contentPadding = PaddingValues(bottom = bottomPadding, top = topPadding),
        verticalArrangement = Arrangement.spacedBy(dimensions.padding.mediumLarge),
    ) {
        rollerCoaster.images?.let { item { RollerCoasterImagesSection(rollerCoaster.images) } }
        item { RollerCoasterDetailsSection(rollerCoaster.identity) }
        rollerCoaster.status?.let { item { RollerCoasterDetailsSection(rollerCoaster.status) } }
        item { RollerCoasterDetailsSection(rollerCoaster.location) }
        rollerCoaster.ride?.let { item { RollerCoasterDetailsSection(rollerCoaster.ride) } }
    }
}

@Composable
internal fun RollerCoasterImagesSection(
    images: List<RollerCoasterDetailsImageViewState>,
) {
    val pagerState = rememberPagerState { images.size }
    val carouselHeight = (LocalWindowInfo.current.containerSize.height * 0.25f).dp


    Column(Modifier.fillMaxWidth()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            pageSpacing = dimensions.padding.smallMedium,
            contentPadding = PaddingValues(horizontal = dimensions.padding.medium),
        ) { page ->
            Image(
                url = images[page].imageUrl,
                contentDescription = images[page].contentDescription,
                roundedCorners = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(carouselHeight),
            )
        }
    }
}

@Composable
internal fun RollerCoasterDetailsSection(
    details: RollerCoasterDetailsSectionViewState,
) {
    Column(modifier = Modifier.padding(horizontal = dimensions.padding.medium)) {
        Header(details.header)
        Spacer(modifier = Modifier.height(dimensions.padding.smallMedium))
        when (details) {
            is RollerCoasterIdentityViewState -> IdentityDetails(details)
            is RollerCoasterLocationViewState -> LocationDetails(details)
            is RollerCoasterRideViewState -> RideDetails(details)
            is RollerCoasterStatusViewState -> StatusDetails(details)
        }
    }
}

@Composable
internal fun RideDetails(details: RollerCoasterRideViewState) {
    val items = listOfNotNull(
        details.height,
        details.drop,
        details.maxVertical,
        details.speed,
        details.inversions,
        details.gForce,
        details.length,
        details.duration
    )

    DetailsCard {
        items.forEachIndexed { index, item ->
            ListItem(state = item, showBottomDivider = index < items.lastIndex)
        }
    }
}

@Composable
internal fun IdentityDetails(state: RollerCoasterIdentityViewState) {
    val items = listOfNotNull(state.name, state.formerNames)

    DetailsCard {
        items.forEachIndexed { index, item ->
            ListItem(state = item, showBottomDivider = index < items.lastIndex)
        }
    }
}

@Composable
internal fun StatusDetails(state: RollerCoasterStatusViewState) {
    val items = listOfNotNull(state.current, state.former, state.openedDate, state.closedDate)

    DetailsCard {
        items.forEachIndexed { index, item ->
            ListItem(state = item, showBottomDivider = index < items.lastIndex)
        }
    }
}

@Composable
internal fun LocationDetails(state: RollerCoasterLocationViewState) {
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

        val items = listOfNotNull(state.park, state.city, state.country, state.relocations)
        items.forEachIndexed { index, item ->
            ListItem(state = item, showBottomDivider = index < items.lastIndex)
        }
    }
}

@Composable
internal fun DetailsCard(content: @Composable () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        content()
    }
}

@Composable
private fun ListItem(
    state: RollerCoasterDetailsRow?,
    showBottomDivider: Boolean = true,
) {
    state?.let {
        ListItemMaterial(
            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
            headlineContent = { Headline(state) },
            trailingContent = { Trailing(state) },
        )
        if (showBottomDivider) HorizontalDivider()
    }
}

@Composable
internal fun Header(@StringRes text: Int) {
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