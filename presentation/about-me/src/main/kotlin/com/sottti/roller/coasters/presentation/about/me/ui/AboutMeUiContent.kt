package com.sottti.roller.coasters.presentation.about.me.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.sottti.roller.coasters.presentation.design.system.images.model.ImageState
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeAction
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeAction.OpenUrl
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeState
import com.sottti.roller.coasters.presentation.about.me.model.SocialProfileState
import com.sottti.roller.coasters.presentation.about.me.model.Topic
import com.sottti.roller.coasters.presentation.about.me.model.TopicDescription
import com.sottti.roller.coasters.presentation.about.me.model.Topics
import com.sottti.roller.coasters.presentation.about.me.model.TopicsState
import com.sottti.roller.coasters.presentation.about.me.ui.bottomsheets.BottomSheetContent
import com.sottti.roller.coasters.presentation.design.system.card.grid.CardGrid
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.colors.color.externalNavigationPrimaryColor
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.hero.image.HeroImage
import com.sottti.roller.coasters.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.presentation.design.system.icons.model.IconState
import com.sottti.roller.coasters.presentation.design.system.icons.ui.pilledIcon.PilledIcon
import com.sottti.roller.coasters.presentation.design.system.text.Text

@Composable
internal fun AboutMeUiContent(
    listState: LazyListState,
    nestedScrollConnection: NestedScrollConnection,
    onAction: (AboutMeAction) -> Unit,
    onShowBottomSheet: (@Composable ColumnScope.() -> Unit) -> Unit,
    paddingValues: PaddingValues,
    state: AboutMeState,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .nestedScroll(nestedScrollConnection),
            state = listState,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item { ProfileImage(state.profileImage) }
            item { Spacer(modifier = Modifier.size(dimensions.padding.smallMedium)) }
            item { Name(state.name) }
            item { Spacer(modifier = Modifier.size(dimensions.padding.large)) }
            item { SocialProfiles(onAction = onAction, state = state.socialProfiles) }
            item { Spacer(modifier = Modifier.size(dimensions.padding.large)) }
            item {
                GetToKnowMe(
                    onAction = onAction,
                    onShowBottomSheet = onShowBottomSheet,
                    topics = state.getToKnowMe,
                )
            }
        }
        FillerCard()
    }
}


@Composable
internal fun ProfileImage(state: ImageState) {
    HeroImage(
        modifier = Modifier
            .padding(top = dimensions.padding.large)
            .fillMaxWidth(0.33f)
            .aspectRatio(1f),
        image = state,
    )
}

@Composable
private fun Name(state: Int) {
    Text.Headline.Medium(
        textResId = state,
        textColor = colors.onBackground,
    )
}

@Composable
internal fun SocialProfiles(
    onAction: (AboutMeAction) -> Unit,
    state: List<SocialProfileState>,
) {
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier
            .horizontalScroll(scrollState)
            .padding(horizontal = dimensions.padding.medium),
        horizontalArrangement = Arrangement.spacedBy(dimensions.padding.smallMedium),
    ) {
        state.forEach { profile ->
            val primaryColor = externalNavigationPrimaryColor(profile.url)
            PilledIcon(
                text = profile.text,
                iconState = profile.icon,
                onClick = {
                    onAction(
                        OpenUrl(
                            urlResId = profile.url,
                            primaryColor = primaryColor,
                        )
                    )
                },
            )
        }
    }
}

@Composable
private fun GetToKnowMe(
    onAction: (AboutMeAction) -> Unit,
    onShowBottomSheet: (@Composable ColumnScope.() -> Unit) -> Unit,
    topics: TopicsState,
) {
    Card(
        shape = topLevelCardShape(),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(dimensions.padding.medium),
            verticalArrangement = Arrangement.spacedBy(dimensions.padding.medium),
        ) {
            AndroidJourney(
                state = topics.journey,
                onClick = {
                    onShowBottomSheet {
                        BottomSheetContent(onAction = onAction, state = topics.journey.description)
                    }
                }
            )
            TopicsGrid(
                topics = topics.android,
                iconState = Icons.Android.filled,
                onClick = { position ->
                    showTopicBottomSheet(
                        topic = topics.android.description(position),
                        onAction = onAction,
                        onShow = onShowBottomSheet
                    )
                },
            )
            TopicsGrid(
                topics = topics.languages,
                iconState = Icons.Translate.outlined,
                onClick = { position ->
                    showTopicBottomSheet(
                        topic = topics.languages.description(position),
                        onAction = onAction,
                        onShow = onShowBottomSheet
                    )
                })
            TopicsGrid(
                topics = topics.hobbies,
                iconState = Icons.Hobbies.outlined,
                onClick = { position ->
                    showTopicBottomSheet(
                        topic = topics.hobbies.description(position),
                        onAction = onAction,
                        onShow = onShowBottomSheet
                    )
                })
        }
    }
}

@Composable
private fun TopicsGrid(
    topics: Topics,
    iconState: IconState,
    onClick: (Int) -> Unit,
) = CardGrid(
    firstItem = topics.firstTopic.textResId,
    secondItem = topics.secondTopic.textResId,
    thirdItem = topics.thirdTopic.textResId,
    forthItem = topics.fourthTopic.textResId,
    iconState = iconState,
    modifier = Modifier.fillMaxWidth(),
    onClick = onClick,
)

private inline fun showTopicBottomSheet(
    topic: TopicDescription?,
    noinline onAction: (AboutMeAction) -> Unit,
    onShow: (@Composable ColumnScope.() -> Unit) -> Unit,
) {
    topic?.let { description ->
        onShow { BottomSheetContent(onAction, description) }
    }
}

@Composable
private fun AndroidJourney(
    state: Topic,
    onClick: (() -> Unit),
) {
    CardGrid(
        item = state.textResId,
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
    )
}

@Composable
private fun topLevelCardShape(): CornerBasedShape =
    MaterialTheme.shapes.extraLarge.copy(
        bottomStart = ZeroCornerSize,
        bottomEnd = ZeroCornerSize,
    )

@Composable
private fun ColumnScope.FillerCard() {
    Card(
        shape = RectangleShape,
        modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
    ) {}
}