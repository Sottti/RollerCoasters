package com.sottti.roller.coasters.presentation.roller.coaster.details.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.unit.Dp
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
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.TopBarState
import com.sottti.roller.coasters.presentation.utils.Spacer
import androidx.compose.material3.ListItem as MaterialListItem

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun RollerCoasterDetailsContent(
    content: RollerCoasterDetailsContentState,
    onBackNavigation: () -> Unit,
    onToggleFavourite: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    topBarState: TopBarState,
) {
    Scaffold(
        topBar = {
            TopBar(
                onBackNavigation = onBackNavigation,
                onToggleFavourite = onToggleFavourite,
                scrollBehavior = scrollBehavior,
                state = topBarState,
            )
        }) { padding: PaddingValues ->
        when (content) {
            RollerCoasterDetailsContentState.Error ->
                ErrorUi(
                    modifier = Modifier.padding(padding),
                    button = ErrorButton {})

            Loading -> ProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
            )

            is Loaded -> LoadedContent(
                nestedScrollConnection = scrollBehavior.nestedScrollConnection,
                padding = padding,
                state = content.rollerCoaster,
            )
        }
    }
}

@Composable
private fun LoadedContent(
    nestedScrollConnection: NestedScrollConnection,
    padding: PaddingValues,
    state: RollerCoasterDetailsRollerCoasterState,
) {
    val topPadding = padding.calculateTopPadding() + dimensions.padding.medium
    val bottomPadding = padding.calculateBottomPadding() + dimensions.padding.medium

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
private fun ImagesSection(
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
private fun DetailsSection(
    details: RollerCoasterDetailsSectionState,
) {
    Column(modifier = Modifier.padding(horizontal = dimensions.padding.medium)) {
        Header(details.header)
        Spacer(size = dimensions.padding.smallMedium)
        when (details) {
            is RollerCoasterIdentityState -> IdentityDetails(details)
            is RollerCoasterLocationState -> LocationDetails(details)
            is RollerCoasterRideState -> RideDetails(details)
            is RollerCoasterStatusState -> StatusDetails(details)
        }
    }
}

@Composable
private fun RideDetails(details: RollerCoasterRideState) {
    val items = listOfNotNull(
        details.speed,
        details.height,
        details.drop,
        details.maxVertical,
        details.inversions,
        details.length,
        details.duration,
        details.gForce,
    )

    DetailsCard {
        items.forEachIndexed { index, item ->
            ListItem(state = item, showBottomDivider = index < items.lastIndex)
        }
    }
}

@Composable
private fun IdentityDetails(state: RollerCoasterIdentityState) {
    val items = listOfNotNull(state.name, state.formerNames)

    DetailsCard {
        items.forEachIndexed { index, item ->
            ListItem(state = item, showBottomDivider = index < items.lastIndex)
        }
    }
}

@Composable
private fun StatusDetails(state: RollerCoasterStatusState) {
    val items = listOfNotNull(state.current, state.former, state.openedDate, state.closedDate)

    DetailsCard {
        items.forEachIndexed { index, item ->
            ListItem(state = item, showBottomDivider = index < items.lastIndex)
        }
    }
}

@Composable
private fun LocationDetails(state: RollerCoasterLocationState) {
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

        val items = listOfNotNull(state.park, state.city, state.country, state.relocations)
        items.forEachIndexed { index, item ->
            ListItem(state = item, showBottomDivider = index < items.lastIndex)
        }
    }
}

@Composable
private fun DetailsCard(content: @Composable () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        content()
    }
}

@Composable
private fun ListItem(
    state: RollerCoasterDetailsRow,
    showBottomDivider: Boolean = true,
) {
    val windowWidthPx = LocalWindowInfo.current.containerSize.width
    val density = LocalDensity.current
    val screenWidth = with(density) { windowWidthPx.toDp() }
    MaterialListItem(
        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
        headlineContent = { Headline(screenWidth = screenWidth, state = state) },
        trailingContent = { Trailing(screenWidth = screenWidth, state = state) },
    )
    if (showBottomDivider) HorizontalDivider()
}

@Composable
private fun Header(@StringRes text: Int) {
    Text.Label.Large(
        text = stringResource(text),
        textColor = colors.onBackground
    )
}

@Composable
private fun Headline(
    screenWidth: Dp,
    state: RollerCoasterDetailsRow,
) {
    Text.Body.Large(
        text = stringResource(state.headline),
        textColor = colors.onSurfaceVariant,
        modifier = Modifier.widthIn(max = screenWidth * 0.4f)
    )
}

@Composable
private fun Trailing(
    screenWidth: Dp,
    state: RollerCoasterDetailsRow,
) {
    state.trailing?.let { trailing ->
        Text.Body.Large(
            text = trailing,
            textColor = colors.onSurface,
            modifier = Modifier.widthIn(max = screenWidth * 0.5f)
        )
    }
}