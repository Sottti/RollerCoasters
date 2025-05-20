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
import androidx.compose.foundation.lazy.rememberLazyListState
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
import com.sottti.roller.coasters.presentation.about.me.data.socialNetworkPrimaryColor
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeAction
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeAction.OpenUrl
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeState
import com.sottti.roller.coasters.presentation.about.me.model.GridTopics
import com.sottti.roller.coasters.presentation.about.me.model.ProfileImageState
import com.sottti.roller.coasters.presentation.about.me.model.SocialProfilesState
import com.sottti.roller.coasters.presentation.about.me.model.TopicsState
import com.sottti.roller.coasters.presentation.design.system.card.grid.CardGrid
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.profile.picture.ProfilePicture

@Composable
internal fun AboutMeUiContent(
    nestedScrollConnection: NestedScrollConnection,
    onAction: (AboutMeAction) -> Unit,
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
            state = rememberLazyListState(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item { ProfileImage(state.profileImage) }
            item { Spacer(modifier = Modifier.size(dimensions.padding.smallMedium)) }
            item { Name(state.name) }
            item { Spacer(modifier = Modifier.size(dimensions.padding.smallMedium)) }
            item { SocialProfiles(onAction = onAction, state = state.socialProfiles) }
            item { Spacer(modifier = Modifier.size(dimensions.padding.large)) }
            item { Topics(state.topics) }
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
                val primaryColor = socialNetworkPrimaryColor(profile.url)
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
    topics: TopicsState,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = topLevelCardShape(),
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(dimensions.padding.medium),
            verticalArrangement = Arrangement.spacedBy(dimensions.padding.medium),
        ) {
            AndroidTopics(topics.android)
            LanguageTopics(topics.languages)
            HobbiesTopics(topics.hobbies)
        }
    }
}

@Composable
private fun AndroidTopics(topics: GridTopics) {
    CardGrid(
        firstItem = topics.firstTopic,
        secondItem = topics.secondTopic,
        thirdItem = topics.thirdTopic,
        forthItem = topics.fourthTopic,
        iconState = Icons.Android.filled,
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
private fun HobbiesTopics(topics: GridTopics) {
    CardGrid(
        firstItem = topics.firstTopic,
        secondItem = topics.secondTopic,
        thirdItem = topics.thirdTopic,
        forthItem = topics.fourthTopic,
        iconState = Icons.Hobbies.outlined,
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
private fun LanguageTopics(topics: GridTopics) {
    CardGrid(
        firstItem = topics.firstTopic,
        secondItem = topics.secondTopic,
        thirdItem = topics.thirdTopic,
        forthItem = topics.fourthTopic,
        iconState = Icons.Translate.outlined,
        modifier = Modifier.fillMaxWidth(),
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