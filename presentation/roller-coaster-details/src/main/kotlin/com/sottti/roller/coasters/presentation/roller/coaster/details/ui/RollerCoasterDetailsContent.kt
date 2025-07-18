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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.stringResource
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.map.Map
import com.sottti.roller.coasters.presentation.design.system.progress.indicators.ProgressIndicator
import com.sottti.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.error.ErrorButton
import com.sottti.roller.coasters.presentation.error.ErrorUi
import com.sottti.roller.coasters.presentation.image.loading.Image
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsContentState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsContentState.Loaded
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsContentState.Loading
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsImageState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsRollerCoasterState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsRow
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsSectionState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterIdentityState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterLocationState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterRideState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterStatusState
import androidx.compose.material3.ListItem as ListItemMaterial

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun RollerCoasterDetailsContent(
    state: RollerCoasterDetailsContentState,
    paddingValues: PaddingValues,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    when (state) {
        RollerCoasterDetailsContentState.Error ->
            ErrorUi(modifier = Modifier.padding(paddingValues), button = ErrorButton {})

        Loading -> ProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        )

        is Loaded -> LoadedContent(
            nestedScrollConnection = scrollBehavior.nestedScrollConnection,
            paddingValues = paddingValues,
            state = state.rollerCoaster,
        )
    }
}

@Composable
private fun LoadedContent(
    nestedScrollConnection: NestedScrollConnection,
    paddingValues: PaddingValues,
    state: RollerCoasterDetailsRollerCoasterState,
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
        state.images?.let { item { ImagesSection(state.images) } }
        item { DetailsSection(state.identity) }
        state.status?.let { item { DetailsSection(state.status) } }
        item { DetailsSection(state.location) }
        state.ride?.let { item { DetailsSection(state.ride) } }
    }
}

@Composable
internal fun ImagesSection(
    images: List<RollerCoasterDetailsImageState>,
) {
    val pagerState = rememberPagerState { images.size }

    val density = LocalDensity.current
    val windowHeightPx = LocalWindowInfo.current.containerSize.height
    val windowHeightDp = with(density) { windowHeightPx.toDp() }
    val carouselHeight = remember(windowHeightDp) { (windowHeightDp * 0.25f) }

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
internal fun DetailsSection(
    details: RollerCoasterDetailsSectionState,
) {
    Column(modifier = Modifier.padding(horizontal = dimensions.padding.medium)) {
        Header(details.header)
        Spacer(modifier = Modifier.height(dimensions.padding.smallMedium))
        when (details) {
            is RollerCoasterIdentityState -> IdentityDetails(details)
            is RollerCoasterLocationState -> LocationDetails(details)
            is RollerCoasterRideState -> RideDetails(details)
            is RollerCoasterStatusState -> StatusDetails(details)
        }
    }
}

@Composable
internal fun RideDetails(details: RollerCoasterRideState) {
    val items = remember(
        details.speed,
        details.height,
        details.drop,
        details.maxVertical,
        details.inversions,
        details.length,
        details.duration,
        details.gForce,
    ) {
        listOfNotNull(
            details.speed,
            details.height,
            details.drop,
            details.maxVertical,
            details.inversions,
            details.length,
            details.duration,
            details.gForce,
        )
    }

    DetailsCard {
        items.forEachIndexed { index, item ->
            ListItem(state = item, showBottomDivider = index < items.lastIndex)
        }
    }
}

@Composable
internal fun IdentityDetails(state: RollerCoasterIdentityState) {
    val items = remember(state.name, state.formerNames) {
        listOfNotNull(state.name, state.formerNames)
    }

    DetailsCard {
        items.forEachIndexed { index, item ->
            ListItem(state = item, showBottomDivider = index < items.lastIndex)
        }
    }
}

@Composable
internal fun StatusDetails(state: RollerCoasterStatusState) {
    val items = remember(
        state.current,
        state.former,
        state.openedDate,
        state.closedDate,
    ) {
        listOfNotNull(state.current, state.former, state.openedDate, state.closedDate)
    }

    DetailsCard {
        items.forEachIndexed { index, item ->
            ListItem(state = item, showBottomDivider = index < items.lastIndex)
        }
    }
}

@Composable
internal fun LocationDetails(state: RollerCoasterLocationState) {
    DetailsCard {
        val coordinates = state.coordinates
        coordinates?.let {
            Map(
                latitude = it.latitude,
                longitude = it.longitude,
                markerTitle = state.mapMarkerTitle,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1.75f)
            )
        }

        val items = remember(state.park, state.city, state.country, state.relocations) {
            listOfNotNull(state.park, state.city, state.country, state.relocations)
        }
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
    state: RollerCoasterDetailsRow,
    showBottomDivider: Boolean = true,
) {
    ListItemMaterial(
        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
        headlineContent = { Headline(state) },
        trailingContent = { Trailing(state) },
    )
    if (showBottomDivider) HorizontalDivider()
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