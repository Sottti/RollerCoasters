package com.sottti.roller.coasters.presentation.about.me.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import co.cuvva.presentation.design.system.icons.model.IconState
import com.roller.coasters.presentation.design.system.images.model.ImageState

@Immutable
internal data class AboutMeState(
    @StringRes val name: Int,
    val profileImage: ProfileImageState,
    val socialProfiles: SocialProfilesState,
    val topics: TopicsState,
)

@Immutable
internal data class TopicsState(
    val android: GridTopics,
    val hobbies: GridTopics,
    val languages: GridTopics,
)

@Immutable
internal data class GridTopics(
    val firstTopic: Int,
    val secondTopic: Int,
    val thirdTopic: Int,
    val fourthTopic: Int,
)

@Immutable
internal data class ProfileImageState(
    val image: ImageState,
)

@Immutable
internal data class SocialProfilesState(
    val profiles: List<SocialProfileState>,
)

@Immutable
internal data class SocialProfileState(
    @StringRes val text: Int,
    @StringRes val url: Int,
    val icon: IconState,
)