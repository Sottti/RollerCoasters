package com.sottti.roller.coasters.presentation.about.me.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import co.cuvva.presentation.design.system.icons.model.IconState
import com.roller.coasters.presentation.design.system.images.model.ImageState

@Immutable
internal data class AboutMeState(
    @StringRes val name: Int,
    @StringRes val title: Int,
    val profileImage: ProfileImageState,
    val socialProfiles: SocialProfilesState,
    val topics: TopicsState,
)

@Immutable
internal data class TopicsState(
    val android: Topics,
    val hobbies: Topics,
    val journey: Topic,
    val languages: Topics,
)

@Immutable
internal data class Topics(
    val firstTopic: Topic,
    val secondTopic: Topic,
    val thirdTopic: Topic,
    val fourthTopic: Topic,
)

@Immutable
internal data class Topic(
    @StringRes val textResId: Int,
    val description: TopicDescription,
)

@Immutable
internal data class TopicDescription(
    @StringRes val bodyResId: Int,
    @StringRes val titleResId: Int,
    val hyperlink: TopicHyperlink? = null,
    val image: TopicDescriptionImage,
)

@Immutable
internal sealed class TopicDescriptionImage {
    data class HeroImage(val state: ImageState) : TopicDescriptionImage()
    data class Image(val state: ImageState) : TopicDescriptionImage()
}

@Immutable
internal data class TopicHyperlink(
    @StringRes val textResId: Int,
    @StringRes val urlResId: Int,
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