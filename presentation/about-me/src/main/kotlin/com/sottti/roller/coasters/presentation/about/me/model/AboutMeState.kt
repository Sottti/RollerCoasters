package com.sottti.roller.coasters.presentation.about.me.model

import androidx.annotation.IntRange
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.sottti.roller.coasters.presentation.design.system.images.model.ImageState
import com.sottti.roller.coasters.presentation.design.system.icons.model.IconState

@Immutable
internal data class AboutMeState(
    @StringRes val name: Int,
    @StringRes val title: Int,
    val profileImage: ImageState,
    val socialProfiles: List<SocialProfileState>,
    val getToKnowMe: TopicsState,
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
) {
    fun description(@IntRange(from = 0, to = 3) position: Int): TopicDescription? =
        when (position) {
            0 -> firstTopic.description
            1 -> secondTopic.description
            2 -> thirdTopic.description
            3 -> fourthTopic.description
            else -> null
        }
}

@Immutable
internal data class Topic(
    @StringRes val textResId: Int,
    val description: TopicDescription,
)

@Immutable
internal data class TopicDescription(
    @StringRes val bodyResId: Int,
    @StringRes val titleResId: Int,
    val image: TopicDescriptionImage,
    val hyperlink: TopicHyperlink? = null,
)

@Immutable
internal sealed interface TopicDescriptionImage {
    @Immutable
    data class HeroImage(val state: ImageState) : TopicDescriptionImage

    @Immutable
    data class Image(val state: ImageState) : TopicDescriptionImage
}

@Immutable
internal data class TopicHyperlink(
    @StringRes val textResId: Int,
    @StringRes val urlResId: Int,
)

@Immutable
internal data class SocialProfileState(
    @StringRes val text: Int,
    @StringRes val url: Int,
    val icon: IconState,
)