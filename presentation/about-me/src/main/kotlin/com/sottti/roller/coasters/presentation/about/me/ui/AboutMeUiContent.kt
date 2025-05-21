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
import co.cuvva.presentation.design.system.icons.data.Icons
import co.cuvva.presentation.design.system.icons.ui.pilledIcon.PilledIcon
import co.cuvva.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.about.me.data.externalNavigationPrimaryColor
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeAction
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeAction.OpenUrl
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeState
import com.sottti.roller.coasters.presentation.about.me.model.ProfileImageState
import com.sottti.roller.coasters.presentation.about.me.model.SocialProfilesState
import com.sottti.roller.coasters.presentation.about.me.model.TopicDescription
import com.sottti.roller.coasters.presentation.about.me.model.Topics
import com.sottti.roller.coasters.presentation.about.me.model.TopicsState
import com.sottti.roller.coasters.presentation.design.system.card.grid.CardGrid
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.profile.picture.ProfilePicture

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
            item { Spacer(modifier = Modifier.size(dimensions.padding.medium)) }
            item { SocialProfiles(onAction = onAction, state = state.socialProfiles) }
            item { Spacer(modifier = Modifier.size(dimensions.padding.large)) }
            item { Topics(onAction, onShowBottomSheet = onShowBottomSheet, topics = state.topics) }
        }
        FillerCard()
    }
}


@Composable
internal fun ProfileImage(state: ProfileImageState) {
    ProfilePicture(
        modifier = Modifier
            .padding(top = dimensions.padding.large)
            .fillMaxWidth(0.33f)
            .aspectRatio(1f),
        image = state.image,
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
    state: SocialProfilesState,
) {
    Column(verticalArrangement = Arrangement.spacedBy(dimensions.padding.medium)) {
        val scrollState = rememberScrollState()
        Row(
            modifier = Modifier
                .horizontalScroll(scrollState)
                .padding(horizontal = dimensions.padding.medium),
            horizontalArrangement = Arrangement.spacedBy(dimensions.padding.smallMedium),
        ) {
            state.profiles.forEach { profile ->
                val primaryColor = externalNavigationPrimaryColor(profile.url)
                PilledIcon(
                    text = profile.text,
                    iconState = profile.icon,
                    onClick = { onAction(OpenUrl(url = profile.url, primaryColor = primaryColor)) },
                )
            }
        }
    }
}

@Composable
private fun Topics(
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
            AndroidTopics(
                topics = topics.android,
                onClick = { position ->
                    onShowBottomSheet {
                        BottomSheetContent(
                            onAction = onAction,
                            topicDescription = topics.android.description(position),
                        )
                    }
                },
            )
            LanguageTopics(
                topics = topics.languages,
                onClick = { position ->
                    onShowBottomSheet {
                        BottomSheetContent(
                            onAction = onAction,
                            topicDescription = topics.languages.description(position),
                        )
                    }
                })
            HobbiesTopics(
                topics = topics.hobbies,
                onClick = { position ->
                    onShowBottomSheet {
                        BottomSheetContent(
                            onAction = onAction,
                            topicDescription = topics.hobbies.description(position),
                        )
                    }
                })
        }
    }
}

private fun Topics.description(position: Int): TopicDescription? =
    when (position) {
        0 -> firstTopic.description
        1 -> secondTopic.description
        2 -> thirdTopic.description
        3 -> fourthTopic.description
        else -> null
    }

@Composable
private fun AndroidTopics(
    onClick: (position: Int) -> Unit,
    topics: Topics,
) {
    CardGrid(
        firstItem = topics.firstTopic.textResId,
        secondItem = topics.secondTopic.textResId,
        thirdItem = topics.thirdTopic.textResId,
        forthItem = topics.fourthTopic.textResId,
        iconState = Icons.Android.filled,
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
    )
}

@Composable
private fun HobbiesTopics(
    onClick: ((Int) -> Unit),
    topics: Topics,
) {
    CardGrid(
        firstItem = topics.firstTopic.textResId,
        secondItem = topics.secondTopic.textResId,
        thirdItem = topics.thirdTopic.textResId,
        forthItem = topics.fourthTopic.textResId,
        iconState = Icons.Hobbies.outlined,
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
    )
}

@Composable
private fun LanguageTopics(
    topics: Topics,
    onClick: ((Int) -> Unit),
) {
    CardGrid(
        firstItem = topics.firstTopic.textResId,
        secondItem = topics.secondTopic.textResId,
        thirdItem = topics.thirdTopic.textResId,
        forthItem = topics.fourthTopic.textResId,
        iconState = Icons.Translate.outlined,
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